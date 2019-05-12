package com.github.youyinnn.tracekafkaapi.propagation;

import com.github.youyinnn.tracekafkaapi.api.YouSpanContext;

/**
 * @author youyinnn
 * Date 4/30/2019
 */
public interface Extractor<T> {

    YouSpanContext extract(T carrier);
}
