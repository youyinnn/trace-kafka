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

    public void setBaggageId(String baggageId) {
        this.baggageId = baggageId;
    }

    public String getSpanContextId() {
        return spanContextId;
    }

    public void setSpanContextId(String spanContextId) {
        this.spanContextId = spanContextId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
