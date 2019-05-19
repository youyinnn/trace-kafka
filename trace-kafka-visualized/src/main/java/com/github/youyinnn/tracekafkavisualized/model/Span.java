package com.github.youyinnn.tracekafkavisualized.model;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class Span {

    private String spanId;
    private String operationName;
    private String traceId;
    private long startTime;
    private long finishTime;
    private String tagKeyValueJson;

    public String getSpanId() {
        return spanId;
    }

    public Span setSpanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    public String getOperationName() {
        return operationName;
    }

    public Span setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public Span setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public long getStartTime() {
        return startTime;
    }

    public Span setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public Span setFinishTime(long finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public String getTagKeyValueJson() {
        return tagKeyValueJson;
    }

    public Span setTagKeyValueJson(String tagKeyValueJson) {
        this.tagKeyValueJson = tagKeyValueJson;
        return this;
    }

    @Override
    public String toString() {
        return "Span{" +
                "spanId='" + spanId + '\'' +
                ", operationName='" + operationName + '\'' +
                ", traceId='" + traceId + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", tagKeyValueJson='" + tagKeyValueJson + '\'' +
                '}';
    }
}
