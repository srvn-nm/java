package com.example.twitterjavafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.ce.ap.com.company.server.impl.TweetingService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.model.Tweet;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientFileHandler;
import org.ce.ap.com.company.server.service.ClientHandler;
import org.ce.ap.com.company.server.service.TweetFile;

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
    @FXML public Button sideMenu;
    @FXML public TextField Tweet;
    @FXML public Button TweetButton;
    @FXML public Text CreateWarning;
    private TimeLine timeLine;
    public ClientFileHandler clientFileHandler;
    public ClientHandler clientHandler;
    public String selectedTweet = "";
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    Account account;
    @FXML
    public Text UserName;
    @FXML public Button Reply;
    @FXML public Button Retweet;
    @FXML public Button Like;
    @FXML public BorderPane massages;
    @FXML public Button Exit;
    @FXML public ScrollBar scroll;
    @FXML public Button Refresh;

    /**
     * constructor
     */
    public Tweeting(ClientHandler clientHandler){
        users = new ArrayList<>();
        tweetFile = new TweetFile();
        usersFileManger = new AccountFile();
        timeLine = new TimeLine();
        clientFileHandler = new ClientFileHandler();
        for (Account a:users) {
            if (a.getUserName().equals(UserName.getText())) {
                account = a;
                break;
            }
        }
        this.clientHandler = clientHandler;
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
        Account user = new Account("","","","");
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
        for (Account a:users) {
            if (a.getUserName().equals(UserName.getText())) {
                account = a;
                break;
            }
        }

    }

    public void TweetProcessor(){
        tweets.clear();
        tweets = new ArrayList<Tweet>(timeLine.allTweets);
        // create a toggle group
        ToggleGroup tg = new ToggleGroup();
        // create radiobutton

        for(Tweet t : tweets){
            RadioButton r1 = new RadioButton(t.toString());
            r1.setToggleGroup(tg);
            massages.getChildren().add(r1);
        }
        // add a change listener
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

                RadioButton rb = (RadioButton)tg.getSelectedToggle();

                if (rb != null) {

                    // save the selected tweet
                    selectedTweet = rb.getText();
                }
            }
        });
    }

    @FXML
    public void ExitFX(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("TimeLineShow");
        clientFileHandler.updateClient(clientNumber,"FirstMenu");
    }

    @FXML
    public void LikeFX(ActionEvent event) {
        Account ac = null;
        int tweetNumber = 0;
        for (Tweet t:tweets) {
            if (t.toString().equals(selectedTweet)){
                tweetNumber = tweets.indexOf(t);
                break;
            }
        }
        for (Account a:users) {
            if (a.getUserName().equals(tweets.get(tweetNumber+1).getUsername())) {
                ac = a;
                break;
            }
        }
        Like(tweets.get(tweetNumber+1),account,ac);
        RefreshFX(event);
    }

    @FXML
    public void RefreshFX(ActionEvent event) {
        timeLine.showTimeLine(UserName.getText(),clientHandler);
        TweetProcessor();
    }

    @FXML
    public void ReplyFX(ActionEvent event) {
        Account ac = null;
        int tweetNumber = 0;
        for (Tweet t:tweets) {
            if (t.toString().equals(selectedTweet)){
                tweetNumber = tweets.indexOf(t);
                break;
            }
        }
        for (Account a:users) {
            if (a.getUserName().equals(tweets.get(tweetNumber+1).getUsername())) {
                ac = a;
                break;
            }
        }
        reply(account,ac,tweets.get(tweetNumber+1),clientHandler);
        RefreshFX(event);
    }

    @FXML
    public void RetweetFX(ActionEvent event) {
        Account ac = null;
        int tweetNumber = 0;
        for (Tweet t:tweets) {
            if (t.toString().equals(selectedTweet)){
                tweetNumber = tweets.indexOf(t);
                break;
            }
        }
        for (Account a:users) {
            if (a.getUserName().equals(tweets.get(tweetNumber+1).getUsername())) {
                ac = a;
                break;
            }
        }
        retweet(account,ac,tweets.get(tweetNumber+1),clientHandler);
        RefreshFX(event);
    }

    @FXML
    public void initialize() {
        int clientNumber = clientFileHandler.getFxmlState("TimeLineShow");
        UserName.setText(clientFileHandler.getUserAccount(clientNumber));
        timeLine.showTimeLine(UserName.getText(),clientHandler);
        TweetProcessor();
    }

    /**
     * this method is for creating a new tweet with the given text.
     * @param actionEvent
     */
    public void TweetEvent(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("CreateTweet");
        String userName = clientFileHandler.getUserAccount(clientNumber);
        tweet(userName,Tweet.getText(),clientHandler);
    }

    /**
     * this method will transfer to the side menu.
     * @param actionEvent
     */
    public void sideMenuButtonCreate(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("CreateTweet");
        clientFileHandler.updateClient(clientNumber,"sideMenu");
    }
}