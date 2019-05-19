package com.github.youyinnn.tracekafkavisualized.dao;

import com.github.youyinnn.tracekafkavisualized.model.Span;
import com.github.youyinnn.youdbutils.dao.YouDao;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youwebutils.third.YouCollectionsUtils;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class SpanDao extends YouDao<Span> {

    public int addSpan(Span span) throws NoneffectiveUpdateExecuteException {
        return modelHandler.saveModel(span);
    }

    public List<Span> getSpansByTraceId(String traceId) {
        return modelHandler.getListWhere(
                YouCollectionsUtils.getYouHashMap("traceId", traceId),
                YouCollectionsUtils.getYouArrayList(
                        "spanId",
                        "operationName",
                        "traceId",
                        "startTime",
                        "finishTime",
                        "tagKeyValueJson"),
                "AND");
    }


}
