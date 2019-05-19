package com.github.youyinnn.tracekafkaapi.codec;

import com.github.youyinnn.tracekafkaapi.propagation.Extractor;
import com.github.youyinnn.tracekafkaapi.propagation.Injector;

/**
 * @author youyinnn
 * Date 4/27/2019
 */
public interface Codec<T>  extends Injector<T>, Extractor<T> {

}
