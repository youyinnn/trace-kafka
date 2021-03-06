package com.github.youyinnn.tracekafkaapi.api;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author youyinnn
 * Date 4/27/2019
 */
public class LogData {

    private final long time; // time in microseconds
    private final String message;
    private final Map<String, ?> fields;

    LogData(long time, String message, Map<String, ?> fields) {
        this.time = time;
        this.message = message;
        this.fields = fields;
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, ?> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogData.class.getSimpleName() + "[", "]")
                .add("time=" + time)
                .add("message='" + message + "'")
                .add("fields=" + fields)
                .toString();
    }
}
