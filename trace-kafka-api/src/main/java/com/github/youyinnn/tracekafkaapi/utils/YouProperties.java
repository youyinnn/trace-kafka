package com.github.youyinnn.tracekafkaapi.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@ConfigurationProperties(prefix = "tracer")
public class YouProperties {

    private String validKeyOne;
    private String validKeyTwo;
    private String reportHttpHost;
    private String reportHttpPort;

    public String getValidKeyOne() {
        return validKeyOne;
    }

    public YouProperties setValidKeyOne(String validKeyOne) {
        this.validKeyOne = validKeyOne;
        return this;
    }

    public String getValidKeyTwo() {
        return validKeyTwo;
    }

    public YouProperties setValidKeyTwo(String validKeyTwo) {
        this.validKeyTwo = validKeyTwo;
        return this;
    }

    public String getReportHttpHost() {
        return reportHttpHost;
    }

    public YouProperties setReportHttpHost(String reportHttpHost) {
        this.reportHttpHost = reportHttpHost;
        return this;
    }

    public String getReportHttpPort() {
        return reportHttpPort;
    }

    public YouProperties setReportHttpPort(String reportHttpPort) {
        this.reportHttpPort = reportHttpPort;
        return this;
    }
}
