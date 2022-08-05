package org.ce.ap.com.company.server.model;

import org.ce.ap.com.company.server.service.ClientHandler;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Account class for save users information like :
 *             name , userName , Password , BirthDayDate , signUpDate , Bio , ...
 *
 *this class is collaborating with tweeter class which will help user to manage his/her account
 * @author Abtin Zandi , Sarvin Nami
 * @version 1.0
 */
public class Account implements Serializable {

    private String name; // the name of user
    private String lastName; // the name of user
    private String userName; // the userName of user Account
    private String Password; // the Password  of user Account
    private String birthDayDate; // the Birth day date of user
    private LocalDate signUpDate; // the signUp date of user
    private String Bio; // the Biography of user
    public ArrayList<Tweet> tweets;
    public ArrayList<Account> following; //user following users
    public ArrayList<Tweet> retweets;
    public ArrayList<Tweet> replies;
    public ArrayList<Tweet> likes;
    private String pictureUrl;
    @Serial
    private static final long serialVersionUID = -5831173123415119657L;

    /**
     * Account constructor will take name and last name and userName of user
     * @param name     --> user name
     * @param lastName --> user last name
     * @param userName --> user username
     */
    public Account(String name, String lastName, String userName, String pictureUrl) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        following = new ArrayList<>();
        tweets = new ArrayList<>();
        retweets = new ArrayList<>();
        replies = new ArrayList<>();
        likes = new ArrayList<>();
        this.pictureUrl = pictureUrl;
    }

    /**
     * this method will return the user picture url.
     * @return pictureUrl
     */
    public String getPictureUrl(){
        return pictureUrl;
    }

    /**
     * this method will set the user picture url.
     * @param pictureUrl
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * this method will return the userName
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * this method will return the user UserName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * this method will return the user lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * this method will return the user Password
     *
     * @return Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * this method will return the user birthDayDate
     *
     * @return birthDayDate
     */
    public String getBirthDayDate() {
        return birthDayDate;
    }

    /**
     * this method will return the user signUpDate
     *
     * @return signUpDate
     */
    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    /**
     * this method will return the user Bio
     *
     * @return Bio
     */
    public String getBio() {
        return Bio;
    }

    /***
     * this method set return the username
     * @param name ,
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * this method set return the user lastName
     *
     * @param lastName ,
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /***
     *this method set return the user userName
     * @param userName ,
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * this method set return the user password
     *
     * @param password ,
     */
    public void setPassword(String password) {
        try {
            Password = toHexString(getSHA(password));
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println();
        }
    }

    /**
     * this method set return the user bio
     *
     * @param bio ,
     */
    public void setBio(String bio) {
        Bio = bio;
    }

    /**
     * this method set return the user signUpDate
     *
     * @param signUpDate ,
     */
    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    /**
     * this method set return the user birthDayDate
     *
     * @param birthDayDate ,
     */
    public void setBirthDayDate(String birthDayDate) {
        this.birthDayDate = birthDayDate;
    }

    /**
     * this method will cast account details for print ...
     *
     * @return account's information
     */
    public String toString() {
        return "-------------------------------------------\n" + "user Detail:\n" + "Name: " + getName() +
                "\nLast Name: " + getLastName() +
                "\nUser Name: " + getUserName() +
                "\nBirthDayDate: " + getBirthDayDate() +
                "\nsignUpDate: " + getSignUpDate() +
                "\nBio: " + getBio() +
                "\nTweets: "+ getTweets() +
                "\nRetweets: "+retweets+
                "\nReplies: "+replies+
                "\nlikes: "+likes+
                "\n-------------------------------------------";
    }

    /**
     * this method will help us for following ...
     *
     * @param user --> followed person
     */
    public void setFollowing(Account user) {
        following.add(user);
    }

    /**
     * this method will help us for unfollow function
     *
     * @param user --> Unfollowed person
     */
    public void Unfollow(Account user) {
        String usernameFollow = user.getUserName();
        Iterator<Account> it = following.iterator();

        while (it.hasNext()) {
            Account account = it.next();
            if (usernameFollow.equals(account.getUserName())) {
                it.remove();
                break;
            }

        }
    }

    /**
     * this method will print a list of following users
     * @return String of users
     */
    public String  printFollowing() {

        StringBuilder returnedString = new StringBuilder();
        returnedString.append("--> Twitter:\n");
        for (Account account : following) {
            returnedString.append("----------------------------------------------------\n");
            returnedString.append(account.getUserName()).append("\n").append(account.getName()).append(" --- ").append(account.getLastName()).append("\n");

        }
        return returnedString.toString();
    }

    /**
     * this method will check following user exist
     */
    public boolean followingExist(Account user) {

        boolean toCheck = false ;
        String usernameFollow = user.getUserName();

        for (Account account : following) {
            if (usernameFollow.equals(account.getUserName())) {
                toCheck = true;
                break;
            }
        }
        return toCheck;
    }

    /**
     * method to get the sha of a string
     * @param input ,
     * @return md.digest(input.getBytes(StandardCharsets.UTF_8))
     * @throws NoSuchAlgorithmException ,
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // digest() method called to calculate message digest of an input and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * method to convert a byte array a hexadecimal string value.
     * @param hash ,
     * @return hexString.toString()
     */
    public static String toHexString(byte[] hash) {
        // Convert byte array into Dignum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /**
     * tweets getter
     * @return tweets
     */
    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    /**
     * tweet setter
     * @param tweets ,
     */
    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }
}