package com.github.youyinnn.tracekafkasample.ormservicemock;

import com.github.youyinnn.tracekafkasample.utils.RandomUtils;
import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
@Component
public class OrmMock implements OrmInterface {

    private final Logger LOGGER = LoggerFactory.getLogger(OrmMock.class);


    @Override
    public int save(Span parent) {
        Span send = GlobalTracer.get().buildSpan("o-op")
                .asChildOf(parent)
                .start();
        RandomUtils.latencyMock(50);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Save: {}", anInt);
        send.log("op save: " + anInt);
        send.finish();
        return anInt;
    }

    @Override
    public int update(Span parent) {
        Span send = GlobalTracer.get().buildSpan("o-op")
                .asChildOf(parent)
                .start();
        RandomUtils.latencyMock(20);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Update: {}", anInt);
        send.log("op update: " + anInt);
        send.finish();
        return anInt;
    }

    @Override
    public String search(Span parent) {
        Span send = GlobalTracer.get().buildSpan("o-op")
                .asChildOf(parent)
                .start();
        RandomUtils.latencyMock(10);
        String rs = UUID.randomUUID().toString();
        LOGGER.info("OrmMock Search: {}", rs);
        send.log("op search: " + rs);
        send.finish();
        return rs;
    }

    @Override
    public int delete(Span parent) {
        Span send = GlobalTracer.get().buildSpan("o-op")
                .asChildOf(parent)
                .start();
        RandomUtils.latencyMock(1);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Delete: {}", anInt);
        send.log("op delete: " + anInt);
        send.finish();
        return anInt;
    }
}
