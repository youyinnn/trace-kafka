package com.github.youyinnn.tracekafkasample.ormservicemock;

import io.opentracing.Span;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
public interface OrmInterface {

    int save(Span parent);

    int update(Span parent);

    String search(Span parent);

    int delete(Span parent);

}
