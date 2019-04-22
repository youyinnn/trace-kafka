package com.github.youyinnn.tracekafkasample.controllerservicemock;

import com.github.youyinnn.tracekafkasample.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author youyinnn
 * Date 4/22/2019
 */
@Component
public class ControllerMock implements ControllerInterface {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ControllerProducer producer;

    @Override
    public void requestHandler() {
        Scanner in = new Scanner(System.in);
        System.out.println("Controller Mock Interface, enter:");
        System.out.println("1.save data");
        System.out.println("2.update data");
        System.out.println("3.search data");
        System.out.println("4.delete data");
        System.out.println("5.exit");
        int s = in.nextInt();
        RandomUtils.latencyMock(10);
        switch (s) {
            case 1:
                producer.send("1");
                break;
            case 2:
                producer.send("2");
                break;
            case 3:
                producer.send("3");
                break;
            case 4:
                producer.send("4");
                break;
            case 5:
                SpringApplication.exit(applicationContext, () -> 0);
                break;
            default:
                break;
        }
    }
}
