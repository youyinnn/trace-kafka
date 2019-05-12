package com.github.youyinnn.tracekafkaapi.reporter;

import com.github.youyinnn.tracekafkaapi.api.YouSpan;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author youyinnn
 * Date 5/9/2019
 */
public class HttpReporter implements Reporter {

    private String serverHost;
    private int port;
    private String token;

    private final OkHttpClient client =
            new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).build();

    @Override
    public void report(YouSpan span) {

    }

    @Override
    public void close() {

    }
}
