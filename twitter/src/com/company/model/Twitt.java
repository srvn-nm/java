package com.company.model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.company.service.*;

/***
 * twitt class
 * @author Sarvin , Abtin
 */
public class Twitt {
    private String username;
    public ArrayList<String> likes;
    private String text;
    private String time;
    private ArrayList<Twitt> replies;

    /**
     * constructor
     */
    public Twitt(){
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        replies = new ArrayList<Twitt>();
        likes = new ArrayList<String>();
    }

    /**
     * like getter method
     * @return likes
     */
    public int getLikesNumber() {
        return likes.size();
    }

    /**
     * Likes getter method
     * @return this.likes
     */
    public ArrayList getLikes() {
        if (likes.size() == 0) {
            return null;
        }
        else {
            System.out.println("users like this twitt:");
            for (String l:likes){
                System.out.print(l+" ");
            }
            return this.likes;
        }
    }
    /**
     * add like method
     */
    public void like(String userName) {
        this.likes.add(userName);
    }

    /**
     * text getter method
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * text setter method
     * @param text
     * @return
     */
    public Twitt setText(String text) {
        if (text.toCharArray().length <= 256)
            this.text = text;
        else
            System.out.println("Given text was more than 256 characters!\nTry again.");
        return this;
    }

    /**
     * time getter method
     * @return time
     */
    public String getTime() {
        return this.time;
    }

    /**
     * username getter method
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * setter username method
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * a method to add new replies to a twitt
     * @param newReply
     */
    public void addReply(Twitt newReply){
        if (!replies.contains(newReply)){
            replies.add(newReply);
            System.out.println("Reply added successfully!");
        }
        else
            System.out.println("This reply has already been added!");
    }

    /**
     * a method to remove a reply from a twitt
     * @param rt
     */
    public void removeReply(Twitt rt){
        if (replies.contains(rt)){
            replies.remove(rt);
            System.out.println("Reply removed successfully!");
        }
        else
            System.out.println("Reply was not found!");
    }

    /**
     * to string method
     * @return twitts with their retwitts
     */
    public String toString(){
        StringBuilder res = new StringBuilder("\n| " + this.username + "    " + this.time + "\n| " + this.text+"\n");
        for (Twitt rt : replies){
            res.append("----").append(rt.toString());
        }
        return res.toString();
    }

    /**
     * return the number of replies a twitt has
     * @return replies.size()
     */
    public int repliesNumber(){
        return replies.size();
    }

    /**
     * this method will set the time
     * @param localDateTime
     */
    public void setTime(String localDateTime){
        this.time = localDateTime;
    }

    /**
     * This method will set usernames who like this twiit
     * @param likes
     */
    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    /**
     * this method will set the replies
     * @param replies
     */
    public void setReplies(ArrayList<Twitt> replies) {
        this.replies = replies;
    }

    /**
     * This method will return the replies.
     * @return this.replies
     */
    public ArrayList<Twitt> getReplies() {
        return replies;
    }
}