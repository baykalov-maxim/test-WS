package com.test.ws;

public class ProxyServer extends Server {

    public ProxyServer(int port) {
        super(port);
    }

    @Override
    protected String handleRequest(String request) {
        return "HTTP/1.1 200 OK\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Headers: *\r\n\r\nHi!";
    }
}
