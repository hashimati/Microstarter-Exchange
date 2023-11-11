package io.hashimati.services;

import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Singleton
public class SocketService {

    @Async
    public void sendString(String host, int port,  String content) {

        try {
            Socket socket = new Socket(host, port);
            if(socket.isConnected()){
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(content);
                printWriter.flush();
                printWriter.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Async
    public void sendBinary(String host, int port, byte[] content) {
        try {
            Socket socket = new Socket(host, port);
            if(socket.isConnected()){
                socket.getOutputStream().write(content);
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
