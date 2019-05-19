package com.github.youyinnn.tracekafkavisualized.model;

import com.github.youyinnn.tracekafkavisualized.util.IDGenerator;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
public class Trace {

    private String traceId;
    private String serviceName;

    public Trace() {
    }

    public Trace(String serviceName) {
        this.serviceName = serviceName;
        this.traceId = IDGenerator.getID();
    }

    public String getTraceId() {
        return traceId;
    }

    public Trace setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Trace setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "traceId='" + traceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
