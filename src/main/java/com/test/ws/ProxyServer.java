package com.test.ws;

public class ProxyServer extends Server {

    public ProxyServer(int port) {
        super(port);
    }

    @Override
    protected String handleRequest(String request) {
        return "HTTP/1.1 200 OK\r\n\r\nHi!";
    }
}
