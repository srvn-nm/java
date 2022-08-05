package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import org.ce.ap.com.company.server.impl.TwitterService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Errortype;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.model.Tweet;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.ce.ap.com.company.server.service.*;


/**
 * sever class will help us to manage and control all the twitter sections
 */
public class Twitter implements TwitterService {

    @FXML public Label welcomeText;
    @FXML public Button ExitApp;
    @FXML public Button logOut;
    private Tweeting tweeting;
    private TimeLine showingTable;
    private final Authentication service;//log in and sign up service
    private Observer browser;//searching service
    private ArrayList<Account> twitterUsers; //this array will save all sign up users
    private final AccountFile usersFileManger ;
    private final ChatRoom chatRoom;
    private org.ce.ap.com.company.server.service.requestJSONHandler requestJSONHandler = new requestJSONHandler();
    private org.ce.ap.com.company.server.service.responseJSONHandler responseJSONHandler = new responseJSONHandler();
    public int errorcode = 0;
    private final Setting setting;

    private ClientFileHandler clientStatusHandler ; //client status handler

    //Help menu
    @FXML public Button About;
    @FXML public AnchorPane AnchorH;
    @FXML public Button HelpH;
    @FXML public Label value;


    //first side menu
    @FXML public AnchorPane Anchor;
    @FXML public Button Application;
    @FXML public Button View;
    @FXML public Button Options;
    @FXML public Button Help;

    //option menu
    @FXML public Button Exit;
    @FXML public Button Tray;

    //ChatRoomFirstMenu
    @FXML
    private Button ChatRoomMenuExit;
    @FXML
    private Button JoinChat;
    @FXML
    private Button NewChat;


    //log in sign up menu
    @FXML
    private Button LogIn;
    @FXML
    private VBox LogInWindow;
    @FXML
    private Button SignUp;

    //client Number
    int clientNumber;




    /**
     *Server Constructor
     */
    public Twitter(int clientNumber,ClientHandler clientHandler){
        service = new Authentication();
        twitterUsers = new ArrayList<>();
        browser = new Observer(clientHandler);
        tweeting = new Tweeting(clientHandler);
        showingTable = new TimeLine();
        usersFileManger = new AccountFile();
        chatRoom = new ChatRoom();
        setting = new Setting();
        this.clientNumber = clientNumber ;
        clientStatusHandler = new ClientFileHandler();
    }

    /**
     * this main controller will help us to use FXML
     * @param handler ,
     * @param clientNumber ,
     */
    public void mainController(ClientHandler handler , int clientNumber){

        clientStatusHandler.newClient(clientNumber);
        while (true){
            //Client Number
            clientStatusHandler.CurrentClient(clientNumber);
            String Status = clientStatusHandler.getStatus(clientNumber);
            handler.outputStream(Status);
            String newStatus = handler.inputStream();
            clientStatusHandler.updateClient(clientNumber,newStatus);
        }
    }


    /**
     * this me
     * @param actionEvent
     */
    public void ChatRoomClick(ActionEvent actionEvent) {

    }


    /**
     * this method is the main part of program Where the user can choose what he/she wants to do
     * collaborating with new tweet , myFollowingSystem ,search , setting
     */
    public void mainMenu(Account user,ClientHandler handler){

        while (true) {
            updateUsers();
            usersFileManger.userUpdate(user);
            String deletedAccount = user.getName();
            if (deletedAccount.equals("del 1")) {
                usersFileManger.removeFile(user);
                break;
            } else {
                handler.outputStream("Welcome to twitter\n1)New tweet \n2)following users\n3)My page view\n4)my tweets\n5)search\n6)Setting\n7)my time line\n8)Chat Room\nEny Other Keyboard : log out");
                String choice = handler.inputStream();

                if (choice.equals("1")) {
                    handler.outputStream("Please type your tweet here:");
                    String text = handler.inputStream();
                    user = newTweet(user, text,handler);
                    usersFileManger.userUpdate(user);
                    updateUsers();
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    parameters.add("tweet text: "+text);
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("new tweet","a submitted user can tweet",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"adding tweet successful"),1,result));
                    errorcode++;
                }
                else if (choice.equals("2")) {
                    handler.outputStream(user.printFollowing());
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("print following users","a submitted user can see their following list",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"following list shown successful"),1,result));
                    errorcode++;
                }

                else if (choice.equals("3")) {
                    handler.outputStream(user.toString());
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("page view","a submitted user can see their page view",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"page view shown successful"),1,result));
                    errorcode++;
                }

                else if (choice.equals("4")) {
                    int i = 1;
                    for (Tweet t : user.tweets) {
                        handler.outputStream(i+")"+t.toString());
                        i++;
                    }
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    parameters.add("name: "+user.getName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("an account tweets","a submitted user can see their tweets",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"tweets shown successful"),1,result));
                    errorcode++;
                }

                else if (choice.equals("5")) {
                    user = search(user,handler);
                    usersFileManger.userUpdate(user);
                    updateUsers();
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("search","a submitted user can search across the accounts",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"search successful"),1,result));
                    errorcode++;
                }

                else if (choice.equals("6")) {
                    user = setting.setting(user,handler);
                    usersFileManger.userUpdate(user);
                    updateUsers();
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    parameters.add("name: "+user.getName());
                    parameters.add("lastname: "+ user.getLastName());
                    parameters.add("password: "+user.getPassword());
                    parameters.add("birthday: "+user.getBirthDayDate());
                    parameters.add("signup: "+user.getSignUpDate());
                    parameters.add("bio: "+user.getBio());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("setting","a submitted user can change every thing in their account except their username",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"setting successful"),1,result));
                    errorcode++;
                }

                else if(choice.equals("7")){
                    showingTable.showTimeLine(user.getUserName(),handler);
                    handler.outputStream(showingTable.allTweets.toString());
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("timeline view","a submitted user can see their followed user's activities",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"timeline shown successful"),1,result));
                    errorcode++;
                }
                else {
                    break;
                }
            }
        }

    }

    /**
     * This method will add a new tweet
     * @param user ,
     * @param text ,
     * @return user --> new user
     */
    public Account newTweet(Account user, String text, ClientHandler handler){
        user = tweeting.tweet(user.getUserName(),text,handler);
        usersFileManger.userUpdate(user);
        return user;
    }

    /**
     * This method will search for a specific account
     * @param user .
     * @return user .
     */
    public Account search(Account user,ClientHandler handler){

        //user = browser.mainMenu(user,handler);
        usersFileManger.userUpdate(user);
        updateUsers();
        return user;
    }

    /**
     * this method will update users list
     */
    public void updateUsers(){
        twitterUsers.clear();
        twitterUsers.addAll(usersFileManger.AllUsers());
        service.updateUsers();
        browser.update();
        tweeting.update();
    }


    /***
     * this method will help us to manage side button
     * @param actionEvent ,
     */
    public void sideMenuButtonChatRoomClick(ActionEvent actionEvent) {
    }

    /**
     * this method will help us to control chat menu
     * @param actionEvent ,
     */
    public void ChatRoomClickCreate(ActionEvent actionEvent) {

    }

    /**
     * this method will help us to control chat menu
     * @param actionEvent ,
     */
    public void ChatRoomClickJion(ActionEvent actionEvent) {

    }

    /**
     * this method will help us to control chat menu
     * @param actionEvent ,
     */
    public void ChatRoomClickExit(ActionEvent actionEvent) {
    }

    public void ApplicationSelect(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("SideMenu");
        clientStatusHandler.updateClient(clientNumber,"ApplicationShow");
    }

    public void ViewSelect(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("SideMenu");
        clientStatusHandler.updateClient(clientNumber,"ViewShow");
    }

    public void OptionSelect(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("SideMenu");
        clientStatusHandler.updateClient(clientNumber,"OptionShow");
    }

    public void HeloSelect(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("SideMenu");
        clientStatusHandler.updateClient(clientNumber,"HelpShow");
    }

    public void ExitSelect(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("OptionShow");
        clientStatusHandler.updateClient(clientNumber,"FirstMenu");
    }

    public void TraySelect(ActionEvent actionEvent) {
//        String iconImageLoc = "../resources/Images/logo.png";
        int clientNumber = clientStatusHandler.getFxmlState("OptionShow");
        clientStatusHandler.updateClient(clientNumber,"OptionShow");
    }

    public void ContactUs(ActionEvent event) {
        value.setText("SA GROUP\r\nAbtin Zandi -> 9931071 , abtin81@aut.ac.ir\r\nSarvin Nami -> 9931103 , srvn0nm@gmail.com");
    }


    public void SendHelp(ActionEvent event) {
        value.setText("You can explore through the menus!");
    }

    public void Exit(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("ApplicationShow");
        clientStatusHandler.updateClient(clientNumber,"FirstMenu");
    }

    public void wholeExit(ActionEvent actionEvent) {
        int clientNumber = clientStatusHandler.getFxmlState("ApplicationShow");
        clientStatusHandler.updateClient(clientNumber,"wholeExit");
    }
}