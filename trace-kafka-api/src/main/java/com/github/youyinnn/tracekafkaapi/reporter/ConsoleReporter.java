package com.github.youyinnn.tracekafkaapi.reporter;

import io.opentracing.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youyinnn
 * Date 5/19/2019
 */
public class ConsoleReporter implements Reporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReporter.class);

    public static void staticReport(Span span) {
        LOGGER.info("{}", span);
    }

    @Override
    public void report(Span span) {
        staticReport(span);
    }

    @Override
    public void close() {

    }
}
