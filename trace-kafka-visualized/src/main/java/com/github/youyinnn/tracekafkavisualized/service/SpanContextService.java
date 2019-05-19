package com.github.youyinnn.tracekafkavisualized.service;

import com.github.youyinnn.tracekafkavisualized.dao.BaggageDao;
import com.github.youyinnn.tracekafkavisualized.dao.SpanContextDao;
import com.github.youyinnn.tracekafkavisualized.model.Baggage;
import com.github.youyinnn.tracekafkavisualized.model.SpanContext;
import com.github.youyinnn.youdbutils.ioc.annotations.Autowired;
import com.github.youyinnn.youdbutils.ioc.annotations.YouService;

import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@YouService(dataSourceName = "trace")
public class SpanContextService {

    @Autowired
    private SpanContextDao spanContextDao;

    @Autowired
    private BaggageDao baggageDao;

    public int createSpanContext(SpanContext spanContext) {

        return 0;
    }

    public int createBaggage(Baggage baggage) {

        return 0;
    }

    public List<SpanContext> searchSpanContextsByTraceId(String traceId) {

        return null;
    }
}
