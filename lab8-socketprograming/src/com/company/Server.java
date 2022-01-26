package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket welcomingSocket = new ServerSocket(7657)) {
            //our socket server should be accepted from clients ...
            System.out.print("Server started.\nWaiting for a client ... ");
            try (Socket connectionSocket = welcomingSocket.accept()) {
                System.out.println("client accepted!");
                //out put st --> server to  save
                OutputStream out = connectionSocket.getOutputStream();
                //input string from our user --> saved massage
                InputStream in = connectionSocket.getInputStream();
                byte[] buffer = new byte[2048];
                ArrayList<String> savedLine = new ArrayList<String>();
                int index = 0;
                while (true) {
                    int read = in.read(buffer);
                    String n = new String(buffer, 0, read);
                    System.out.println("RECV: " + n);
                    savedLine.add(n);
                    out.write(savedLine.get(index).getBytes());
                    System.out.println("SENT: " + savedLine);
                    if (savedLine.get(index).equals("Over"))break;
                    index++;
                }
                System.out.print("All messages sent.\nClosing client ... ");
            } catch (IOException error) {
                error.printStackTrace();
            }
            System.out.print("done.\nClosing server ... ");
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println("done.");
    }
}
