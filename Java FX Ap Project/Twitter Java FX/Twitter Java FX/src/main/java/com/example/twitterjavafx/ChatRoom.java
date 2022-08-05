package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.ce.ap.com.company.server.service.ClientFileHandler;

/**
 * chatroom class for handling chats
 * @author ABTIN ZANDI
 */
public class ChatRoom extends ChatRoomFile {

    ArrayList<String> Title = new ArrayList<>(); // ChatRoom Titles
    ClientFileHandler clientFileHandler = new ClientFileHandler();

    //ChatRoom
    @FXML
    private Button ExitChat;
    @FXML
    private Text GroupName;
    @FXML
    private TextField NewMassage;
    @FXML
    private Button Refresh;
    @FXML
    private Button SendMassage;
    @FXML
    private BorderPane massges;
    @FXML
    private ScrollBar scroll;
    @FXML
    private Text MsgText;
    @FXML
    private Text NewMassageWarning;

    //Create New Chat
    @FXML
    private Button CreateButton;
    @FXML
    private Text CreateWarning;
    @FXML
    private TextField NewChatRoomName;

    //JoinChat
    @FXML
    private Button AllChatButton;
    @FXML
    private Button SearchCreateButton;
    @FXML
    private Button SearchButton;
    @FXML
    private TextField SearchChatRoomName;
    @FXML
    private Text SearchChatRoomNameWarning;

    private ArrayList<BorderPane> Massages = new ArrayList<>();

    //Join Chat

    /***
     * this method will help us to join in chat rooms
     * @param actionEvent ,
     */
    public void JoinEvent(ActionEvent actionEvent) {

        if(!SearchChatroom(SearchChatRoomName.getText())){
            SearchChatRoomNameWarning.setText("Chat room("+SearchChatRoomName.getText()+") isnt already exist");
        }
        else{
            int clientNumber = clientFileHandler.getFxmlState("JoinChatRoom");
            clientFileHandler.updateClient(clientNumber,"ChatRoom");
            clientFileHandler.updateServerFXML(clientNumber,"JoinChatRoom",SearchChatRoomName.getText());
        }
    }

    /**
     * this method will help us to control (JoinChat --> create )
     * @param actionEvent ,
     */
    public void SearchCreateButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("JoinChatRoom");
        clientFileHandler.updateClient(clientNumber,"CreatNewChat");
    }

    /**
     * this method will help us to manage sideMenu in Join ChatRoom
     * @param actionEvent ,
     */
    public void sideMenuButtonJoinChat(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("ChatRoom");
        clientFileHandler.updateClient(clientNumber,"JoinChatRoom");
    }

    //caht room

    /**
     * THIS METHOD WILL REFRESH OUR MASSAGES IN CHAT ROOM FXML
     * @param actionEvent ,
     */
    public void RefreshButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("ChatRoom");
        ArrayList<String> ChatName = clientFileHandler.getFXMLDetails(clientNumber,"ChatRoom");
        GroupName.setText(ChatName.get(0));
        ArrayList<String> chats= new ArrayList<>();
        chats.addAll(chatDetails(ChatName.get(0)));
        String massagesText = "" ;
        for(String text : chats) {
            massagesText += text ;
        }

        MsgText.setText(massagesText);

    }

    /**
     * this method will help us to manage exit of chatRoom
     * @param actionEvent ,
     */
    public void ExitChatButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("ChatRoom");
        clientFileHandler.updateClient(clientNumber,"ChatRoomMenu");
    }

    /**
     * this method will help us to manage sideMenu in ChatRoom
     * @param actionEvent ,
     */
    public void sideMenuButtonChatRoom(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("ChatRoom");
        clientFileHandler.updateClient(clientNumber,"sideMenu");
    }

    /**
     * this method will help us to manage send message button
     * @param actionEvent ,
     */
    public void SendMassageButton(ActionEvent actionEvent) {
        String NewMSG = NewMassage.getText();
        String Group = GroupName.getText();
        if(NewMSG.isBlank()){
            NewMassageWarning.setText("Error : Massage is Empty");
        }
        if(Group.isBlank()){
            NewMassageWarning.setText("Error : Please Press Refresh Button");
        }
        if(!Group.isBlank() && !NewMSG.isBlank()){
            int client =  clientFileHandler.getFxmlState("ChatRoom");
            UpdateChatRoom(clientFileHandler.getUserAccount(client)+": " + NewMSG,Group);
        }
    }

    public void AllChatsEvent(ActionEvent actionEvent) {

    }

    //create chat

    /**
     * this method will help us to Create a chat
     * @param actionEvent ,
     */
    public void CreateEvent(ActionEvent actionEvent) {
        if(SearchChatroom(NewChatRoomName.getText())){
            CreateWarning.setText("Chat room("+NewChatRoomName.getText()+") is already exist");
        }
        else{
            chatBuilder(NewChatRoomName.getText());
            CreateWarning.setText("Chat room("+NewChatRoomName.getText()+") has ");
        }
    }

    /**
     * this method will help us to manage sideMenu in Create ChatRoom
     * @param actionEvent
     */
    public void sideMenuButtonCreate(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("CreatNewChat");
        clientFileHandler.updateClient(clientNumber,"JoinChatRoom");
    }

    /**
     * this method will help us to show all titles
     */
    public void getTitles() {
        String showAllTitles = "";
        for(String title : Title){
            showAllTitles += title+"\n";
        }
    }

    public void setTitle(String title) {
        Title.add(title);
    }

    /**
     * this method will add a new chat room to server
     * @param title,
     */
    public void chatBuilder(String title){
        NewChatRoom(title);
        setTitle(title);
    }

}