package org.ce.ap.com.company.client;

import com.fxcode.twitterjavafx.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import java.io.IOException;
public class ConsoleViewService extends Application{

    public String FXMLAddress;

    public void setFXMLAddress(String address) {
        FXMLAddress = address;
    }

    public String getFXMLAddress() {
        System.out.println(FXMLAddress+"ll");
        return FXMLAddress;
    }

    public void start(Stage stage) throws IOException {
        if(FXMLAddress.equals("FirstMenu.fxml")){
            System.out.println("success");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FirstMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }
    }

    public String consoleInputPrint(InputStream input, OutputStream output) {
        try {
            while (true) {

                byte[] bufferMenu = new byte[50000];
                int transfer = input.read(bufferMenu);

                if(transfer!=-1){
                    String Address = new String(bufferMenu, 0, transfer);
                    setFXMLAddress(Address);
                    launch();
                }
            }
        }catch (Exception error) {
            System.out.println();
        }

        return getFXMLAddress();

    }

}
