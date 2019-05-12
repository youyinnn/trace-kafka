package com.github.youyinnn.tracekafkaapi.api;

import java.util.List;
import java.util.Map;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouObjectFactory {

    private YouObjectFactory() {}

    public static YouObjectFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final YouObjectFactory INSTANCE =
                new YouObjectFactory();
    }

    public YouSpan createSpan(
            YouTracer tracer,
            String operationName,
            long startTimeMicroseconds,
            Map<String, String> tags,
            YouSpanContext context,
            List<Reference> references) {

        return new YouSpan(
                tracer,
                startTimeMicroseconds,
                tags,
                operationName,
                context,
                references);
    }

    public YouSpanContext createContext(
            long traceIdHigh,
            long traceIdLow,
            long parentId,
            long spanId,
            Map<String, String> baggage) {

        return new YouSpanContext(
                traceIdLow,
                traceIdHigh,
                parentId,
                spanId,
                baggage,
                this);
    }

    public YouTracer.SpanBuilder createSpanBuilder(YouTracer tracer, String operationName) {
        return tracer.new SpanBuilder(operationName);
    }

}
