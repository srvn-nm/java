package com.example.twitterjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientFileHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowProfile implements Initializable {
    @FXML public AnchorPane anchorPane;
    @FXML public Label name;
    @FXML public javafx.scene.shape.Circle Circle;
    @FXML public Label bio;
    @FXML public Label username;
    @FXML public ScrollPane scrollPane;
    public ClientFileHandler clientFileHandler = new ClientFileHandler();
    public String userName;
    public AccountFile accountFile = new AccountFile();
    public Account user = new Account("","","","");
    @FXML public Label tweets;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int clientNumber = clientFileHandler.getFxmlState("ShowProfile");
        userName = clientFileHandler.getUserAccount(clientNumber);
        ArrayList<Account> accounts = accountFile.AllUsers();
        for (Account a:accounts) {
            if (a.getUserName().equals(userName)){
                user = a;
            }
        }
        Circle.setStroke(Color.SEAGREEN);
        Image im = new Image(user.getPictureUrl(),false);
        Circle.setFill(new ImagePattern(im));
        Circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        String massagesText = "" ;
        for(Tweet t : user.tweets) {
            massagesText += t.toString()+"\r\n" ;
        }

        tweets.setText(massagesText);
        name.setText(user.getName());
        bio.setText(user.getBio());


    }
}
