package com.github.youyinnn.tracekafkavisualized.dao;

import com.github.youyinnn.tracekafkavisualized.model.Baggage;
import com.github.youyinnn.youdbutils.dao.YouDao;
import com.github.youyinnn.youdbutils.exceptions.NoneffectiveUpdateExecuteException;
import com.github.youyinnn.youwebutils.third.YouCollectionsUtils;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class BaggageDao extends YouDao<Baggage> {

    public int addBaggage(Baggage baggage) throws NoneffectiveUpdateExecuteException {
        return modelHandler.saveModel(baggage);
    }

    public List<Baggage> getBaggagsBySpanContextId(String spanContextId) {
        return modelHandler.getListWhere(
                YouCollectionsUtils.getYouHashMap("spanContextId", spanContextId),
                YouCollectionsUtils.getYouArrayList("baggageId","spanContextId", "key", "value"),
                "AND"
        );
    }
}
