package org.ce.ap.com.company.Client;

import com.example.twitterjavafx.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ce.ap.com.company.server.service.ClientFileHandler;
import java.io.*;
import java.io.IOException;
public class ConsoleViewService extends Application{

    public static String FXMLAddress;
    private static String NextStage;
    private static OutputStream outputStream;
    private static ClientFileHandler clientFileHandler = new ClientFileHandler();
    private int ClientNumber = 0 ;
    public void setFXMLAddress(String address) {
        FXMLAddress = address;
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

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FXMLAddress));
            System.out.println(FXMLAddress);
            System.out.println(fxmlLoader.getLocation());
            Scene scene = new Scene(fxmlLoader.load());
           // Scene scene = new Scene(fxmlLoader.(getClass().getResource("/com/companyname/reports/" + report.getClass().getCanonicalName().substring(18).replaceAll("Controller", "") + ".fxml")));
            System.out.println("2");
            stage.setTitle("Twitter");
            stage.setScene(scene);
            stage.show();
            System.out.println("1");

        }catch (Exception error){
            error.printStackTrace();
        }

    }

    @Override
    public void stop() throws Exception {
        try {
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
    public void consoleInputPrint(InputStream input, OutputStream output,int number) {
        setOutputStream(output);
        try {
            byte[] bufferMenu = new byte[50000];
            ClientNumber = number;
            int transfer = input.read(bufferMenu);
            if(transfer!=-1){
                String Address = new String(bufferMenu, 0, transfer);
                Address = Address + ".fxml";
                setFXMLAddress(Address);
                System.out.println(Address);
                clientFileHandler.setAllFxmlsStats(FXMLAddress,ClientNumber);
                launch();
                String NewFxmlStatus  = clientFileHandler.getStatus(ClientNumber);
                output.write(NewFxmlStatus.getBytes());
            }
        }catch (Exception error) {
            System.out.println();
        }
    }

}