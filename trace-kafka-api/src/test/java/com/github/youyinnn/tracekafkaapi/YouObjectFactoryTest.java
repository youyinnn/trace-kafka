package com.github.youyinnn.tracekafkaapi;

import com.github.youyinnn.tracekafkaapi.api.YouObjectFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class YouObjectFactoryTest {

    @Test
    public void getInstance() {
        assertSame(YouObjectFactory.getInstance(), YouObjectFactory.getInstance());
    }

    @Test
    public void createSpan() {
    }

    @Test
    public void createContext() {
    }
}