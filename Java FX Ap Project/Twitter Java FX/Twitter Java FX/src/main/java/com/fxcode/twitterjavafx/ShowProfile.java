package com.fxcode.twitterjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowProfile implements Initializable {
    @FXML private Label userNameField;
    @FXML private Label nameField;
    @FXML private Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        circle.setStroke(Color.SEAGREEN);
        Image image;
    }
}
