package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.impl.TweetingService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;

import java.util.ArrayList;
import java.util.Iterator;

/***
 * TweetingService class for managing user's actions like :
 *             tweets , retweets , replies and likes
 *
 *
 * @author  Sarvin Nami , Abtin Zandi
 * @version 3.0
 */
public class Tweeting implements TweetingService {
    public ArrayList<Account> users;
    private final TweetFile tweetFile;
    private final AccountFile usersFileManger ;
    /**
     * constructor
     */
    public Tweeting(){
        users = new ArrayList<>();
        tweetFile = new TweetFile();
        usersFileManger = new AccountFile();
    }
    /**
     * THIS METHOD WILL LIKE A TWEET.
     * @param t ,
     * @param likerAc ,
     * @param likedAc ,
     */
    public Account Like(Tweet t, Account likerAc, Account likedAc){
        update();
        t.like(likerAc.getUserName());
        tweetFile.writeNewTweetToFile(t);
        Account user;
        likerAc.likes.add(t);
        user = likerAc;
        System.out.println("1");
        usersFileManger.userUpdate(likedAc);
        usersFileManger.userUpdate(likerAc);
        update();
        return user;
    }


    /**
     * a method to add a new tweet
     * @param un ,
     * @param text ,
     * @return t
     */
    public Account tweet(String un, String text, ClientHandler clientHandler){
        update();
        for (Account a:users) {
            for (Tweet t:a.tweets){
                if ((a.getUserName().equals(un)) && (t.getText().equals(text))){
                    clientHandler.outputStream("This tweet was already added!");
                    return null;
                }
            }
        }
        Tweet t = new Tweet();
        t.setUsername(un);
        t.setText(text,clientHandler);
        Account user = new Account("","","");
        for (Account ac:users) {
            if (ac.getUserName().equals(un)){
                ac.tweets.add(t);
                user = ac;
            }
        }
        tweetFile.writeNewTweetToFile(t);
        usersFileManger.newUser(user);
        update();
        return user;
    }

    /**
     * This method will delete a tweet from a user's tweets.
     * @param un ,
     * @param text ,
     */
    public void deleteTweet(String un, String text, ClientHandler clientHandler){
        update();
        boolean check = false;
        for (Account a:users) {
            if (!a.getUserName().equals(un)){
                clientHandler.outputStream("This username does not exist and should sign up first!");
            }
            Iterator<Tweet> it = a.tweets.iterator();
            while (it.hasNext()){
                Tweet t = it.next();
                if ((a.getUserName().equals(un)) && (t.getText().equals(text))){
                    it.remove();
                    usersFileManger.newUser(a);
                    clientHandler.outputStream("This tweet deleted successfully!");
                    check = true;
                }
            }
        }
        if (!check)
            clientHandler.outputStream("This tweet was not found!");
        tweetFile.removeFile(un,text);
        update();
    }

    /**
     * a method to retweet someone's tweet
     * @param destinationAc ,
     * @param originAc ,
     * @param tweet ,
     */
    public Account retweet(Account destinationAc, Account originAc, Tweet tweet, ClientHandler clientHandler){
        update();
        boolean check = false;
        Tweet t2 = new Tweet();
        for (Tweet t:originAc.tweets){
            if (t.equals(tweet)){
                t2.setUsername(destinationAc.getUserName());
                t2.setText("retweeted from:\n              "+ originAc +"\n"+t.getText(),clientHandler);
                destinationAc.tweets.add(t2);
                destinationAc.retweets.add(t2);
                usersFileManger.userUpdate(destinationAc);
                clientHandler.outputStream("This tweet retweeted successfully!");
                check = true;
                break;
            }
        }
        if (!check) {
            clientHandler.outputStream("This tweet was not found!");
        }
        Account user;
        user = destinationAc;
        tweetFile.writeNewTweetToFile(t2);
        usersFileManger.userUpdate(originAc);
        update();
        return user;
    }

    /**
     * this method can be used when somebody wants to reply to somebody else
     * @param replierAc ,
     * @param repliedAc ,
     * @param tweet ,
     */
    public Account reply(Account replierAc, Account repliedAc, Tweet tweet, ClientHandler clientHandler){
        update();
        Account user;
        boolean check = false;
        Tweet t2 = new Tweet();
        for (Tweet t : repliedAc.tweets) {
            if (t.equals(tweet)) {
                t2.setUsername(replierAc.getUserName());
                clientHandler.outputStream("please type your reply:");
                String text = clientHandler.inputStream();
                t2.setText(text, clientHandler);
                t.addReply(t2, clientHandler);
                tweetFile.writeNewTweetToFile(t);
                replierAc.tweets.add(t2);
                replierAc.replies.add(t2);
                usersFileManger.userUpdate(replierAc);
                clientHandler.outputStream("Reply added successfully!");
                check = true;
                break;
            }
        }
        if (!check) {
            clientHandler.outputStream("This tweet was not found!");
        }
        user = replierAc;
        tweetFile.writeNewTweetToFile(t2);
        usersFileManger.userUpdate(repliedAc);
        update();
        return user;
    }

    public void update() {
        users.clear();
        users.addAll(usersFileManger.AllUsers());

    }
}