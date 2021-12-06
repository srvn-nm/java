package com.company;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/***
 * twitt class
 * @author Sarvin
 */
public class Twitt {
    private String username;
    private int likes;
    private String text;
    private final String time;
    private ArrayList<Twitt> retwitts;

    /**
     * constructor
     * @param text
     */
    public Twitt(String text){
        this.text = text;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        retwitts = new ArrayList<Twitt>();
    }

    /**
     * like getter method
     * @return likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * like setter method
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * text getter method
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * text setter method
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * time getter method
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * username getter method
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter username method
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * a method to add new retwitts to a twitt
     * @param newRetwitt
     */
    public void addRetwitt(Twitt newRetwitt){
        if (!retwitts.contains(newRetwitt)){
            retwitts.add(newRetwitt);
            System.out.println("Retwitt added successfully!");
        }
        else
            System.out.println("This retwitt has already been added!");
    }

    /**
     * a method to remove a retwitt from a twitt
     * @param rt
     */
    public void removeRetwitt(Twitt rt){
        if (retwitts.contains(rt)){
            retwitts.remove(rt);
            System.out.println("Retwitt removed successfully!");
        }
        else
            System.out.println("Retwitt was not found!");
    }
    public String toString(){
        
    }
}
