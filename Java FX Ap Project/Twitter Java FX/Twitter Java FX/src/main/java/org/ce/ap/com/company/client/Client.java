package org.ce.ap.com.company.client;

import java.io.*;
import java.net.Socket;

/**
 * client class will help us to connect with server socket and our client can use Twitter Application  with this connection
 * @author Abtin zandi
 * @version 2.0  2021-12-20
 */

public class Client{

    public static ConsoleViewService viewService = new ConsoleViewService();
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

            String FXMLAddress = viewService.consoleInputPrint(inputMenu,outputs);

        }catch (Exception runTimeError){
            runTimeError.printStackTrace();
        }

    }
}