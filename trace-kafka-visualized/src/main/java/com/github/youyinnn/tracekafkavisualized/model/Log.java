package com.github.youyinnn.tracekafkavisualized.model;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class Log {

    private String logId;
    private String spanId;
    private String key;
    private String value;

    public String getLogId() {
        return logId;
    }

    public Log setLogId(String logId) {
        this.logId = logId;
        return this;
    }

    public String getSpanId() {
        return spanId;
    }

    public Log setSpanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Log setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Log setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", spanId='" + spanId + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
