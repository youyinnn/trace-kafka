package com.github.youyinnn.tracekafkaapi;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.InMemoryReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Reporter;
import io.jaegertracing.spi.Sampler;
import io.opentracing.Tracer;
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

        Tracer tracer1 = GlobalTracer.get();

        Reporter reporter = new InMemoryReporter();
        Sampler sampler = new ConstSampler(true);
        Tracer tracer = new JaegerTracer.Builder("123")
                .withReporter(reporter)
                .withSampler(sampler)
                .build();
    }
}
