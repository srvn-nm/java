package org.ce.ap.com.company.Client;

import com.fxcode.twitterjavafx.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import java.io.IOException;
public class ConsoleViewService extends Application{

    public static String FXMLAddress;
    public static String NextStage;
    public static OutputStream outputStream;

    public void setFXMLAddress(String address) {
        FXMLAddress = address;
    }

    public String getFXMLAddress() {
        System.out.println(FXMLAddress+"ll");
        return FXMLAddress;
    }

    public static void setNextStage(String nextStage) {
        NextStage = nextStage;
    }

    public static String getNextStage() {
        return NextStage;
    }

    public static void setOutputStream(OutputStream output) {
        outputStream = output;
    }

    public void start(Stage stage) throws IOException {

        if(FXMLAddress.equals("FirstMenu.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FirstMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }
        else if(FXMLAddress.equals("SignUpFirst.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUpFirst.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("ChatRoom.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatRoom.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("ChatRoomMenu.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatRoomMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("ChatRoomChat.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatRoomChat.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("JoinChatRoom.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("JoinChatRoom.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("Massage.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Massage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("Search.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Search.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("ShowProfile.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShowProfile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }


        else if(FXMLAddress.equals("SignUpThird.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUpThird.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }

        else if(FXMLAddress.equals("SignUpSecond.fxml")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUpSecond.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
        }


    }

    @Override
    public void stop() throws Exception {
        try {
            outputStream.write(NextStage.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.stop();
    }

    /**
     * this method will help us to manage server inputs
     * @param input ,
     * @param output ,
     */
    public void consoleInputPrint(InputStream input, OutputStream output) {
        setOutputStream(output);
        try {
            byte[] bufferMenu = new byte[50000];
            int transfer = input.read(bufferMenu);
            if(transfer!=-1){
                String Address = new String(bufferMenu, 0, transfer);
                setFXMLAddress(Address);
                launch();
            }
        }catch (Exception error) {
            System.out.println();
        }
    }

}