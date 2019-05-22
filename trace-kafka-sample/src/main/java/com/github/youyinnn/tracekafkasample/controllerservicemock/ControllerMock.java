package com.github.youyinnn.tracekafkasample.controllerservicemock;

import com.github.youyinnn.tracekafkaapi.api.YouTracer;
import com.github.youyinnn.tracekafkasample.model.KafkaMessage;
import com.github.youyinnn.tracekafkasample.utils.RandomUtils;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapAdapter;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
@Component
public class ControllerMock implements ControllerInterface {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ControllerProducer producer;

    @Override
    public void requestHandler() {
        Scanner in = new Scanner(System.in);
        System.out.println("Controller Mock Interface, enter:");
        System.out.println("1.save data");
        System.out.println("2.update data");
        System.out.println("3.search data");
        System.out.println("4.delete data");
        System.out.println("5.exit");
        int s = in.nextInt();
        RandomUtils.latencyMock(10);
        Tracer tracer = GlobalTracer.get();
        switch (s) {
            case 1:
                Span saveData = tracer.buildSpan("c-requestHandle")
                        .withTag("data", "1")
                        .start();
                YouTracer.setServiceName(saveData, "saveData");
                KafkaMessage save = new KafkaMessage("1");
                tracer.inject(saveData.context(), Format.Builtin.TEXT_MAP, new TextMapAdapter(save.getHeader()));
                producer.send(save, saveData);
                saveData.finish();
                break;
            case 2:
                Span updateData = tracer.buildSpan("c-requestHandle")
                        .withTag("data", "2")
                        .start();
                YouTracer.setServiceName(updateData, "updateData");
                KafkaMessage update = new KafkaMessage("2");
                tracer.inject(updateData.context(), Format.Builtin.TEXT_MAP, new TextMapAdapter(update.getHeader()));
                producer.send(update, updateData);
                updateData.finish();
                break;
            case 3:
                Span searchData = tracer.buildSpan("c-requestHandle")
                        .withTag("data", "3")
                        .start();
                YouTracer.setServiceName(searchData, "searchData");
                KafkaMessage search = new KafkaMessage("3");
                tracer.inject(searchData.context(), Format.Builtin.TEXT_MAP, new TextMapAdapter(search.getHeader()));
                producer.send(search, searchData);
                searchData.finish();
                break;
            case 4:
                Span deleteData = tracer.buildSpan("c-requestHandle")
                        .withTag("data", "3")
                        .start();
                YouTracer.setServiceName(deleteData, "deleteData");
                KafkaMessage delete = new KafkaMessage("4");
                tracer.inject(deleteData.context(), Format.Builtin.TEXT_MAP, new TextMapAdapter(delete.getHeader()));
                producer.send(delete, deleteData);
                deleteData.finish();
                break;
            case 5:
                SpringApplication.exit(applicationContext, () -> 0);
                break;
            default:
                break;
        }
    }
}
