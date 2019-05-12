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

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getTagKeyValueJson() {
        return tagKeyValueJson;
    }

    public void setTagKeyValueJson(String tagKeyValueJson) {
        this.tagKeyValueJson = tagKeyValueJson;
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
