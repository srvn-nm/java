package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import org.ce.ap.com.company.server.impl.ObserverService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.TimeLine;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientFileHandler;
import org.ce.ap.com.company.server.service.ClientHandler;

/**
 * ObserverService class for search users' actions like :
 *             tweets , retweets , replies , likes
 *             and to add above to a specific tweet from specific user
 *
 *
 * @author Abtin Zandi , Sarvin Nami
 * @version 3.0
 */
public class Observer implements ObserverService {

    private ArrayList<Account> twitterUsers;//this array will save all sign up users
    private TimeLine showingTable;
    private Tweeting tweeting;
    private AccountFile usersFileManger;

    //Search
    @FXML
    private Button AllUserButton;
    @FXML
    private Button SearchButton;
    @FXML
    private TextField SearchUserName;
    @FXML
    private Text SearchUserNameWarning;

    public void sideMenuButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("Search");
        clientFileHandler.updateClient(clientNumber,"TimeLineShow");
    }


    @FXML
    public void UnFollow(ActionEvent event) {
        update();
        int clientNumber = clientFileHandler.getFxmlState("Search");
        Account user = searchingUserNameAccount(clientFileHandler.getUserAccount(clientNumber));
        if(!searchingUserName(SearchUserName.getText())){
            SearchUserNameWarning.setText("Invalid Username!");
        }
        else{
            Account user1 = searchingUserNameAccount(SearchUserName.getText());
            if(!user.followingExist(user1)){
                SearchUserNameWarning.setText("You have not followed " + SearchUserName.getText() + " already!");
            }
            else {
                user.Unfollow(user1);
                AccountFile accountFile = new AccountFile();
            }
        }
    }


    /**
     * ObserverService constructor will make a new list of twitterUsers
     */
    public Observer(ClientHandler clientHandler) {
        twitterUsers = new ArrayList<>();
        showingTable = new TimeLine();
        tweeting = new Tweeting(clientHandler);
        usersFileManger = new AccountFile();
    }



    private ClientFileHandler clientFileHandler = new ClientFileHandler();
    /**
     * search will return your specific Account
     * (for following or reply or like)
     * <p>
     * return Account
     **/
    public void SerchButton(ActionEvent actionEvent) {
        update();
        int clientNumber = clientFileHandler.getFxmlState("Search");
        Account user = searchingUserNameAccount(clientFileHandler.getUserAccount(clientNumber));
        if(!searchingUserName(SearchUserName.getText())){
            SearchUserNameWarning.setText("Invalid Username!");
        }
        else{
            Account user1 = searchingUserNameAccount(SearchUserName.getText());
            if(user.followingExist(user1)){
                SearchUserNameWarning.setText("You have not followed " + SearchUserName.getText() + " already!");
            }
            else {
                user.setFollowing(user1);
                AccountFile accountFile = new AccountFile();
            }
        }
    }

    /**
     * this method will check username in search
     * if its exist the returned condition is true
     * @param username ,
     * @return isCorrect
     */

    public boolean searchingUserName(String username) {
        update();
        boolean isCorrect = false;
        for (Account person : twitterUsers) {
            if (username.equals(person.getUserName())) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    /**
     * this method will check username in search
     * if its exist the returned condition is true
     * @param username ,
     * @return isCorrect
     */

    public Account searchingUserNameAccount(String username) {
        update();
        Account searchedAccount = new Account("","","","") ;
        for (Account person : twitterUsers) {
            if (username.equals(person.getUserName())) {
                searchedAccount = person ;
                break;
            }
        }
        return searchedAccount ;
    }

    public void update() {
        twitterUsers.clear();
        twitterUsers.addAll(usersFileManger.AllUsers());
        tweeting.update();
    }

}