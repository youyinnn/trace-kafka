package com.github.youyinnn.tracekafkavisualized.model;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class Baggage {

    private String baggageId;
    private String spanContextId;
    private String key;
    private String value;

    public String getBaggageId() {
        return baggageId;
    }

    public Baggage setBaggageId(String baggageId) {
        this.baggageId = baggageId;
        return this;
    }

    public String getSpanContextId() {
        return spanContextId;
    }

    public Baggage setSpanContextId(String spanContextId) {
        this.spanContextId = spanContextId;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Baggage setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Baggage setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "baggageId='" + baggageId + '\'' +
                ", spanContextId='" + spanContextId + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
