package com.github.youyinnn.tracekafkasample.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
public class RandomUtils {

    public static Random random = new Random();

    public static void latencyMock(int bound) {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(bound) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
