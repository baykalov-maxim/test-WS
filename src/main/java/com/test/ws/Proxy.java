package com.test.ws;

import okhttp3.*;

public class Proxy extends Server {

    public Proxy(int port) {
        super(port);
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String handleRequest(String request) {
        Request.Builder builder = new Request.Builder().url("ws://echo.websocket.org");
        WebSocket ws = client.newWebSocket(builder.build(), new WsListener());
        ws.send("asd");

        return "";
    }

    private class WsListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            System.out.println(response.code());
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            System.out.println("Received message: " + text);
        }
    }
}
