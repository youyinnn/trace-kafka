package com.github.youyinnn.tracekafkavisualized.model;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class SpanContext {

    private String spanContextId;
    private String spanId;
    private String traceId;
    private String referenceJson;

    public String getSpanContextId() {
        return spanContextId;
    }

    public SpanContext setSpanContextId(String spanContextId) {
        this.spanContextId = spanContextId;
        return this;
    }

    public String getSpanId() {
        return spanId;
    }

    public SpanContext setSpanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public SpanContext setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public String getReferenceJson() {
        return referenceJson;
    }

    public SpanContext setReferenceJson(String referenceJson) {
        this.referenceJson = referenceJson;
        return this;
    }

    @Override
    public String toString() {
        return "SpanContext{" +
                "spanContextId='" + spanContextId + '\'' +
                ", spanId='" + spanId + '\'' +
                ", traceId='" + traceId + '\'' +
                ", referenceJson='" + referenceJson + '\'' +
                '}';
    }
}
