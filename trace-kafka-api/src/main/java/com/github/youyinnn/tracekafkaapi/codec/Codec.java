package com.github.youyinnn.tracekafkaapi.codec;

import com.github.youyinnn.tracekafkaapi.YouSpanContext;

/**
 * @author youyinnn
 * Date 4/27/2019
 */
public interface Codec<T> {

    void inject(YouSpanContext context, T carrier);

    YouSpanContext extract(T carrier);
}
