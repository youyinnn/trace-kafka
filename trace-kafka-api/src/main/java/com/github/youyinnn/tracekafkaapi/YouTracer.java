package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.propagation.Extractor;
import com.github.youyinnn.tracekafkaapi.propagation.Injector;
import com.github.youyinnn.tracekafkaapi.propagation.PropagationRegistry;
import com.github.youyinnn.tracekafkaapi.reporter.Reporter;
import com.github.youyinnn.tracekafkaapi.utils.IdGenerator;
import com.github.youyinnn.tracekafkaapi.utils.SystemClock;
import io.jaegertracing.internal.exceptions.UnsupportedFormatException;
import io.opentracing.*;
import io.opentracing.propagation.Format;
import io.opentracing.tag.Tag;
import io.opentracing.util.ThreadLocalScopeManager;

import java.io.Closeable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouTracer implements Tracer, Closeable {

    private final String serviceName;
    private Reporter reporter;
    private final Map<String, String> tags;

    private final SystemClock clock;
    private final ScopeManager scopeManager;
    private final YouObjectFactory objectFactory;
    private final PropagationRegistry propagationRegistry;

    private YouTracer(YouTracer.Builder builder) {
        this.serviceName = builder.serviceName;
        this.reporter = builder.reporter;
        this.tags = new ConcurrentHashMap<>();
        this.propagationRegistry = builder.propagationRegistry;
        tags.putAll(builder.tags);
        this.clock = builder.clock;
        this.scopeManager = builder.scopeManager;
        this.objectFactory = builder.objectFactory;

        if (!builder.manualShutdown) {
            Runtime.getRuntime().addShutdownHook(new Thread(YouTracer.this::close));
        }
    }

    public SystemClock clock() {
        return clock;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Reporter getReporter() {
        return reporter;
    }

    void reportSpan(YouSpan span) {
        if (reporter != null) {
            reporter.report(span);
        }
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public ScopeManager getScopeManager() {
        return scopeManager;
    }

    @Override
    public ScopeManager scopeManager() {
        return scopeManager;
    }

    @Override
    public Span activeSpan() {
        return scopeManager.activeSpan();
    }

    @Override
    public Scope activateSpan(Span span) {
        return scopeManager.activate(span);
    }

    @Override
    public SpanBuilder buildSpan(String operationName) {
        return objectFactory.createSpanBuilder(this, operationName);
    }

    @Override
    public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {
        Injector<C> injector = propagationRegistry.getInjector(format);
        if (injector == null) {
            throw new UnsupportedFormatException(format);
        }
        injector.inject((YouSpanContext) spanContext, carrier);
    }

    @Override
    public <C> YouSpanContext extract(Format<C> format, C carrier) {
        Extractor<C> extractor = propagationRegistry.getExtractor(format);
        if (extractor == null) {
            throw new UnsupportedFormatException(format);
        }
        return extractor.extract(carrier);
    }

    @Override
    public void close() {
        reporter.close();
    }

    public static class Builder {
        private Reporter reporter;
        private final String serviceName;
        private Map<String, String> tags = new HashMap<>();
        private SystemClock clock = SystemClock.getInstance();
        private ScopeManager scopeManager = new ThreadLocalScopeManager();
        private YouObjectFactory objectFactory = YouObjectFactory.getInstance();
        private boolean manualShutdown;
        private final PropagationRegistry propagationRegistry = new PropagationRegistry();

        public Builder(String serviceName) {
            this.serviceName = serviceName;
        }

        public Builder withReporter(Reporter reporter) {
            this.reporter = reporter;
            return this;
        }

        public Builder withManualShutdown() {
            this.manualShutdown = true;
            return this;
        }

        public Builder withTag(String key, String value) {
            tags.putIfAbsent(key, value);
            return this;
        }

        public Builder withTag(String key, boolean value) {
            tags.putIfAbsent(key, String.valueOf(value));
            return this;
        }

        public Builder withTag(String key, Number value) {
            tags.putIfAbsent(key, String.valueOf(value));
            return this;
        }

        public Builder withTags(Map<String, String> tags) {
            if (tags != null) {
                this.tags.putAll(tags);
            }
            return this;
        }

        public YouTracer build() {
            if (reporter == null) {
                // create reporter
            }
            return createTracer();
        }

        protected YouTracer createTracer() {
            return new YouTracer(this);
        }

        public <T> Builder registerInjector(Format<T> format, Injector<T> injector) {
            this.propagationRegistry.register(format, injector);
            return this;
        }

        public <T> Builder registerExtractor(Format<T> format, Extractor<T> extractor) {
            this.propagationRegistry.register(format, extractor);
            return this;
        }
    }

    public class SpanBuilder implements Tracer.SpanBuilder {

        private String operationName;
        private long startTimeMilliseconds;

        /**
         * In 99% situations there is only one parent (childOf), so we do not want to allocate
         * a collection of references.
         */
        private List<Reference> references = Collections.emptyList();

        private final Map<String, String> tags = new HashMap<>();
        private boolean ignoreActiveSpan = false;

        public SpanBuilder(String operationName) {
            this.operationName = operationName;
        }

        @Override
        public YouTracer.SpanBuilder asChildOf(SpanContext parent) {
            return addReference(References.CHILD_OF, parent);
        }

        @Override
        public YouTracer.SpanBuilder asChildOf(Span parent) {
            return addReference(References.CHILD_OF, parent != null ? parent.context() : null);
        }

        @Override
        public YouTracer.SpanBuilder addReference(String referenceType, SpanContext referencedContext) {
            if (!(referencedContext instanceof YouSpanContext)) {
                return this;
            }
            YouSpanContext realReferenceContext = (YouSpanContext) referencedContext;

            // dose not support other reference
            if (!References.CHILD_OF.equals(referenceType) &&
                    !References.FOLLOWS_FROM.equals(referenceType)) {
                return this;
            }

            if (references.isEmpty()) {
                // Optimization for 99% situations, when there is only one parent
                references = Collections.singletonList(new Reference(realReferenceContext, referenceType));
            } else {
                if (references.size() == 1) {
                    references = new ArrayList<>(references);
                }
                references.add(new Reference(realReferenceContext, referenceType));
            }

            return null;
        }

        @Override
        public YouTracer.SpanBuilder ignoreActiveSpan() {
            ignoreActiveSpan = true;
            return this;
        }

        @Override
        public YouTracer.SpanBuilder withTag(String key, String value) {
            tags.put(key, value);
            return this;
        }

        @Override
        public YouTracer.SpanBuilder withTag(String key, boolean value) {
            tags.put(key, String.valueOf(value));
            return this;
        }

        @Override
        public YouTracer.SpanBuilder withTag(String key, Number value) {
            tags.put(key, String.valueOf(value));
            return this;
        }

        @Override
        public <T> YouTracer.SpanBuilder withTag(Tag<T> tag, T value) {
            tags.put(tag.getKey(), String.valueOf(value));
            return this;
        }

        @Override
        public YouTracer.SpanBuilder withStartTimestamp(long milliseconds) {
            this.startTimeMilliseconds = milliseconds;
            return this;
        }

        @Override
        public YouSpan startManual() {
            return start();
        }

        @Override
        public YouSpan start() {
            YouSpanContext context;

            // Check if active span should be established as CHILD_OF relationship
            if (references.isEmpty() && !ignoreActiveSpan && null != scopeManager.active()) {
                asChildOf(scopeManager.activeSpan());
            }

            if (references.isEmpty() ||
                    !references.get(0).getSpanContext().hasTrace()) {
                context = createNewContext();
            } else {
                context = createChildContext();
            }

            if (startTimeMilliseconds == 0) {
                startTimeMilliseconds = clock.currentTimeMillis();
            }

            return objectFactory.createSpan(
                    YouTracer.this,
                    operationName,
                    startTimeMilliseconds,
                    tags,
                    context,
                    references);
        }

        private YouSpanContext createChildContext() {
            YouSpanContext preferredReference = preferredReference();

            return objectFactory.createContext(
                    preferredReference.getTraceIdHigh(),
                    preferredReference.getTraceIdLow(),
                    preferredReference.getSpanId(),
                    IdGenerator.uniqueId(),
                    preferredReference.baggage());
        }

        private YouSpanContext createNewContext() {

            return objectFactory.createContext(
                    IdGenerator.uniqueId(),
                    IdGenerator.uniqueId(),
                    0,
                    IdGenerator.uniqueId(),
                    getBaggage());
        }

        private Map<String, String> getBaggage() {
            Map<String, String> baggage = null;

            // optimization for 99% use cases, when there is only one parent
            if (references.size() == 1) {
                return references.get(0).getSpanContext().baggage();
            }

            for (Reference reference: references) {
                if (reference.getSpanContext().baggage() != null) {
                    if (baggage == null) {
                        baggage = new HashMap<>();
                    }
                    baggage.putAll(reference.getSpanContext().baggage());
                }
            }

            return baggage;
        }

        private YouSpanContext preferredReference() {
            Reference preferredReference = references.get(0);
            for (Reference reference: references) {
                // childOf takes precedence as a preferred parent
                if (References.CHILD_OF.equals(reference.getType())
                        && !References.CHILD_OF.equals(preferredReference.getType())) {
                    preferredReference = reference;
                    break;
                }
            }
            return preferredReference.getSpanContext();
        }

        @Override
        public Scope startActive(boolean finishSpanOnClose) {
            return scopeManager.activate(start(), finishSpanOnClose);
        }
    }
}
