package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.utils.CodecStringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraceKafkaApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TraceKafkaApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(CodecStringUtil.verifyToken(CodecStringUtil.generateToken()));
    }
}
