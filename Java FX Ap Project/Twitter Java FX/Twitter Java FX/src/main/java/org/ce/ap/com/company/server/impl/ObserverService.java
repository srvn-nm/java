package org.ce.ap.com.company.server.impl;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientHandler;
import org.ce.ap.com.company.server.service.Tweeting;

import java.util.ArrayList;

public interface ObserverService {
    ArrayList<Account> twitterUsers = new ArrayList<>();
    TimeLine showingTable = new TimeLine();
    Tweeting TWEETING = new Tweeting();
    AccountFile usersFileManger = new AccountFile();
    /**
     * print all Account will help  us
     * to show all twitterUsers
     **/
    public void printAllAccount(ClientHandler clientHandler);
    /**
     * search will return your specific Account
     * (for following or reply or like)
     * <p>
     * return Account
     **/

    public Account search(Account user, ClientHandler clientHandler);
    /**
     * this method will check username in search
     * if its exist the returned condition is true
     * @param username ,
     * @return isCorrect
     */

    public boolean searchingUserName(String username);
    /**
     * this method will help us to follow and unfollow users and show following users
     * @param user ,
     * @return updated account
     */

    public Account mainMenu(Account user, ClientHandler clientHandler);
    public void update();
}
