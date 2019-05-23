package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.api.YouTracer;
import com.github.youyinnn.tracekafkaapi.codec.TextMapCodec;
import com.github.youyinnn.tracekafkaapi.reporter.ConsoleReporter;
import com.github.youyinnn.tracekafkaapi.reporter.HttpReporter;
import io.opentracing.Span;
import io.opentracing.propagation.Format;
import io.opentracing.util.GlobalTracer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraceKafkaApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TraceKafkaApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HttpReporter reporter = new HttpReporter();
        YouTracer tracer = new YouTracer.Builder()
                .withReporter(new ConsoleReporter())
                .registerCodec(Format.Builtin.TEXT_MAP, new TextMapCodec(true))
                .build();
        GlobalTracer.registerIfAbsent(tracer);
        Span span = tracer.buildSpan("c-receive")
                .start();
        reporter.report(span);
    }
}
