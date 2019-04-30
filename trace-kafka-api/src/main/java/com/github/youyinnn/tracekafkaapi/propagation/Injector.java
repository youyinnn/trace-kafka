package com.github.youyinnn.tracekafkaapi.propagation;

import com.github.youyinnn.tracekafkaapi.YouSpanContext;

/**
 * @author youyinnn
 * Date 4/30/2019
 */
public interface Injector<T> {

    void inject(YouSpanContext context, T carrier);
}
