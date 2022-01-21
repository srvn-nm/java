package org.ce.ap.com.company.server.impl;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.service.*;

import java.util.ArrayList;

public interface TwitterService {
    Tweeting tweeting = new Tweeting();
    TimeLine showingTable = new TimeLine();
    final Authentication service = new Authentication();//log in and sign up service
    Observer browser = new Observer();//searching service
    ArrayList<Account> twitterUsers = new ArrayList<>(); //this array will save all sign up users
    final AccountFile usersFileManger = new AccountFile();
    final ChatRoom chatRoom = new ChatRoom();
    requestJSONHandler requestJSONHandler = new requestJSONHandler();
    responseJSONHandler responseJSONHandler = new responseJSONHandler();
    public int errorcode = 0;
    final Setting setting = new Setting();
    /**
     *log in server can help us to manage our account and use Twitter after log in ...
     */
    public void serverLogIn(ClientHandler handler);
    /**
     * new Member will collaborate with signUp method for registration
     */
    public void newMember(ClientHandler handler);
    /**
     * this method is the main part of program Where the user can choose what he/she wants to do
     * collaborating with new tweet , myFollowingSystem ,search , setting
     */
    public void mainMenu(Account user,ClientHandler handler);
    /**
     * This method will add a new tweet
     * @param user ,
     * @param text ,
     * @return user --> new user
     */
    public Account newTweet(Account user, String text, ClientHandler handler);
    /**
     * This method will search for a specific account
     * @param user .
     * @return user .
     */
    public Account search(Account user,ClientHandler handler);
    /**
     * this method will update users list
     */
    public void updateUsers();
    /**
     * in this method we will show chats menu
     */
    public void Chatroom(Account user, ClientHandler handler);
}
