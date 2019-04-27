package com.github.youyinnn.tracekafkaapi.reporter;

import com.github.youyinnn.tracekafkaapi.YouSpan;

public interface Reporter {

    void report(YouSpan span);

    void close();
}
