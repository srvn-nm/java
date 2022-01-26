package org.ce.ap.com.company.server.service;

import java.io.*;
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

    /**
     * this class will creat new Files for FXML details
     * @param clientNum ,
     */
    public void NewClient(int clientNum){
        NewFile("./src/main/resources/ServerClientFxml"+"FirstMenu-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"ChatRoom-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"ChatRoomMenu-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"CreatNewChat-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"JoinChatRoom-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"LogIn-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"Massage-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"Search-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"ShowProfile-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"SignUpFirst-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"SignUpSecond-"+clientNum+".txt");
        NewFile("./src/main/resources/ServerClientFxml"+"SignUpThird-"+clientNum+".txt");
    }

    /***
     * create file with path
     * @param path ,
     */
    public void NewFile(String path){
        File file = new File(path);
        try(FileWriter newFileWriter = new FileWriter(file,false)){
            newFileWriter.write("new client ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[2048];
            ArrayList<String> savedLine = new ArrayList<String>();
            int index = 0;
            Twitter service = new Twitter(clientNum);
            while (true) {

                service.mainController(this, clientNum);
                int read = in.read(buffer);
                String receivedMessage = new String(buffer, 0, read);

                System.out.println("RECV from "+clientNum+": " + receivedMessage);
                savedLine.add(receivedMessage);
                //out.write(savedLine.get(index).getBytes());
                System.out.println("SENT to "+clientNum+": " + savedLine);

                index++;
                Thread.sleep(2000);
                break;
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