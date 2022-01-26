package com.company.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.company.model.*;

/**
 * ObserverService class for search users' actions like :
 *             twitts , retwitts , replies , likes
 *             and to add above to a specific twitt from specific user
 *
 *
 * @author Abtin Zandi , Sarvin Nami
 * @version 3.0
 */
public class ObserverService {

    private ArrayList<Account> twitterUsers;//this array will save all sign up users
    private TimeLine showingTable;
    private TweetingService tweetingService;

    /**
     * ObserverService constructor will make a new list of twitterUsers
     */

    public ObserverService(){
        twitterUsers = new ArrayList<>();
        showingTable = new TimeLine();
        tweetingService = new TweetingService();
    }

    /**
     * this method will add new users ...
     * @param user
     */
    public void addNewMember(Account user){
        twitterUsers.add(user);
        showingTable.users.add(user);
        tweetingService.users.add(user);
    }


    /**
     * print all Account will help  us
     to show all twitterUsers
     **/

    public void printAllAccount(){
        for
        (Account person : twitterUsers){

            System.out.println(person.toString
                    ()+"\n");
        }
    }

    /**
     *
     search will return your specific Account
     (for following or reply or like)
     *
     return Account
     **/

    public Account search(Account user){
        Account searchedAccount = new Account("","","") ;
        printAllAccount();
        while (1==1){
            System.out.println("Please enter the username you want :");
            Scanner input = new Scanner(System.in);
            String searchingUserName = input.nextLine();
            if(searchingUserName(searchingUserName)){
                for(Account person : twitterUsers){
                    if(searchingUserName.equals(person.getUserName())){
                        searchedAccount = person;
                        while (true){
                            System.out.println("1)Follow\n2)Un follow\n3) "+searchedAccount.getUserName()+" Twitts\nEny Other Keyboard : Exit");
                            String choice = input.nextLine();
                            if(choice.equals("1")){
                                if(user.followingExist(searchedAccount)){
                                    System.out.println("You have already followed "+searchedAccount.getUserName()+" !");
                                }
                                else {
                                    user.setFollowing(searchedAccount);
                                }
                                showingTable.showTimeLine(user.getUserName());
                            }
                            else if(choice.equals("2")){
                                if(!user.followingExist(searchedAccount)){
                                    System.out.println("You have not followed "+searchedAccount.getUserName()+" already!");
                                }
                                else {
                                    user.Unfollow(searchedAccount);
                                }
                                showingTable.showTimeLine(user.getUserName());
                            }

                            else if(choice.equals("3")){
                                if(user.followingExist(searchedAccount)){
                                    int i = 1;
                                    for (Twitt t : searchedAccount.twitts) {
                                        System.out.println(i+")"+t.toString());
                                        i++;
                                    }
                                }
                                showingTable.showTimeLine(user.getUserName());
                            }
                            else {
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
            else {
                System.out.println("Invalid Username -->\nDo you want to try it again?\n1) Yes\nother keys:No,Back to Menu");
                String choice = input.nextLine();
                if(!choice.equals("1")){
                    break;
                }
            }
        }

        return user;

    }

    /***
     * this method will check username in search
     * if its exist the returned condition is true
     * @param username
     * @return isCorrect
     */

    public boolean searchingUserName(String username){
        boolean isCorrect = false;
        for(Account person : twitterUsers){
            if(username.equals(person.getUserName())){
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    /***
     * this method will help us to follow and unfollow users and show following users
     * @param user
     * @return
     */

    public Account mainMenu(Account user){

        while (1==1){
            System.out.println("\n1)search\n2)my Following List\n3)my TimeLine\nEny Other Keyboard : Exit");
            showingTable.showTimeLine(user.getUserName());
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equals("1")){
                user = search(user);
                showingTable.showTimeLine(user.getUserName());
            }

            else if(choice.equals("2")){
                user.printFollowing();
                showingTable.showTimeLine(user.getUserName());
            }

            else if(choice.equals("3")){
                while (true){
                    int index = 1;
                    showingTable.showTimeLine(user.getUserName());
                    for (Twitt t : showingTable.allTwitts) {
                        System.out.println(index+") "+t.toString());
                        index++;
                    }
                    System.out.println("enter the number of the tweet you want to react:");
                    Scanner in2 = new Scanner(System.in);
                    int num = in2.nextInt() - 1;
                    System.out.println("\n1)Reply\n2)Retweet\n3)Like\nEny Other Keyboard : Exit");
                    Scanner in = new Scanner(System.in);
                    String choice2 = in.nextLine();
                    if (choice2.equals("1")){
                        Account account = tweetingService.reply(user.getUserName(),showingTable.allTwitts.get(num).getUsername(),showingTable.allTwitts.get(num).getText());
                        update(account);
                        showingTable.showTimeLine(user.getUserName());
                    }
                    else if (choice2.equals("2")){
                        Account account = tweetingService.retwitt(user.getUserName(),showingTable.allTwitts.get(num).getUsername(),showingTable.allTwitts.get(num).getText());
                        update(account);
                        showingTable.showTimeLine(user.getUserName());
                    }
                    else if (choice2.equals("3")){
                        Account account = tweetingService.Like(showingTable.allTwitts.get(num),user.getUserName(),showingTable.allTwitts.get(num).getUsername());
                        update(account);
                        showingTable.showTimeLine(user.getUserName());
                    }
                    else break;
                }
                showingTable.showTimeLine(user.getUserName());
            }

            else {
                break;
            }
        }
        return user;
    }

    /**
     * this method will help us to delete on specific account
     * @param user --> deleted account
     */

    public void removeAccount(Account user){

        String usernameFollow = user.getUserName();
        Iterator<Account> it = twitterUsers.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (usernameFollow.equals(account.getUserName())) {
                it.remove();

            }
        }
    }
    public void update(Account user){
        Iterator<Account> it = twitterUsers.iterator();
        Iterator<Account> it2 = showingTable.users.iterator();
        Iterator<Account> it3 = tweetingService.users.iterator();
        while (it.hasNext() && it2.hasNext() && it3.hasNext()){
            Account u = it.next();
            Account u2 = it2.next();
            Account u3 = it3.next();
            if (user.getUserName().equals(u.getUserName()) && user.getUserName().equals(u2.getUserName()) && user.getUserName().equals(u3.getUserName())){
                it.remove();
                it2.remove();
                it3.remove();
                twitterUsers.add(user);
                showingTable.users.add(user);
                tweetingService.users.add(user);
                break;
            }
        }
    }
}