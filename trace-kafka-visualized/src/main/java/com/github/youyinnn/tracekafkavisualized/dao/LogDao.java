package com.github.youyinnn.tracekafkavisualized.dao;

import com.github.youyinnn.tracekafkavisualized.model.Log;
import com.github.youyinnn.youdbutils.dao.YouDao;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youwebutils.third.YouCollectionsUtils;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class LogDao extends YouDao<Log> {

    public int addLog(Log log) throws NoneffectiveUpdateExecuteException {
        return modelHandler.saveModel(log);
    }

    public List<Log> getLogsBySpanId(String spanId) {
        return modelHandler.getListWhere(
                YouCollectionsUtils.getYouHashMap("spanId", spanId),
                YouCollectionsUtils.getYouArrayList("logId", "spanId", "key", "value"),
                "AND"
        );
    }
}
