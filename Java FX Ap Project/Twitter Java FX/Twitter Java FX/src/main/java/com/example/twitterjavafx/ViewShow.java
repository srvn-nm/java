package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.ce.ap.com.company.server.service.ClientFileHandler;

public class ViewShow {
    @FXML
    public Button Full;
    public ClientFileHandler clientFileHandler = new ClientFileHandler();

    public void Toggle(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("ViewShow");
        clientFileHandler.updateServerFXML(clientNumber,"ViewShow","True");
    }
}
