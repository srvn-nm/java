package org.ce.ap.com.company.server.model;

import org.ce.ap.com.company.server.service.ClientHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/***
 * tweet class
 * @author Sarvin , Abtin
 */
public class Tweet implements Serializable {
    private String username;
    public ArrayList<String> likes;
    private String text;
    private String time;
    private ArrayList<Tweet> replies;

    /**
     * constructor
     */
    public Tweet(){
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        replies = new ArrayList<Tweet>();
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
    public ArrayList getLikes(ClientHandler clientHandler) {
        if (likes.size() == 0) {
            return null;
        }
        else {
            clientHandler.outputStream("users like this tweet:");
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
    public Tweet setText(String text, ClientHandler clientHandler) {
        if (text.toCharArray().length <= 256)
            this.text = text;
        else
            clientHandler.outputStream("Given text was more than 256 characters!\nTry again.");
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
     * a method to add new replies to a tweet
     * @param newReply
     */
    public void addReply(Tweet newReply, ClientHandler clientHandler){
        if (!replies.contains(newReply)){
            replies.add(newReply);
            clientHandler.outputStream("Reply added successfully!");
        }
        else
            clientHandler.outputStream("This reply has already been added!");
    }

    /**
     * a method to remove a reply from a tweet
     * @param rt
     */
    public void removeReply(Tweet rt, ClientHandler clientHandler){
        if (replies.contains(rt)){
            replies.remove(rt);
            clientHandler.outputStream("Reply removed successfully!");
        }
        else
            clientHandler.outputStream("Reply was not found!");
    }

    /**
     * to string method
     * @return tweets with their retweets
     */
    public String toString(){
        StringBuilder res = new StringBuilder("\n| " + this.username + "    " + this.time + "\n| " + this.text+"\n");
        for (Tweet rt : replies){
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
     * This method will set usernames who like this tweet
     * @param likes
     */
    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    /**
     * this method will set the replies
     * @param replies
     */
    public void setReplies(ArrayList<Tweet> replies) {
        this.replies = replies;
    }

    /**
     * This method will return the replies.
     * @return this.replies
     */
    public ArrayList<Tweet> getReplies() {
        return replies;
    }
}