package com.github.youyinnn.tracekafkaapi.api;

import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Reference.class.getSimpleName() + "[", "]")
                .add("spanContext=" + spanContext)
                .add("type='" + type + "'")
                .toString();
    }
}
