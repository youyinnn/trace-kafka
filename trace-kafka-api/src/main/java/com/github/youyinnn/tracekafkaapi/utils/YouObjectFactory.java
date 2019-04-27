package com.github.youyinnn.tracekafkaapi.utils;

import com.github.youyinnn.tracekafkaapi.YouSpan;
import com.github.youyinnn.tracekafkaapi.YouTracer;

import java.util.Map;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class YouObjectFactory {

    private YouObjectFactory() {}

    public static YouObjectFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final YouObjectFactory INSTANCE =
                new YouObjectFactory();
    }

    public YouSpan createSpan(
            YouTracer tracer,
            String operationName,
            long startTimeMicroseconds,
            long startTimeNanoTicks,
            Map<String, Object> tags) {

        return null;
    }

}
