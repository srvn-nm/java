package org.ce.ap.com.company.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Connections implements Runnable {

    private final Socket connectionSocket;
    private final int clientNum;
    private InputStream in;
    private OutputStream out;



    public ClientHandler(Socket connectionSocket, int clientNum , InputStream in, OutputStream out) {

        super(connectionSocket,clientNum ,in,out);
        this.connectionSocket = connectionSocket;
        this.clientNum=clientNum;
        this.in=in;
        this.out=out;

    }


    @Override
    public void run() {
        try {

            byte[] buffer = new byte[2048];
            ArrayList<String> savedLine = new ArrayList<String>();
            int index = 0;

            Twitter service = new Twitter();

            while (true) {
                outputStream("FirstMenu.fxml");
                int read = in.read(buffer);
                String receivedMessage = new String(buffer, 0, read);

                System.out.println("RECV from "+clientNum+": " + receivedMessage);
                savedLine.add(receivedMessage);
                //out.write(savedLine.get(index).getBytes());
                System.out.println("SENT to "+clientNum+": " + savedLine);

                if(receivedMessage.equals("1")){
                    service.serverLogIn(this);
                }

                else if(receivedMessage.equals("2")){
                    service.newMember(this);
                }

                else if(receivedMessage.equals("You are not logged in. If you want you can re-login or register from the main menu\n--> Twitter")){
                    continue;
                }

                else{
                    String exit = "Over";
                    out.write(exit.getBytes());
                    break;
                }
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