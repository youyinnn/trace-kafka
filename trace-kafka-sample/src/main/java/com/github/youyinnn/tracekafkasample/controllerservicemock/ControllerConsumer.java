package com.github.youyinnn.tracekafkasample.controllerservicemock;

import com.alibaba.fastjson.JSON;
import com.github.youyinnn.tracekafkasample.model.KafkaMessage;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapAdapter;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
@Component
class ControllerConsumer {

    @Autowired
    private ControllerMock controller;

    private final Logger LOGGER = LoggerFactory.getLogger(ControllerConsumer.class);

    @KafkaListener(topics = "${cloudkarafka.orm.topic}")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        if (JSON.isValid(message)) {
            Tracer tracer = GlobalTracer.get();
            KafkaMessage kafkaMessage = JSON.parseObject(message, KafkaMessage.class);
            SpanContext extract
                    = tracer.extract(Format.Builtin.TEXT_MAP, new TextMapAdapter(kafkaMessage.getHeader()));
            Span span = tracer.buildSpan("c-receive")
                    .asChildOf(extract)
                    .start();
            LOGGER.info("ControllerService receive message from orm: [ {} ]", kafkaMessage);
            span.log("c receive data: " + kafkaMessage);

            controller.requestHandler();
            span.finish();
        }
    }

}
