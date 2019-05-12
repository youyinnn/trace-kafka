package com.github.youyinnn.tracekafkaapi.api;

import io.opentracing.SpanContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouSpanContext implements SpanContext {

    private final long traceIdLow;
    private final long traceIdHigh;
    private final long spanId;
    private final long parentId;
    private final Map<String, String> baggage;
    private final YouObjectFactory objectFactory;

    public YouSpanContext(long traceIdLow,
                          long traceIdHigh,
                          long parentId,
                          long spanId,
                          Map<String, String> baggage,
                          YouObjectFactory objectFactory) {

        this.traceIdLow = traceIdLow;
        this.traceIdHigh = traceIdHigh;
        this.parentId = parentId;
        this.spanId = spanId;
        this.baggage = baggage;
        this.objectFactory = objectFactory;
    }

    @Override
    public String toTraceId() {
        return null;
    }

    @Override
    public String toSpanId() {
        return null;
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
        return traceIdHigh + "-" + traceIdLow + "-" + parentId + "-" + spanId;
    }

    boolean hasTrace() {
        return (traceIdLow != 0 || traceIdHigh != 0) && spanId != 0;
    }

    public Map<String, String> baggage() {
        return baggage;
    }

    public long getTraceIdLow() {
        return traceIdLow;
    }

    public long getTraceIdHigh() {
        return traceIdHigh;
    }

    public long getSpanId() {
        return spanId;
    }

    public long getParentId() {
        return parentId;
    }

    public String getTraceId() {
        return String.valueOf(traceIdHigh) + traceIdLow;
    }

    public YouObjectFactory getObjectFactory() {
        return objectFactory;
    }
}
