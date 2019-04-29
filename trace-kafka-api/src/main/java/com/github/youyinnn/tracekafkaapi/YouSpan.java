package com.github.youyinnn.tracekafkaapi;

import io.opentracing.Span;
import io.opentracing.tag.Tag;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouSpan implements Span {

    private final YouTracer tracer;
    private final long startTimeMilliseconds;
    private final Map<String, String> tags;
    private final List<Reference> references;

    /**
     * span durationMicroseconds
     */
    private long durationMicroseconds;
    private String operationName;
    private YouSpanContext context;

    private List<LogData> logs;

    /**
     * to prevent the same span from getting reported multiple times
     */
    private boolean finished = false;

    protected YouSpan(YouTracer tracer,
                      long startTimeMilliseconds,
                      Map<String, String> tags,
                      String operationName,
                      YouSpanContext context,
                      List<Reference> references) {

        this.tracer = tracer;
        this.startTimeMilliseconds = startTimeMilliseconds;
        this.operationName = operationName;
        this.tags = new ConcurrentHashMap<>(16);
        this.logs = new CopyOnWriteArrayList<>();
        this.context = context;
        this.references = references != null ? new ArrayList<>(references) : null;

        this.tags.putAll(tags);
    }

    public YouTracer getTracer() {
        return tracer;
    }

    public long getStartTimeMilliseconds() {
        return startTimeMilliseconds;
    }

    public Map<String, Object> getTags() {
        return Collections
                .unmodifiableMap(new HashMap<>(tags));
    }

    public long getDurationMicroseconds() {
        return durationMicroseconds;
    }

    public String getOperationName() {
        return operationName;
    }

    public List<LogData> getLogs() {
        return Collections.unmodifiableList(new LinkedList<>(logs));
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public YouSpanContext context() {
        return context;
    }

    @Override
    public YouSpan setTag(String key, String value) {
        tags.putIfAbsent(key, value);
        return this;
    }

    @Override
    public YouSpan setTag(String key, boolean value) {
        tags.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    @Override
    public YouSpan setTag(String key, Number value) {
        tags.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    @Override
    public <T> YouSpan setTag(Tag<T> tag, T value) {
        tags.putIfAbsent(tag.getKey(), String.valueOf(value));
        return this;
    }

    @Override
    public YouSpan log(Map<String, ?> fields) {
        return this;
    }

    @Override
    public YouSpan log(long timestampMicroseconds, Map<String, ?> fields) {
        return this;
    }

    @Override
    public YouSpan log(String event) {
        return this;
    }

    @Override
    public YouSpan log(long timestampMicroseconds, String event) {
        return this;
    }

    @Override
    public YouSpan setBaggageItem(String key, String value) {
        if (key == null ||
                (value == null && context.getBaggageItem(key) == null)) {
            return this;
        }
        synchronized (this) {
            context.setBaggageItem(key, value);
            return this;
        }
    }

    @Override
    public String getBaggageItem(String key) {
        return this.context.getBaggageItem(key);
    }

    @Override
    public YouSpan setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(long finishMicros) {

    }
}
