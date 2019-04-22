package com.github.youyinnn.tracekafkasample;

import com.github.youyinnn.tracekafkasample.controllerservicemock.ControllerMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author youyinnn
 */
@SpringBootApplication
public class TraceKafkaSampleApplication implements CommandLineRunner {

    @Autowired
    private ControllerMock controller;

    public static void main(String[] args) {
        SpringApplication.run(TraceKafkaSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        controller.requestHandler();
    }
}
