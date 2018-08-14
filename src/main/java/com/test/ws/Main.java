package com.test.ws;

import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    static java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));

    static OkHttpClient client = new OkHttpClient.Builder()
            .proxy(proxy)
            .build();

    public static void main(String[] args) {
        new Thread(() -> new Proxy(8080).start()).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .url("http://google.com.ua")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
