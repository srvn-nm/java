package com.company.model;

import java.util.ArrayList;
import java.util.Comparator;
import com.company.service.*;

/***
 * TimeLine class for showing user's following' actions like :
 *             twitts , retwitts , replies and likes
 *
 *
 * @author  Sarvin Nami , Abtin Zandi
 * @version 3.0
 */
public class TimeLine {
    public ArrayList<Account> users;
    public ArrayList<Twitt> allTwitts;
    /**
     * constructor
     */
    public TimeLine(){
        users = new ArrayList<Account>();
        allTwitts =new ArrayList<Twitt>();
    }

    /**
     * This method will create a user's timeline
     * @param username
     */
    public void showTimeLine(String username){
        allTwitts.clear();
        //adding twitts, retwitts, replies and likes
        for (Account a:users) {
            if (a.getUserName().equals(username)){
                for (Account a2:a.following) {
                    for (Twitt t2:a2.twitts) {
                        Twitt t3 = new Twitt();
                        t3.setText("Tweeted by "+a2.getUserName()+"\n"+t2.getText());
                        t3.setUsername(a2.getUserName());
                        t3.setReplies(t2.getReplies());
                        t3.setLikes(t2.getLikes());
                        t3.setTime(t2.getTime());
                        allTwitts.add(t3);
                    }
                    for (Account a3:users) {
                        for (Twitt t:a3.twitts) {
                            for (String u:t.likes) {
                                if (a2.getUserName().equals(u)){
                                    Twitt t3 = new Twitt();
                                    t3.setText("Liked by "+a2.getUserName()+"\n"+t.getText());
                                    t3.setUsername(a2.getUserName());
                                    t3.setReplies(t.getReplies());
                                    t3.setLikes(t.getLikes());
                                    t3.setTime(t.getTime());
                                    allTwitts.add(t3);
                                }
                            }
                        }
                    }
                }
            }
        }
        allTwitts.sort(Comparator.comparing(Twitt::getTime));
    }
}