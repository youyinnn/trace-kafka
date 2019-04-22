package com.github.youyinnn.tracekafkasample.ormservicemock;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
public interface OrmInterface {

    int save();

    int update();

    String search();

    int delete();

}
