package com.github.youyinnn.tracekafkaapi.reporter;

import com.alibaba.fastjson.JSON;
import com.github.youyinnn.tracekafkaapi.utils.CodecStringUtil;
import io.opentracing.Span;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author youyinnn
 * Date 5/9/2019
 */
public class HttpReporter implements Reporter {

    private static String serverHost;
    private static int port;

    private final OkHttpClient client =
            new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).build();

    public static void setEndpoint(String host, String port) {
        HttpReporter.serverHost = host;
        HttpReporter.port = Integer.parseInt(port);
    }

    @Override
    public void report(Span span) {
        String jsonString = JSON.toJSONString(span);
        String token = CodecStringUtil.generateToken();
        String encryptMessage = CodecStringUtil.encryptMessage(jsonString);
        System.out.println(CodecStringUtil.verifyToken(token));
        System.out.println(CodecStringUtil.decryptMessage(encryptMessage));
        Request request = new Request.Builder()
                .url("http://" + serverHost + ":" +port + "/report")
                .get()
                .header("t", token)
                .header("r", encryptMessage)
                .build();

        try {
            Response execute = client.newCall(request).execute();
            if (execute.body() != null) {
                System.out.println(execute.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
