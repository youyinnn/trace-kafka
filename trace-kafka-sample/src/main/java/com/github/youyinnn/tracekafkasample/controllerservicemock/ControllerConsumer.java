package com.github.youyinnn.tracekafkasample.controllerservicemock;

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
        LOGGER.info("ControllerService receive message from orm: [ {} ]", message);
        controller.requestHandler();
    }

}
