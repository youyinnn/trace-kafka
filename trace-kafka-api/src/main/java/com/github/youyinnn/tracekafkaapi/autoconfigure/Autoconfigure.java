package com.github.youyinnn.tracekafkaapi.autoconfigure;

import com.github.youyinnn.tracekafkaapi.reporter.HttpReporter;
import com.github.youyinnn.tracekafkaapi.utils.CodecStringUtil;
import com.github.youyinnn.tracekafkaapi.utils.YouProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@Configuration
@EnableConfigurationProperties(YouProperties.class)
public class Autoconfigure implements BeanPostProcessor {

    @Autowired
    private YouProperties prop;

    @PostConstruct
    public void setup() {
        CodecStringUtil.setKey(prop.getValidKeyOne(), prop.getValidKeyTwo());
        if (prop.getReportHttpHost() != null && prop.getReportHttpPort() != null) {
            HttpReporter.setEndpoint(prop.getReportHttpHost(), prop.getReportHttpPort());
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
