package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.ce.ap.com.company.server.service.ClientFileHandler;

/**
 * this class will help us to
 * @version 1.0
 * @author SARVIN and ABTIN
 */
public class OptionsMenuButtons {

    ClientFileHandler clientFileHandler = new ClientFileHandler();

    @FXML
    private Button ChatRoom;

    @FXML
    private Button CreateTweet;

    @FXML
    private Button Help;

    @FXML
    private Button LogOut;

    @FXML
    private Button Search;

    @FXML
    private Button ShowProfile;

    @FXML
    private Button TimeLine;

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void ChatRoomAplicitaion(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"ChatRoom");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void CreateTweetAplication(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"CreateTweet");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void HelpAcction(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"Help");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void LogOutApliccation(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"LogOut");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void SearchAplication(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"Search");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void ShowProfileAplication(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"ShowProfile");
    }

    /**
     * THIS METHOD WILL HELP US TO MANAGE OptionsMenuButtons TRANSITION
     * @param event ,
     */
    @FXML
    void TimeLIneAplication(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("OptionsMenuButtons");
        clientFileHandler.updateClient(clientNumber,"TimeLine");
    }

}
