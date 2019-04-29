package com.github.youyinnn.tracekafkaapi;

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