package com.company.service;

import java.util.ArrayList;
import java.util.Scanner;
import com.company.model.*;

/***
 * TweetingService class for managing user's actions like :
 *             twitts , retwitts , replies and likes
 *
 *
 * @author  Sarvin Nami , Abtin Zandi
 * @version 3.0
 */
public class TweetingService {
    public ArrayList<Account> users;
    /**
     * constructor
     */
    public TweetingService(){
        users = new ArrayList<Account>();
    }
    /**
     * THIS METHOD WILL LIKE A TWITT.
     * @param t
     * @param likerUsername
     * @param likedUsername
     */
    public Account Like(Twitt t,String likerUsername,String likedUsername){
        t.like(likerUsername);
        Account user = null;
        for (Account ac:users) {
            if (ac.getUserName().equals(likerUsername)){
                ac.likes.add(t);
                user = ac;
                break;
            }
        }
        return user;
    }

    /**
     * a method to add a new twitt
     * @param un
     * @param text
     * @return t
     */
    public Account twitt(String un,String text){
        for (Account a:users) {
            if (!a.getUserName().equals(un)){
                System.out.println("This username does not exist and should sign up first!");
                return null;
            }
            for (Twitt t:a.twitts){
                if ((a.getUserName().equals(un)) && (t.getText().equals(text))){
                    System.out.println("This twitt was already added!");
                    return null;
                }
            }
        }
        Twitt t = new Twitt();
        t.setUsername(un);
        t.setText(text);
        Account user = null;
        for (Account ac:users) {
            if (ac.getUserName().equals(un)){
                ac.twitts.add(t);
                user = ac;
            }
        }
        return user;
    }

    /**
     * This method will delete a twitt from a user's twitts.
     * @param un
     * @param text
     */
    public void deleteTwitt(String un, String text){
        boolean check = false;
        for (Account a:users) {
            if (!a.getUserName().equals(un)){
                System.out.println("This username does not exist and should sign up first!");
            }
            for (Twitt t:a.twitts){
                if ((a.getUserName().equals(un)) && (t.getText().equals(text))){
                    a.twitts.remove(t);
                    System.out.println("This twitt deleted successfully!");
                    check = true;
                }
            }
        }
        if (!check)
            System.out.println("This twitt was not found!");
    }

    /**
     * a method to retwitt someone's twitt
     * @param destinationUsername
     * @param originUsername
     * @param text
     */
    public Account retwitt(String destinationUsername,String originUsername,String text){
        boolean check = false;
        Twitt t2 = new Twitt();
        for (Account oa:users) {
            for (Account da:users) {
                for (Twitt t:oa.twitts){
                    if ((oa.getUserName().equals(originUsername)) && (t.getText().equals(text))){
                        t2.setUsername(destinationUsername);
                        t2.setText("retwitted from:\n              "+originUsername+"\n"+t.getText());
                        da.twitts.add(t2);
                        System.out.println("This twitt retwitted successfully!");
                        check = true;
                        break;
                    }
                }
            }
        }
        if (!check)
            System.out.println("This twitt was not found!");
        Account user = null;
        for (Account ac:users) {
            if (ac.getUserName().equals(destinationUsername)){
                ac.twitts.add(t2);
                ac.retwitts.add(t2);
                user = ac;
            }
        }
        return user;
    }

    /**
     * this method can be used when somebody wants to reply to somebody else
     * @param replyerUsername
     * @param repliedUsername
     * @param text
     */
    public Account reply(String replyerUsername,String repliedUsername,String text){
        boolean check = false;
        Twitt t2 = new Twitt();
        Scanner in = new Scanner(System.in);
        for (Account oa:users) {
            for (Twitt t:oa.twitts){
                if ((oa.getUserName().equals(repliedUsername)) && (t.getText().equals(text))){
                    t2.setUsername(replyerUsername);
                    System.out.println("please type your reply:");
                    t2.setText(in.nextLine());
                    t.addReply(t2);
                    System.out.println("Reply added successfully!");
                    check = true;
                    break;
                }
            }
        }
        if (!check)
            System.out.println("This twitt was not found!");
        Account user = null;
        for (Account ac:users) {
            if (ac.getUserName().equals(replyerUsername)){
                ac.twitts.add(t2);
                ac.replies.add(t2);
                user = ac;
            }
        }
        return user;
    }
}