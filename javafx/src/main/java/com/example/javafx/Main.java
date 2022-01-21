package com.example.javafx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("hello");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("goodbye");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("calc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("calculator");
            primaryStage.show();
        } catch (IOException E) {
            E.printStackTrace();
        }
    }


    public static void main(String[] args) {

        launch(args);
    }
}
