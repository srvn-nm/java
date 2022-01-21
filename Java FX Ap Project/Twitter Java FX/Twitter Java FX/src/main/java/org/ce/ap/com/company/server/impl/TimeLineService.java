package org.ce.ap.com.company.server.impl;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientHandler;

import java.util.ArrayList;

public interface TimeLineService {
    public ArrayList<Account> users = new ArrayList<>();
    public ArrayList<Tweet> allTweets =new ArrayList<>();
    AccountFile usersFileManger = new AccountFile();

    /**
     * this method will return all Tweets of timeLine
     * @return Tweets list
     */
    public ArrayList<Tweet> getAllTweets();
    /**
     * This method will create a user's timeline
     * @param username
     * @return
     */
    public void showTimeLine(String username, ClientHandler clientHandler);
    public void update();
}
