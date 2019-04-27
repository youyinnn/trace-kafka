package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.reporter.Reporter;
import com.github.youyinnn.tracekafkaapi.utils.SystemClock;
import com.github.youyinnn.tracekafkaapi.utils.YouObjectFactory;
import io.opentracing.*;
import io.opentracing.propagation.Format;
import io.opentracing.util.ThreadLocalScopeManager;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
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

    protected YouTracer(YouTracer.Builder builder) {
        this.serviceName = builder.serviceName;
        this.reporter = builder.reporter;
        this.tags = new ConcurrentHashMap<>();
        tags.putAll(builder.tags);
        this.clock = builder.clock;
        this.scopeManager = builder.scopeManager;
        this.objectFactory = builder.objectFactory;
    }

    @Override
    public ScopeManager scopeManager() {
        return null;
    }

    @Override
    public Span activeSpan() {
        return null;
    }

    @Override
    public Scope activateSpan(Span span) {
        return null;
    }

    @Override
    public SpanBuilder buildSpan(String operationName) {
        return null;
    }

    @Override
    public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {

    }

    @Override
    public <C> SpanContext extract(Format<C> format, C carrier) {
        return null;
    }

    @Override
    public void close() {

    }

    public static class Builder {
        private Reporter reporter;
        private final String serviceName;
        private Map<String, String> tags = new HashMap<>();
        private SystemClock clock = SystemClock.getInstance();
        private ScopeManager scopeManager = new ThreadLocalScopeManager();
        private YouObjectFactory objectFactory = YouObjectFactory.getInstance();
        private boolean manualShutdown;

        public Builder(String serviceName) {
            this.serviceName = serviceName;
        }
    }
}
