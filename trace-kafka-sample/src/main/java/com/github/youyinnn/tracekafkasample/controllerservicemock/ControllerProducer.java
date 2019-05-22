package com.github.youyinnn.tracekafkasample.controllerservicemock;

import com.alibaba.fastjson.JSON;
import com.github.youyinnn.tracekafkasample.model.KafkaMessage;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
@Component
public class ControllerProducer {

    @Value("${cloudkarafka.controller.topic}")
    private String topic;

    private final Logger LOGGER = LoggerFactory.getLogger(ControllerProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    ControllerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(KafkaMessage message, Span parent) {
        Span send = GlobalTracer.get().buildSpan("c-sendMessage")
                .asChildOf(parent)
                .start();

        this.kafkaTemplate.send(topic, JSON.toJSONString(message));
        send.log("c send msg:" + JSON.toJSONString(message));
        LOGGER.info("ControllerService sent message [ {} ] to {}", message, topic);
        send.finish();
    }

}
