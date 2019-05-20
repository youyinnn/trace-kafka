package com.github.youyinnn.tracekafkasample.ormservicemock;

import com.alibaba.fastjson.JSON;
import com.github.youyinnn.tracekafkasample.model.KafkaMessage;
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
class OrmConsumer {

    @Autowired
    private OrmMock ormMock;

    @Autowired
    private OrmProducer producer;

    private final Logger LOGGER = LoggerFactory.getLogger(OrmConsumer.class);

    @KafkaListener(topics = "${cloudkarafka.controller.topic}")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        if (JSON.isValid(message)) {
            LOGGER.info("OrmService receive message from controller: [ {} ]", message);
            KafkaMessage kafkaMessage = JSON.parseObject(message, KafkaMessage.class);
            switch (kafkaMessage.getMessage()) {
                case "1":
                    int save = ormMock.save();
                    producer.send(new KafkaMessage("Save: " + save));
                    break;
                case "2":
                    int update = ormMock.update();
                    producer.send(new KafkaMessage("Update: " + update));
                    break;
                case "3":
                    String search = ormMock.search();
                    producer.send(new KafkaMessage("Search: " + search));
                    break;
                case "4":
                    int delete = ormMock.delete();
                    producer.send(new KafkaMessage("Delete: " + delete));
                    break;
                default:
                    break;
            }
        }
    }

}
