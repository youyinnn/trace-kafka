package com.github.youyinnn.tracekafkaapi.reporter;

import io.opentracing.Span;

public interface Reporter {

    void report(Span span);

    void close();
}
