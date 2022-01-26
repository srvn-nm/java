package com.company.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.company.model.*;

/**
 * sever class will help us to manage and control all the twitter sections
 */
public class TwitterService {
    private TweetingService twitt;
    private TimeLine showingTable;
    private AuthenticationService service;//log in and sign up service
    private ObserverService browser;//searching service
    private ArrayList<Account> twitterUsers; //this array will save all sign up users

    /**
     *Server Constructor
     */
    public TwitterService(){
        service = new AuthenticationService();
        twitterUsers = new ArrayList<Account>();
        browser = new ObserverService();
        twitt = new TweetingService();
        showingTable = new TimeLine();
    }

    /**
     *log in server can help us to manage our account and use Twitter after log in ...
     */
    public Account serverLogIn(){

        Account user = service.logIn();
        String username = user.getUserName();
        boolean repeated = false;

        if(username.equals("You are not logged in. If you want you can re-login or register from the main menu\n--> \"Twitter\"")){
            System.out.println(username);
        }

        else{
            for(Account person : twitterUsers){
                if(username.equals(person.getUserName())){
                    mainMenu(user);
                    repeated = true;
                    break;
                }
            }
        }

        if(!repeated){
            twitterUsers.add(user);
            browser.addNewMember(user);
            twitt.users.add(user);
            showingTable.users.add(user);
            mainMenu(user);
        }
        return user;
    }

    /**
     * new Member will collaborate with signUp method for registration
     */
    public Account newMember(){
        Account user ;
        user = service.SignUp();
        twitterUsers.add(user);
        browser.addNewMember(user);
        twitt.users.add(user);
        showingTable.users.add(user);
        mainMenu(user);
        return user;
    }

    /**
     * this method is the main part of program Where the user can choose what he/she wants to do
     * collaborating with new twitt , myFollowingSystem ,search , setting
     * @param user
     */
    public void mainMenu(Account user){
        while (1==1) {
            String deletedAccount = user.getName();
            if (deletedAccount.equals("del 1")) {
                break;
            } else {
                System.out.println("Welcome to twitter\n1)new twitt \n2)following users\n3)My page view\n4)my twitts\n5)search\n6)Setting\n7)my time line\nEny Other Keyboard : log out");
                Scanner input = new Scanner(System.in);
                String choice = input.nextLine();
                Scanner in = new Scanner(System.in);
                if (choice.equals("1")) {
                    System.out.println("Please type your twitt here:");
                    String text = in.nextLine();
                    user = newTwitt(user, text);
                    update(user);
                } else if (choice.equals("2")) {
                    user.printFollowing();
                } else if (choice.equals("3")) {
                    System.out.println(user.toString());
                } else if (choice.equals("4")) {
                    int i = 1;
                    for (Twitt t : user.twitts) {
                        System.out.println(i+")"+t.toString());
                        i++;
                    }
                } else if (choice.equals("5")) {
                    user = search(user);
                    update(user);
                } else if (choice.equals("6")) {
                    user = setting(user);
                    update(user);
                }else if(choice.equals("7")){
                    showingTable.showTimeLine(user.getUserName());
                    System.out.println(showingTable.allTwitts);
                }else {
                    break;
                }
            }
        }

    }

    /**
     * This method will add anew twitt
     * @param user
     * @param text
     * @return user
     */
    public Account newTwitt(Account user,String text){
        user = twitt.twitt(user.getUserName(),text);
        update(user);
        return user;
    }

    /**
     * This method will search for a specific account
     * @param user
     * @return user
     */
    public Account search(Account user){

        user = browser.mainMenu(user);
        update(user);
        return user;
    }

    /**
     * setting method will help user to edit or remove one part of account
     */
    public Account setting(Account user){
        Scanner input = new Scanner(System.in);
        while (1==1){
            String deletedAccount = user.getName();
            if(deletedAccount.equals("del 1")){
                break;
            }
            else{
                System.out.println("Setting :\n1)Edit Profile details\n2)Edit Twitt \n3)remove account\nEny Other Keyboard : EXIT");
                String choice = input.nextLine();
                if(choice.equals("1")){
                    while (1==1){
                        System.out.println("Please choose one field :\n1)name\n2)lastName\n3)Password\n4)BirthDayDate\n5)Bio\nEny Other Keyboard : EXIT");
                        Scanner input1 = new Scanner(System.in);
                        choice = input1.nextLine();

                        Scanner inputChange = new Scanner(System.in);
                        String change = inputChange.nextLine();

                        if(choice.equals("1")){
                            System.out.print("Please enter your new Name:");
                            change = inputChange.nextLine();
                            if (stringCheck(change)) {
                                user.setName(change);
                                update(user);
                                break;
                            }else {
                                System.out.println("Error !\n Input name is incorrect due to incorrect characters or numbers\n");
                            }
                        }

                        if(choice.equals("2")){
                            System.out.print("Please enter your new Last Name:");
                            if (stringCheck(change)) {
                                user.setLastName(change);
                                update(user);
                                break;
                            }else{
                                System.out.println("Error !\nEntered Last Name is incorrect due to incorrect characters or numbers\n");
                            }
                        }

                        if(choice.equals("3")){
                            System.out.print("Please enter your new password:");
                            change = inputChange.nextLine();
                            if (passwordCheck(change)) {
                                user.setPassword(change);
                                update(user);
                                break;
                            } else {
                                System.out.println("Error !\n passwords did not match together\n");
                            }
                        }

                        if(choice.equals("4")){
                            user.setBirthDayDate(birthdayCheck());
                            update(user);
                        }

                        if(choice.equals("5")){

                            System.out.print("Please write your new Bio:");
                            change = inputChange.nextLine();
                            if (bioCheck(change)) {
                                update(user);
                                break;
                            } else {
                                System.out.println("Error !\n Bio len is more than 256 char limit\n");
                            }
                        }
                        else {
                            break;
                        }
                    }

                }

                else if(choice.equals("2")){
                    int i = 1;
                    for (Twitt t : user.twitts) {
                        System.out.println(i+")"+t.toString());
                        i++;
                    }
                    Scanner in = new Scanner(System.in);
                    System.out.println("Please type the number of the twitt you want to edit here:");
                    int index = in.nextInt();
                    System.out.println("Please type your edited twitt here:");
                    String text = in.nextLine();
                    user.twitts.remove(index-1);
                    user.twitts.add(index-1,user.twitts.get(index-1).setText(text));
                    update(user);
                }

                else if(choice.equals("3")){
                    String usernameFollow = user.getUserName();
                    Iterator<Account> it = twitterUsers.iterator();
                    while (it.hasNext()) {
                        Account account = (Account) it.next();
                        if (usernameFollow.equals(account.getUserName())) {

                            System.out.println("------------------------------------------------------------------");
                            System.out.print(account.toString());
                            System.out.println("------------------------------------------------------------------");
                            System.out.println("Are you sure?\n1) yes\netc.No");

                            Scanner inputSure = new Scanner(System.in);
                            String sure = inputSure.nextLine();

                            if (sure.equals("1")) {

                                it.remove();
                                browser.removeAccount(user);
                                service.removeAccount(user);
                                user.setName("del 1");
                                break;

                            } else {
                                break;
                            }
                        }
                    }
                }
                else {
                    break;
                }
            }
        }
        return user;
    }


    /***
     * Here we check that our string does not contain  a number or incorrect character.
     * ascii base ...
     * @param text
     * @return toCheck
     */
    public boolean stringCheck(String text) {
        boolean toCheck = true;
        for (Character character : text.toCharArray()) {
            int ascii = (int) character;
            if (ascii > 90 && ascii < 97) {
                toCheck = false;
                break;
            } else if (ascii > 128) {
                toCheck = false;
                break;
            } else if (ascii < 65) {
                toCheck = false;
                break;
            }
        }
        return toCheck;
    }

    /***
     * Here we check password again .
     * @param password
     * @return toCheck (false --> not similar)
     */
    public boolean passwordCheck(String password) {

        boolean toCheck = true;
        System.out.print("please write your password again(equal check): ");
        Scanner input = new Scanner(System.in);
        String secondPassword = input.nextLine();
        if (!password.equals(secondPassword) ){
            toCheck = false;
        }

        return toCheck;
    }

    /**
     * Bio check will check the length of user Bio
     * @param bio
     * @return tocheck (false --> Bio len is more than 256 char limit )
     */
    public boolean bioCheck(String bio) {
        boolean toCheck = true;
        if (bio.length() > 256 ){
            toCheck = false;
        }
        return toCheck;
    }

    /**
     * in this method we will check input date of user birthday
     * @return date of user birthday
     */
    public String birthdayCheck(){
        String date = "";
        int year = 0;
        int month = 0;
        int day = 0;
        Scanner input = new Scanner(System.in);

        while (true){
            try {
                System.out.println("Please enter your year of birth:");
                year = input.nextInt();
            }
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }

            if(year>0 && year<2008){
                date +=String.valueOf(year) + " - ";
                break;
            }
            else if(year>=2009){
                System.out.println("You can not register due to being under the age of majority !");
            }
        }

        while (true){
            try {
                System.out.println("Please enter your month of birth:");
                month = input.nextInt();
            }
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }

            if(month>0 && month<10){
                date+="0" + String.valueOf(month) + " - ";
                break;
            }
            if(month>9 && month<=12){
                date +=String.valueOf(month) + " - ";
                break;
            }
            else {
                System.out.println("We dont have more than 12 month in on year !");
            }
        }


        while (true){
            try {
                System.out.println("Please enter your birthDay:");
                day = input.nextInt();
            }
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }

            if(day<0){

                System.out.println("Error !");
            }
            else if(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){

                if(day<32){

                    if(day<10){
                        date+="0" + String.valueOf(day) + " - ";
                        break;
                    }

                    else if(day>9){
                        date+=String.valueOf(day);
                        break;
                    }
                }
                else {
                    System.out.printf("%d th month have only 31 days",month);
                }
            }
            else if(month==11 || month == 9|| month == 6 || month == 4 ){
                if(day<31){
                    if(day<10){
                        date+="0" + String.valueOf(day) + " - ";
                        break;
                    }
                    else if(day>9){
                        date+=String.valueOf(day);
                        break;
                    }
                }
                else {
                    System.out.printf("%d th month have only 30 days",month);
                }
            }
            else{
                if(day<10){
                    date+="0" + String.valueOf(day) + " - ";
                    break;
                }
                else if(day>9){
                    date+=String.valueOf(day);
                    break;
                }
                else if(day>29) {
                    System.out.printf("%d th month have only 29 days",month);
                }
            }
        }
        return date;
    }
    public void update(Account user){
        Iterator<Account> it = twitterUsers.iterator();
        Iterator<Account> it2 = showingTable.users.iterator();
        Iterator<Account> it3 = twitt.users.iterator();
        Iterator<Account> it4 = service.twitterUsers.iterator();
        while (it.hasNext() && it2.hasNext() && it3.hasNext() && it4.hasNext()){
            Account u = it.next();
            Account u2 = it2.next();
            Account u3 = it3.next();
            Account u4 = it4.next();
            if (user.getUserName().equals(u.getUserName()) && user.getUserName().equals(u2.getUserName()) && user.getUserName().equals(u3.getUserName()) && user.getUserName().equals(u4.getUserName())){
                it.remove();
                it2.remove();
                it3.remove();
                it4.remove();
                twitterUsers.add(user);
                showingTable.users.add(user);
                twitt.users.add(user);
                service.twitterUsers.add(user);
                browser.update(user);
                break;
            }
        }
    }
}