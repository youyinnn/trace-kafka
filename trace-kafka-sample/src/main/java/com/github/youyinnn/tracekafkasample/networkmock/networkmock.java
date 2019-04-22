package com.github.youyinnn.tracekafkasample.networkmock;

import com.github.youyinnn.tracekafkasample.utils.RandomUtils;

import java.util.HashMap;
import java.util.function.Function;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
public class networkmock {

    public static String transfer(Function<HashMap, String> function,
                                 HashMap<String, String> parameters) {
        RandomUtils.latencyMock(50);
        return function.apply(parameters);
    }

}
