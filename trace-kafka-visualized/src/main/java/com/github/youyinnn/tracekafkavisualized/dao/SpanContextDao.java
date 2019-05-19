package com.github.youyinnn.tracekafkavisualized.dao;

import com.github.youyinnn.tracekafkavisualized.model.SpanContext;
import com.github.youyinnn.youdbutils.dao.YouDao;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youwebutils.third.YouCollectionsUtils;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class SpanContextDao extends YouDao<SpanContext> {

    public int addSpanContext(SpanContext spanContext) throws NoneffectiveUpdateExecuteException {
        return modelHandler.saveModel(spanContext);
    }

    public List<SpanContext> getSpanContextsByTraceId(String traceId) {
        return modelHandler.getListWhere(
                YouCollectionsUtils.getYouHashMap("traceId", traceId),
                YouCollectionsUtils.getYouArrayList(
                        "spanId",
                        "spanContextId",
                        "traceId",
                        "referenceJson"),
                "AND");
    }

}
