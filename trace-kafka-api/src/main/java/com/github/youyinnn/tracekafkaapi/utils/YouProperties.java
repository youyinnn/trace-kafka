package com.github.youyinnn.tracekafkaapi.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author youyinnn
 * Date 5/12/2019
 */
@Configuration
//@PropertySource("classpath:my.properties")
@ConfigurationProperties(prefix = "valid")
public class YouProperties {

    private String keyOne;
    private String keyTwo;

    public String getKeyOne() {
        return keyOne;
    }

    public void setKeyOne(String keyOne) {
        this.keyOne = keyOne;
    }

    public String getKeyTwo() {
        return keyTwo;
    }

    public void setKeyTwo(String keyTwo) {
        this.keyTwo = keyTwo;
    }

    @Override
    public String toString() {
        return "YouProperties{" +
                "keyOne='" + keyOne + '\'' +
                ", keyTwo='" + keyTwo + '\'' +
                '}';
    }
}
