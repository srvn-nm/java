package org.ce.ap.com.company.server.model;

import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientHandler;

import java.util.ArrayList;
import java.util.Comparator;

/***
 * TimeLine class for showing user's following' actions like :
 *             tweets , retweets , replies and likes
 *
 *
 * @author  Sarvin Nami , Abtin Zandi
 * @version 3.0
 */
public class TimeLine {
    public ArrayList<Account> users;
    public ArrayList<Tweet> allTweets;
    private AccountFile usersFileManger;
    /**
     * constructor
     */
    public TimeLine(){
        users = new ArrayList<>();
        allTweets =new ArrayList<>();
        usersFileManger = new AccountFile();
    }


    /**
     * this method will return all Tweets of timeLine
     * @return Tweets list
     */
    public ArrayList<Tweet> getAllTweets() {
        return allTweets;
    }

    /**
     * This method will create a user's timeline
     * @param username
     * @return
     */
    public void showTimeLine(String username, ClientHandler clientHandler){
        allTweets.clear();
        update();
        //adding tweets, retweets, replies and likes
        for (Account a:users) {
            if (a.getUserName().equals(username)){
                for (Account a2:a.following) {
                    for (Tweet t2:a2.tweets) {
                        Tweet t3 = new Tweet();
                        t3.setText("Tweeted by "+a2.getUserName()+"\n"+t2.getText(),clientHandler);
                        t3.setUsername(a2.getUserName());
                        t3.setReplies(t2.getReplies());
                        t3.setLikes(t2.getLikes(clientHandler));
                        t3.setTime(t2.getTime());
                        allTweets.add(t3);
                    }
                    for (Account a3:users) {
                        for (Tweet t:a3.tweets) {
                            for (String u:t.likes) {
                                if (a2.getUserName().equals(u)){
                                    Tweet t3 = new Tweet();
                                    t3.setText("Liked by "+a2.getUserName()+"\n"+t.getText(),clientHandler);
                                    t3.setUsername(a2.getUserName());
                                    t3.setReplies(t.getReplies());
                                    t3.setLikes(t.getLikes(clientHandler));
                                    t3.setTime(t.getTime());
                                    allTweets.add(t3);
                                }
                            }
                        }
                    }
                }
            }
        }
        allTweets.sort(Comparator.comparing(Tweet::getTime));
    }
    public void update() {
        users.clear();
        users.addAll(usersFileManger.AllUsers());
    }
}
