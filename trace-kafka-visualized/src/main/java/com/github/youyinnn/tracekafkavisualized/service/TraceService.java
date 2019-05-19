package com.github.youyinnn.tracekafkavisualized.service;

import com.github.youyinnn.tracekafkavisualized.dao.TraceDao;
import com.github.youyinnn.tracekafkavisualized.model.Trace;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youdbutils.ioc.annotations.Autowired;
import com.github.youyinnn.youdbutils.ioc.annotations.YouService;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@YouService(dataSourceName = "trace")
public class TraceService {

    @Autowired
    private TraceDao traceDao;

    public List<Trace> searchTraceByServiceNameInFuzzy(String serviceName) {
        return traceDao.getTracesByServiceNameInFuzzy(serviceName);
    }

    public int createTrace(String traceId, String serviceName) {
        int flag = -1;
        try {
            flag = traceDao.addTrace(new Trace(traceId, serviceName));
        } catch (NoneffectiveUpdateExecuteException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
