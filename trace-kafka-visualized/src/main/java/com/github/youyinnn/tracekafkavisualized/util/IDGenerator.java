package com.github.youyinnn.tracekafkavisualized.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author youyinnn
 * Date 5/19/2019
 */
public class IDGenerator {

    private static AtomicLong atomicLong = new AtomicLong();

    private static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String getID() {
        return (System.nanoTime() + atomicLong.getAndIncrement()) + "-" + getUUID32();
    }

}
