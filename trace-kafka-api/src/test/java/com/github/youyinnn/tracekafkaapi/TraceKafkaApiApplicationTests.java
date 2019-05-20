package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.api.YouTracer;
import com.github.youyinnn.tracekafkaapi.codec.TextMapAdapter;
import com.github.youyinnn.tracekafkaapi.codec.TextMapCodec;
import com.github.youyinnn.tracekafkaapi.reporter.ConsoleReporter;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.util.GlobalTracer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraceKafkaApiApplicationTests {

    @Before
    public void tracerRegister() {
        YouTracer tracer = new YouTracer.Builder()
                .withReporter(new ConsoleReporter())
                .registerCodec(Format.Builtin.TEXT_MAP, new TextMapCodec(true))
                .build();
        System.out.println(tracer);

        GlobalTracer.registerIfAbsent(tracer);
        Tracer global = GlobalTracer.get();

        System.out.println(global);
    }

    @Test
    public void testStartSpan(){
        Tracer tracer = GlobalTracer.get();
        Span op1 = tracer.buildSpan("op1").start();
        op1.log("event1");
        op1.setTag("tagk1", "tagv1");
        op1.setTag("tagk2", "tagv2");
        //ConsoleReporter.staticReport(op1);
        YouTracer.setServiceName(op1, "service1");

        op1.finish();
    }

    @Test
    public void testChildOf(){
        Tracer tracer = GlobalTracer.get();
        Span op1 = tracer.buildSpan("op1").start();
        op1.log("event1");
        op1.setTag("tagk1", "tagv1");
        op1.setTag("tagk2", "tagv2");
        //ConsoleReporter.staticReport(op1);
        YouTracer.setServiceName(op1, "service1");

        Span op2 = tracer.buildSpan("op2").asChildOf(op1).start();
        op2.log("event2");

        op2.finish();
        op1.finish();
    }

    @Test
    public void testInjectAndExtract(){
        Tracer tracer = GlobalTracer.get();
        Span op1 = tracer.buildSpan("op1").start();
        op1.log("event1");
        op1.setTag("tagk1", "tagv1");
        op1.setTag("tagk2", "tagv2");
        op1.setBaggageItem("bgk1", "bgv1");
        //ConsoleReporter.staticReport(op1);
        YouTracer.setServiceName(op1, "service1");

        HashMap<String, String> map = new HashMap<>();
        TextMapAdapter textMapAdapter = new TextMapAdapter(map);
        tracer.inject(op1.context(), Format.Builtin.TEXT_MAP, textMapAdapter);

        System.out.println(map);
        SpanContext extract = tracer.extract(Format.Builtin.TEXT_MAP, textMapAdapter);
        assertEquals(op1.context().toTraceId(), extract.toTraceId());
        assertEquals(op1.context().toSpanId(), extract.toSpanId());
        for (Map.Entry<String, String> entry : extract.baggageItems()) {
            System.out.println(entry);
        }
    }

}
