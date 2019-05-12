package com.github.youyinnn.tracekafkavisualized;

import com.github.youyinnn.youdbutils.YouDbManager;
import com.github.youyinnn.youdbutils.druid.YouDruid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraceKafkaVisualizedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TraceKafkaVisualizedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        YouDbManager.signInYouDruid(
                YouDruid.initMySQLDataSource("application.properties", "trace", true));
        YouDbManager.scanPackageForModelAndService(
                "com.github.youyinnn.tracekafkavisualized.model",
                "com.github.youyinnn.tracekafkavisualized.service",
                "trace");
    }
}
