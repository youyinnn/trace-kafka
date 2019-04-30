package com.github.youyinnn.tracekafkaapi.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author youyinnn
 * Date 4/30/2019
 */
public class IdGenerator {

    public static long uniqueId() {
        return ThreadLocalRandom.current().nextLong();
    }

}
