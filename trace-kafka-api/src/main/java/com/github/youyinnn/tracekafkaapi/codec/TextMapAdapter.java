package com.github.youyinnn.tracekafkaapi.codec;

import io.opentracing.propagation.TextMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author youyinnn
 * Date 5/19/2019
 */
public class TextMapAdapter implements TextMap {

    private HashMap<String, String> map;

    public TextMapAdapter(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }
}
