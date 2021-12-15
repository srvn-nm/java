package com.company;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

class ClientHandler implements Runnable {

    private final Socket connectionSocket;
    private final int clientNum;

    public ClientHandler(Socket connectionSocket, int clientNum) {
        this.connectionSocket = connectionSocket;
        this.clientNum=clientNum;
    }

    @Override
    public void run() {
        try {
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
                System.out.println("RECV from "+clientNum+": " + n);
                savedLine.add(n);
                out.write(savedLine.get(index).getBytes());
                System.out.println("SENT to "+clientNum+": " + savedLine);
                if (savedLine.get(index).equals("Over"))break;
                index++;
                Thread.sleep(2000);
            }
            System.out.print("All messages sent.\nClosing client ... ");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionSocket.close();
            } catch (IOException error) {
                System.err.println(error);
            }
        }
    }
}