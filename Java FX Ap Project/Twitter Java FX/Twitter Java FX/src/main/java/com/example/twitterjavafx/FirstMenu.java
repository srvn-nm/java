package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.ce.ap.com.company.server.service.ClientFileHandler;

/**
 * first menu handler
 */
public class FirstMenu{

    @FXML
    private Button LogIn;

    @FXML
    private VBox LogInWindow;

    @FXML
    private Button SignUp;

    @FXML
    private Label welcomeText;
    private ClientFileHandler clientStatusHandler = new ClientFileHandler();
    int clientNumber = clientStatusHandler.getCurrentClient();

    /**
     * this method will set Log In and SignUp Menu
     * @param actionEvent ,
     */
    public void LogInCheck(ActionEvent actionEvent) {
        clientStatusHandler.updateServerFXML(clientNumber,"FirstMenu","LogIn.fxml");
    }

    /**
     * this method will set Log In and SignUp Menu
     * @param actionEvent ,
     */
    public void SignUpCheck(ActionEvent actionEvent) {
        clientStatusHandler.updateServerFXML(clientNumber,"FirstMenu","SignUpSecond.fxml");
    }

}