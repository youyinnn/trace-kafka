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

    public void setSpanContextId(String spanContextId) {
        this.spanContextId = spanContextId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getReferenceJson() {
        return referenceJson;
    }

    public void setReferenceJson(String referenceJson) {
        this.referenceJson = referenceJson;
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
