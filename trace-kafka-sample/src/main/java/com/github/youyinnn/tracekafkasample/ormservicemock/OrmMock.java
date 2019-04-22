package com.github.youyinnn.tracekafkasample.ormservicemock;

import com.github.youyinnn.tracekafkasample.utils.RandomUtils;
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
    public int save() {
        RandomUtils.latencyMock(50);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Save: {}", anInt);
        return anInt;
    }

    @Override
    public int update() {
        RandomUtils.latencyMock(20);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Update: {}", anInt);
        return anInt;
    }

    @Override
    public String search() {
        RandomUtils.latencyMock(10);
        String rs = UUID.randomUUID().toString();
        LOGGER.info("OrmMock Search: {}", rs);
        return rs;
    }

    @Override
    public int delete() {
        RandomUtils.latencyMock(1);
        int anInt = RandomUtils.random.nextInt(1);
        LOGGER.info("OrmMock Delete: {}", anInt);
        return anInt;
    }
}
