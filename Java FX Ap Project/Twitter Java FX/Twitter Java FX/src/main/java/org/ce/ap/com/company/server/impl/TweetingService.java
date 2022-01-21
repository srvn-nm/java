package org.ce.ap.com.company.server.impl;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientHandler;
import org.ce.ap.com.company.server.service.TweetFile;

import java.util.ArrayList;

public interface TweetingService {
    public ArrayList<Account> users = new ArrayList<>();
    final TweetFile tweetFile = new TweetFile();
    final AccountFile usersFileManger = new AccountFile();
    /**
     * THIS METHOD WILL LIKE A TWEET.
     * @param t ,
     * @param likerAc ,
     * @param likedAc ,
     */
    public Account Like(Tweet t, Account likerAc, Account likedAc);
    /**
     * a method to add a new tweet
     * @param un ,
     * @param text ,
     * @return t
     */
    public Account tweet(String un, String text, ClientHandler clientHandler);
    /**
     * This method will delete a tweet from a user's tweets.
     * @param un ,
     * @param text ,
     */
    public void deleteTweet(String un, String text, ClientHandler clientHandler);
    /**
     * a method to retweet someone's tweet
     * @param destinationAc ,
     * @param originAc ,
     * @param tweet ,
     */
    public Account retweet(Account destinationAc, Account originAc, Tweet tweet, ClientHandler clientHandler);
    /**
     * this method can be used when somebody wants to reply to somebody else
     * @param replierAc ,
     * @param repliedAc ,
     * @param tweet ,
     */
    public Account reply(Account replierAc, Account repliedAc, Tweet tweet, ClientHandler clientHandler);
    public void update();
}
