package com.github.youyinnn.tracekafkasample.model;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author youyinnn
 * Date 5/20/2019
 */
public class KafkaMessage {

    private HashMap<String, String> header = new HashMap<>(16);
    private String message;

    public KafkaMessage() {
    }

    public KafkaMessage(String message) {
        this.message = message;
    }

    public KafkaMessage setHeader(HashMap<String, String> header) {
        this.header = header;
        return this;
    }

    public KafkaMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public KafkaMessage addHeader(String key, String value) {
        header.put(key, value);
        return this;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KafkaMessage.class.getSimpleName() + "[", "]")
                .add("header=" + header)
                .add("message='" + message + "'")
                .toString();
    }
}
