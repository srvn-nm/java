package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.impl.ObserverService;
import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.TimeLine;
import org.ce.ap.com.company.server.model.Tweet;

import java.util.ArrayList;

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

    /**
     * ObserverService constructor will make a new list of twitterUsers
     */

    public Observer() {
        twitterUsers = new ArrayList<>();
        showingTable = new TimeLine();
        tweeting = new Tweeting();
        usersFileManger = new AccountFile();
    }


    /**
     * print all Account will help  us
     * to show all twitterUsers
     **/
    public void printAllAccount(ClientHandler clientHandler) {
        String allUsers = "";
        for (Account person : twitterUsers) {
            allUsers+=person.toString()+"\n";
        }
        clientHandler.outputStream("--> Twitter:\n"+allUsers);
    }

    /**
     * search will return your specific Account
     * (for following or reply or like)
     * <p>
     * return Account
     **/

    public Account search(Account user, ClientHandler clientHandler) {
        Account searchedAccount;
        printAllAccount(clientHandler);
        while (true) {
            clientHandler.outputStream("Please enter the username you want :");
            String searchingUserName = clientHandler.inputStream();
            if (searchingUserName(searchingUserName)) {
                for (Account person : twitterUsers) {
                    if (searchingUserName.equals(person.getUserName())) {
                        searchedAccount = person;
                        while (true) {
                            clientHandler.outputStream("1)Follow\n2)Un follow\n3) " + searchedAccount.getUserName() + " Tweets\nEny Other Keyboard : Exit");
                            String choice = clientHandler.inputStream();
                            if (choice.equals("1")) {
                                if (user.followingExist(searchedAccount)) {
                                    clientHandler.outputStream("Error: You have already followed " + searchedAccount.getUserName() + " !");
                                } else {
                                    user.setFollowing(searchedAccount);
                                }
                                showingTable.showTimeLine(user.getUserName(), clientHandler);
                                usersFileManger.newUser(user);
                                update();
                            } else if (choice.equals("2")) {
                                if (!user.followingExist(searchedAccount)) {
                                    clientHandler.outputStream("Error: You have not followed " + searchedAccount.getUserName() + " already!");
                                } else {
                                    user.Unfollow(searchedAccount, clientHandler);
                                }
                                showingTable.showTimeLine(user.getUserName(), clientHandler);
                                usersFileManger.newUser(user);
                                update();
                            } else if (choice.equals("3")) {
                                if (user.followingExist(searchedAccount)) {
                                    int i = 1;
                                    for (Tweet t : searchedAccount.tweets) {
                                        clientHandler.outputStream("* Tweet: "+i + ")" + t.toString());
                                        i++;
                                    }
                                }
                                showingTable.showTimeLine(user.getUserName(), clientHandler);
                            } else {
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            } else {
                clientHandler.outputStream("Invalid Username -->\nDo you want to try it again?\n1) Yes\nother keys:No,Back to Menu");
                String choice = clientHandler.inputStream();
                if (!choice.equals("1")) {
                    break;
                }
            }
        }
        return user;
    }

    /**
     * this method will check username in search
     * if its exist the returned condition is true
     * @param username ,
     * @return isCorrect
     */

    public boolean searchingUserName(String username) {
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
     * this method will help us to follow and unfollow users and show following users
     * @param user ,
     * @return updated account
     */

    public Account mainMenu(Account user, ClientHandler clientHandler) {

        while (true) {
            clientHandler.outputStream("\n1)search\n2)my Following List\n3)my TimeLine\nEny Other Keyboard : Exit");
            showingTable.showTimeLine(user.getUserName(), clientHandler);
            String choice = clientHandler.inputStream();
            if (choice.equals("1")) {
                user = search(user, clientHandler);
                showingTable.showTimeLine(user.getUserName(), clientHandler);
                update();
            } else if (choice.equals("2")) {
                clientHandler.outputStream(user.printFollowing());
                showingTable.showTimeLine(user.getUserName(), clientHandler);
            } else if (choice.equals("3")) {
                while (true) {
                    int index = 1;
                    showingTable.showTimeLine(user.getUserName(), clientHandler);
                    ArrayList<Tweet> allTweet = new ArrayList<>();
                    allTweet.addAll(showingTable.getAllTweets());
                    String str = "";
                    for (Tweet t : allTweet) {
                        str += "\n" +index + ") " + t.toString();
                        index++;
                    }
                    clientHandler.outputStream(str);
                    int num = -1;
                    do{
                        clientHandler.outputStream("Enter the number of the tweet you want to react:\nif you want to exit enter 0");
                        String choice3 = clientHandler.inputStream();
                        char[] chars = choice3.toCharArray();
                        StringBuilder sb = new StringBuilder();
                        boolean digit = true;
                        for (char c:chars) {
                            if (Character.isDigit(c)) {
                                sb.append(c);
                            }
                            else {
                                digit = false;
                                break;
                            }
                        }
                        if(choice3.equals("0")) break;
                        if (digit){
                            num = Integer.parseInt(String.valueOf(sb));
                        }else {
                            clientHandler.outputStream("Error! Try again:");
                        }
                    }while(num == -1);
                    String choice2;
                    if (num == 0){
                        choice2 = "0";
                    }
                    else {
                        clientHandler.outputStream("\n1)Reply\n2)Retweet\n3)Like\nEny Other Keyboard : Exit");
                        choice2 = clientHandler.inputStream();
                    }
                    Account followed = null;
                    for (Account account:twitterUsers) {
                        if (showingTable.allTweets.get(num - 1).getUsername().equals(account.getUserName())) {
                            followed = account;
                            break;
                        }
                    }
                    if (choice2.equals("1")) {
                        user = tweeting.reply(user, followed, allTweet.get(num - 1), clientHandler);
                        usersFileManger.userUpdate(user);
                        update();
                        allTweet.addAll(showingTable.getAllTweets());
                    } else if (choice2.equals("2")) {
                        user = tweeting.retweet(user,followed, allTweet.get(num - 1), clientHandler);
                        usersFileManger.userUpdate(user);
                        update();
                        allTweet.addAll(showingTable.getAllTweets());
                    } else if (choice2.equals("3")) {
                        user = tweeting.Like(allTweet.get(num - 1), user,followed);
                        usersFileManger.userUpdate(user);
                        update();
                        allTweet.addAll(showingTable.getAllTweets());
                    } else break;
                }
                showingTable.showTimeLine(user.getUserName(), clientHandler);
            } else {
                break;
            }
        }
        return user;
    }

    public void update() {
        twitterUsers.clear();
        twitterUsers.addAll(usersFileManger.AllUsers());
        tweeting.update();
    }
}