package com.github.youyinnn.tracekafkaapi.autoconfigure;

import com.github.youyinnn.tracekafkaapi.utils.CodecStringUtil;
import com.github.youyinnn.tracekafkaapi.utils.YouProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@Component
public class Autoconfigure implements BeanPostProcessor {

    @Autowired
    private YouProperties properties;

    @PostConstruct
    public void setup() {
        CodecStringUtil.setKey(properties.getKeyOne(), properties.getKeyTwo());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
