package org.ce.ap.com.company.server.service;


import org.ce.ap.com.company.server.impl.ConnectionService;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connections implements ConnectionService {

    private final Socket connectionSocket;
    private final int clientNum;
    private InputStream in;
    private OutputStream out;


    public Connections(Socket connectionSocket, int clientNum , InputStream in, OutputStream out) {
        this.connectionSocket = connectionSocket;
        this.clientNum=clientNum;
        this.in=in;
        this.out=out;
    }
    /**
     * this method will help us to use our classes and transfer details with users and client class
     * @return string for client side
     */
    public String inputStream(){
        String receivedMessage;
        try {
            byte[] buffer = new byte[2048];
            int read = in.read(buffer);
            receivedMessage = new String(buffer, 0, read);
        } catch (Exception e) {
            receivedMessage = "error";
            e.printStackTrace();
        }
        return receivedMessage;
    }

    public void outputStream(String outputs) {
        try {
            out.write(outputs.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
