package com.github.youyinnn.tracekafkasample;

import com.github.youyinnn.tracekafkaapi.api.YouTracer;
import com.github.youyinnn.tracekafkaapi.codec.TextMapCodec;
import com.github.youyinnn.tracekafkaapi.reporter.ConsoleReporter;
import com.github.youyinnn.tracekafkasample.controllerservicemock.ControllerMock;
import io.opentracing.propagation.Format;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author youyinnn
 */
@SpringBootApplication
public class TraceKafkaSampleApplication implements CommandLineRunner {

    @Autowired
    private ControllerMock controller;

    public static void main(String[] args) {
        SpringApplication.run(TraceKafkaSampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        YouTracer tracer = new YouTracer.Builder()
                .withReporter(new ConsoleReporter())
                .registerCodec(Format.Builtin.TEXT_MAP, new TextMapCodec(true))
                .build();
        GlobalTracer.registerIfAbsent(tracer);
        controller.requestHandler();
    }
}
