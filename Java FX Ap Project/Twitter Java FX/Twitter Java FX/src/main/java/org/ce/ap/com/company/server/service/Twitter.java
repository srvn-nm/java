package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.impl.TwitterService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Errortype;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.model.Tweet;

import java.util.ArrayList;

/**
 * sever class will help us to manage and control all the twitter sections
 */
public class Twitter implements TwitterService {

    private Tweeting tweeting;
    private TimeLine showingTable;
    private final Authentication service;//log in and sign up service
    private Observer browser;//searching service
    private ArrayList<Account> twitterUsers; //this array will save all sign up users
    private final AccountFile usersFileManger ;
    private final ChatRoom chatRoom;
    private requestJSONHandler requestJSONHandler = new requestJSONHandler();
    private responseJSONHandler responseJSONHandler = new responseJSONHandler();
    public int errorcode = 0;
    private final Setting setting;

    /**
     *Server Constructor
     */
    public Twitter(){
        service = new Authentication();
        twitterUsers = new ArrayList<>();
        browser = new Observer();
        tweeting = new Tweeting();
        showingTable = new TimeLine();
        usersFileManger = new AccountFile();
        chatRoom = new ChatRoom();
        setting = new Setting();
    }

    /**
     *log in server can help us to manage our account and use Twitter after log in ...
     */
    public void serverLogIn(ClientHandler handler){

        Account user = service.logIn(handler);
        String username = user.getName();
        boolean repeated = false;
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("username: "+user.getUserName());
        parameters.add("password: "+user.getPassword());
        requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("log in","a submitted user can log in",parameters));
        ArrayList<String> result = new ArrayList<>();
        result.add(user.toString());
        responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"log in successful"),1,result));
        errorcode++;
        if(username.equals("1")){
            handler.outputStream("You are not logged in. If you want you can re-login or register from the main menu\n--> \"Twitter\"");
        }

        else{
            for(Account person : twitterUsers){
                if(username.equals(person.getUserName())){
                    mainMenu(user,handler);
                    repeated = true;
                    break;
                }
            }
        }

        if(!repeated && !username.equals("1")){
            mainMenu(user,handler);
        }

    }

    /**
     * new Member will collaborate with signUp method for registration
     */
    public void newMember(ClientHandler handler){
        Account user ;
        user = service.SignUp(handler);
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("username: "+user.getUserName());
        parameters.add("name: "+user.getName());
        parameters.add("lastname: "+ user.getLastName());
        parameters.add("password: "+user.getPassword());
        parameters.add("birthday: "+user.getBirthDayDate());
        parameters.add("signup: "+user.getSignUpDate());
        parameters.add("bio: "+user.getBio());
        requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("sign up","a submitted user can log in",parameters));
        ArrayList<String> result = new ArrayList<>();
        result.add(user.toString());
        responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"sign up successful"),1,result));
        errorcode++;
        mainMenu(user,handler);
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
                else if(choice.equals("8")){
                    Chatroom(user,handler);
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add("username: "+user.getUserName());
                    requestJSONHandler.writeToFile(requestJSONHandler.JSONMaker("chatroom","a submitted user can chat in chatroom",parameters));
                    ArrayList<String> result = new ArrayList<>();
                    result.add(user.toString());
                    responseJSONHandler.writeToFile(responseJSONHandler.JSONMaker(false,new Errortype(errorcode,"chatting successful"),1,result));
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

        user = browser.mainMenu(user,handler);
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

    /**
     * in this method we will show chats menu
     */
    public void Chatroom(Account user, ClientHandler handler){
        while (true){
            handler.outputStream("1)New chat\n2)Join Chat\nEny Other Keyboard : Exit");
            String choice = handler.inputStream();
            if (choice.equals("1")) {
                chatRoom.chatBuilder(handler);
            } else if (choice.equals("2")) {
                chatRoom.JoinChat(user,handler);
            }
            else{
                break;
            }
        }
    }
}