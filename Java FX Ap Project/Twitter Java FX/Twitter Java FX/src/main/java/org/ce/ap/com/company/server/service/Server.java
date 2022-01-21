package org.ce.ap.com.company.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Server.
 */
public class Server {

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ExecutorService pool = Executors.newCachedThreadPool();
        int count = 0;

        try (ServerSocket welcomingSocket = new ServerSocket(8080)) {
            //our socket server should be accepted from clients ...
            System.out.println("Server started.\nWaiting for a client ... ");
            while (count < 1000) {
                Socket connectionSocket = welcomingSocket.accept();
                count++;
                System.out.println("client accepted!");
                //out put st --> server to  save
                OutputStream out = connectionSocket.getOutputStream();
                //input string from our user --> saved massage
                InputStream in = connectionSocket.getInputStream();
                ClientHandler handler = new ClientHandler(connectionSocket, count , in,out);
                pool.execute(handler);

            }
            pool.shutdown();
            System.out.print("done.\nClosing server ... ");
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println("done.");
    }
}