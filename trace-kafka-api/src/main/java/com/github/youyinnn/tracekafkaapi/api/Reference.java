package com.github.youyinnn.tracekafkaapi.api;

/**
 * @author youyinnn
 * Date 4/29/2019
 */
public class Reference {

    private final YouSpanContext spanContext;
    private final String type;

    public Reference(YouSpanContext spanContext, String type) {
        this.spanContext = spanContext;
        this.type = type;
    }

    public YouSpanContext getSpanContext() {
        return spanContext;
    }

    public String getType() {
        return type;
    }
}
