package com.github.youyinnn.tracekafkaapi.api;

import io.opentracing.SpanContext;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouSpanContext implements SpanContext {

    private final String traceId;
    private final String spanId;
    private final String parentId;
    private final Map<String, String> baggage;
    private final YouObjectFactory objectFactory;

    public YouSpanContext(String traceId,
                          String parentId,
                          String spanId,
                          Map<String, String> baggage,
                          YouObjectFactory objectFactory) {

        this.traceId = traceId;
        this.parentId = parentId;
        this.spanId = spanId;
        this.baggage = baggage;
        this.objectFactory = objectFactory;
    }

    @Override
    public String toTraceId() {
        return traceId;
    }

    @Override
    public String toSpanId() {
        return spanId;
    }

    @Override
    public Iterable<Map.Entry<String, String>> baggageItems() {
        return new HashMap<>(baggage).entrySet();
    }

    public String getBaggageItem(String key) {
        return baggage.get(key);
    }

    public void setBaggageItem(String key, String value) {
        baggage.put(key, value);
    }

    public String getContextKey() {
        return traceId + "-" + parentId + "-" + spanId;
    }

    public Map<String, String> baggage() {
        return baggage;
    }

    public String getSpanId() {
        return spanId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getTraceId() {
        return traceId;
    }

    public YouObjectFactory getObjectFactory() {
        return objectFactory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", YouSpanContext.class.getSimpleName() + "[", "]")
                .add("spanId='" + spanId + "'")
                .add("parentId='" + parentId + "'")
                .add("traceId='" + traceId + "'")
                .add("baggage=" + baggage)
                .toString();
    }
}
