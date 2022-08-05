package com.example.twitterjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ce.ap.com.company.server.service.ClientFileHandler;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUpSecond.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ClientFileHandler clientStatusHandler = new ClientFileHandler();
        int clientNumber = clientStatusHandler.getFxmlState("ApplicationShow");
        if (clientStatusHandler.getFXMLDetails(clientNumber,"ApplicationShow").contains("True"))
            stage.setFullScreen(true);
        stage.setTitle("Twitter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}