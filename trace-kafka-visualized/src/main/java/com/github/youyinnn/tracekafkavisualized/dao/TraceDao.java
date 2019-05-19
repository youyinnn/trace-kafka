package com.github.youyinnn.tracekafkavisualized.dao;

import com.github.youyinnn.tracekafkavisualized.model.Trace;
import com.github.youyinnn.youdbutils.dao.YouDao;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youwebutils.third.YouCollectionsUtils;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class TraceDao extends YouDao<Trace> {

    public int addTrace(Trace trace) throws NoneffectiveUpdateExecuteException {
        return modelHandler.saveModel(trace);
    }

    public List<Trace> getTraces() {
        return modelHandler.getListForAll(YouCollectionsUtils.getYouArrayList("traceId", "serviceName"));
    }
}
