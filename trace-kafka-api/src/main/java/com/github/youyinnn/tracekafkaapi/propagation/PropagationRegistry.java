package com.github.youyinnn.tracekafkaapi.propagation;

import com.github.youyinnn.tracekafkaapi.api.YouSpanContext;
import com.github.youyinnn.tracekafkaapi.codec.Codec;
import io.opentracing.propagation.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youyinnn
 * Date 4/30/2019
 */
public class PropagationRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropagationRegistry.class);

    private final Map<Format<?>, Injector<?>> injectors = new HashMap<Format<?>, Injector<?>>();
    private final Map<Format<?>, Extractor<?>> extractors = new HashMap<Format<?>, Extractor<?>>();

    @SuppressWarnings("unchecked")
    public <T> Injector<T> getInjector(Format<T> format) {
        return (Injector<T>) injectors.get(format);
    }

    @SuppressWarnings("unchecked")
    public <T> Extractor<T> getExtractor(Format<T> format) {
        return (Extractor<T>) extractors.get(format);
    }

    public <T> void register(Format<T> format, Injector<T> injector) {
        injectors.put(format, new ExceptionCatchingInjectorDecorator<>(injector));
    }

    public <T> void register(Format<T> format, Extractor<T> extractor) {
        extractors.put(format, new ExceptionCatchingExtractorDecorator<>(extractor));
    }

    public <T> void register(Format<T> format, Codec<T> codec) {
        injectors.put(format, new ExceptionCatchingInjectorDecorator<>(codec));
        extractors.put(format, new ExceptionCatchingExtractorDecorator<>(codec));
    }

    private static class ExceptionCatchingExtractorDecorator<T> implements Extractor<T> {

        private final Extractor<T> decorated;

        private ExceptionCatchingExtractorDecorator(Extractor<T> decorated) {
            this.decorated = decorated;
        }

        @Override
        public YouSpanContext extract(T carrier) {
            try {
                return decorated.extract(carrier);
            } catch (RuntimeException ex) {
                LOGGER.warn("Error when extracting SpanContext from carrier. Handling gracefully.", ex);
                return null;
            }
        }
    }

    private static class ExceptionCatchingInjectorDecorator<T> implements Injector<T> {

        private final Injector<T> decorated;

        private ExceptionCatchingInjectorDecorator(Injector<T> decorated) {
            this.decorated = decorated;
        }

        @Override
        public void inject(YouSpanContext spanContext, T carrier) {
            try {
                decorated.inject(spanContext, carrier);
            } catch (RuntimeException ex) {
                LOGGER.error("Error when injecting SpanContext into carrier. Handling gracefully.", ex);
            }
        }
    }

}
