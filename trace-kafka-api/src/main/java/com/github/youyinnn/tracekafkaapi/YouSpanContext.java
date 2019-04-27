package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.utils.YouObjectFactory;
import io.opentracing.SpanContext;

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
                          long spanId,
                          long parentId,
                          Map<String, String> baggage,
                          YouObjectFactory objectFactory) {

        this.traceIdLow = traceIdLow;
        this.traceIdHigh = traceIdHigh;
        this.spanId = spanId;
        this.parentId = parentId;
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
        return null;
    }

    public String getBaggageItem(String key) {
        return baggage.get(key);
    }

    public void setBaggageItem(String key, String value) {
        baggage.put(key, value);
    }

}
