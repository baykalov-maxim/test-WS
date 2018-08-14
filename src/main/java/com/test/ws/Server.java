package com.test.ws;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {
    private ServerSocket serverSocket;
    private boolean status = false;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void start() {
        status = true;
        while (status) {
            System.out.println("Wait client");

            try (Socket client = serverSocket.accept()) {
                System.out.println("Client accepted");
                handle(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        status = false;
    }

    private void handle(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String request = getRequest(in);
            String response = handleRequest(request);
            sendMessage(out, response);

            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String handleRequest(String request);

    private String getRequest(BufferedReader in) throws IOException {
        StringBuilder request = new StringBuilder();
        String buff;
        while ((buff = in.readLine()) != null) {
            request.append(buff).append("\n");
            if (buff.isEmpty())
                break;
        }

        System.out.println("Server has got message:\n" + request);
        return request.toString();
    }

    private void sendMessage(BufferedWriter out, String msg) throws IOException {
        System.out.println("Sending message: " + msg);
        out.write(msg);
        out.flush();
    }
}
