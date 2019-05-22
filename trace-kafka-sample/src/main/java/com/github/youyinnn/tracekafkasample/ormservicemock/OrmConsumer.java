package com.github.youyinnn.tracekafkasample.ormservicemock;

import com.alibaba.fastjson.JSON;
import com.github.youyinnn.tracekafkaapi.api.YouTracer;
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
            Tracer tracer = GlobalTracer.get();
            SpanContext extract
                    = tracer.extract(Format.Builtin.TEXT_MAP, new TextMapAdapter(kafkaMessage.getHeader()));
            switch (kafkaMessage.getMessage()) {
                case "1":
                    Span saveData = tracer.buildSpan("orm-process")
                            .asChildOf(extract)
                            .withTag("data-op", "1")
                            .start();
                    YouTracer.setServiceName(saveData, "saveData-op");
                    int save = ormMock.save(saveData);
                    KafkaMessage saveMsg = new KafkaMessage("Save: " + save);
                    tracer.inject(extract, Format.Builtin.TEXT_MAP, new TextMapAdapter(saveMsg.getHeader()));
                    producer.send(saveMsg, saveData);
                    saveData.finish();
                    break;
                case "2":
                    Span updateData = tracer.buildSpan("orm-process")
                            .asChildOf(extract)
                            .withTag("data-op", "2")
                            .start();
                    YouTracer.setServiceName(updateData, "updateData-op");
                    int update = ormMock.update(updateData);
                    KafkaMessage updateMsg = new KafkaMessage("Update: " + update);
                    tracer.inject(extract, Format.Builtin.TEXT_MAP, new TextMapAdapter(updateMsg.getHeader()));
                    producer.send(updateMsg, updateData);
                    updateData.finish();
                    break;
                case "3":
                    Span searchData = tracer.buildSpan("orm-process")
                            .asChildOf(extract)
                            .withTag("data-op", "3")
                            .start();
                    YouTracer.setServiceName(searchData, "updateData-op");
                    String search = ormMock.search(searchData);
                    KafkaMessage searchMsg = new KafkaMessage("Search: " + search);
                    tracer.inject(extract, Format.Builtin.TEXT_MAP, new TextMapAdapter(searchMsg.getHeader()));
                    producer.send(searchMsg, searchData);
                    searchData.finish();
                    break;
                case "4":
                    Span deleteData = tracer.buildSpan("orm-process")
                            .asChildOf(extract)
                            .withTag("data-op", "4")
                            .start();
                    YouTracer.setServiceName(deleteData, "deleteData-op");
                    int delete = ormMock.delete(deleteData);
                    KafkaMessage deleteMsg = new KafkaMessage("Delete: " + delete);
                    tracer.inject(extract, Format.Builtin.TEXT_MAP, new TextMapAdapter(deleteMsg.getHeader()));
                    producer.send(deleteMsg, deleteData);
                    deleteData.finish();
                    break;
                default:
                    break;
            }
        }
    }

}
