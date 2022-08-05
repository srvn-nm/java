package org.ce.ap.com.company.Client;

import org.ce.ap.com.company.server.service.ClientFileHandler;
import java.io.*;
import java.net.Socket;

/**
 * client class will help us to connect with server socket and our client can use Twitter Application  with this connection
 * @author Abtin zandi
 * @version 2.0  2021-12-20
 */

public class Client{

    private static ConsoleViewService viewService = new ConsoleViewService();
    private static ClientFileHandler clientFileHandler = new ClientFileHandler();

    public static void main(String[] args) {
        try(Socket clientSocket = new Socket("127.0.0.1",8080)) {
            //writing setting properties
            File propertiesFile = new File("./src/resources/setting properties/SettingProperties.txt");

            try(FileWriter fileOutputStream = new FileWriter(propertiesFile,true)){
                String temp = "Socket Information : "+clientSocket.toString()+"\r\n";
                fileOutputStream.write(temp);
            } catch (IOException e) {
                System.out.println();
            }

            //server message to client --> Menu,tweets,search ...
            InputStream inputMenu = clientSocket.getInputStream();
            //out put choice --> menu server
            OutputStream outputs = clientSocket.getOutputStream();
            int ClientNumber = clientFileHandler.getCurrentClient();
            viewService.consoleInputPrint(inputMenu,outputs,ClientNumber);
//            while (true){
//                viewService.consoleInputPrint(inputMenu,outputs,ClientNumber);
//                String NewFxmlStatus  = clientFileHandler.getStatus(ClientNumber);
//                if(NewFxmlStatus.equals("LogOut")){
//                    break;
//                }
//                else{
//
//                }
//            }


        }catch (Exception runTimeError){
            runTimeError.printStackTrace();
        }

    }
}