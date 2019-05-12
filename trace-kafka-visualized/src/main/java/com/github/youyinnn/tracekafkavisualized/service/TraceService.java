package com.github.youyinnn.tracekafkavisualized.service;

import com.github.youyinnn.tracekafkavisualized.dao.TraceDao;
import com.github.youyinnn.youdbutils.ioc.annotations.Autowired;
import com.github.youyinnn.youdbutils.ioc.annotations.YouService;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@YouService(dataSourceName = "trace")
public class TraceService {

    @Autowired
    private TraceDao traceDao;
}
