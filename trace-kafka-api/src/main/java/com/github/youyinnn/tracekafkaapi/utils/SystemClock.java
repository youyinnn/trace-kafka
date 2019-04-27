package com.github.youyinnn.tracekafkaapi.utils;

/**
 * @author youyinnn
 * Date 4/25/2019
 */
public class SystemClock {

    private SystemClock() {}

    private static class Holder {
        private static final SystemClock INSTANCE = new SystemClock();
    }

    public static SystemClock getInstance() {
        return Holder.INSTANCE;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long currentTimeMicros() {
        return System.currentTimeMillis() * 1000;
    }

    public long currentTimeNanos() {
        return System.nanoTime();
    }

}
