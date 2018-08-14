package com.test.ws;

public class Proxy extends Server {

    public Proxy(int port) {
        super(port);
    }

    @Override
    protected String handleRequest(String request) {
        return "HTTP/1.1 200 OK\r\n\r\nHi!";
    }
}
