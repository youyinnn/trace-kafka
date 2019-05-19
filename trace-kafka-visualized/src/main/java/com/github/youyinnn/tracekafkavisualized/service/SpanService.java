package com.github.youyinnn.tracekafkavisualized.service;

import com.github.youyinnn.tracekafkavisualized.dao.LogDao;
import com.github.youyinnn.tracekafkavisualized.dao.SpanDao;
import com.github.youyinnn.tracekafkavisualized.model.Log;
import com.github.youyinnn.tracekafkavisualized.model.Span;
import com.github.youyinnn.youdbutils.ioc.annotations.Autowired;
import com.github.youyinnn.youdbutils.ioc.annotations.YouService;

import java.util.HashMap;
import java.util.List;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@YouService(dataSourceName = "trace")
public class SpanService {

    @Autowired
    private SpanDao spanDao;

    @Autowired
    private LogDao logDao;


    public int createSpanAsNewTrace(Span span) {

        return 0;
    }

    public int createSpanWithInATrace(Span span, String traceId) {

        return 0;
    }

    public int createSpanAsChild(Span span, String parentSpanId) {

        return 0;
    }

    public List<Span> searchSpansByTraceId(String traceId) {

        return null;
    }

    public List<Span> searchSpansByServiceNameInFuzzy(String serviceName) {

        return null;
    }

    public List<Span> searchSpansByOperationNameInFuzzy(String operationName) {

        return null;
    }

    public List<Span> searchSpanByTagsKeyAndValueInFuzzy(HashMap<String, String> keyValueMap) {

        return null;
    }

    public List<Span> searchSpanBeforeTime(long startTime) {

        return null;
    }

    public int createLog(Log log, String spanId) {

        return 0;
    }
}
