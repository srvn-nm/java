package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        int count = 0;
        try (ServerSocket welcomingSocket = new ServerSocket(7657)) {
            //our socket server should be accepted from clients ...
            System.out.print("Server started.\nWaiting for a client ... ");
            while (count < 3) {
                Socket connectionSocket = welcomingSocket.accept();
                count++;
                System.out.println("client accepted!");
                pool.execute(new ClientHandler(connectionSocket, count));
            }
            pool.shutdown();
            System.out.print("done.\nClosing server ... ");
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println("done.");
    }
}