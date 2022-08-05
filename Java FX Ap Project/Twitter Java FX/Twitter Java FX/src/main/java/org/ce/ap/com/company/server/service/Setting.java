package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * thisclass will show and handle setting --> delete account or edit tweet or account
 */
public class Setting {

    private ArrayList<Account> twitterUsers = new ArrayList<>(); //this array will save all sign up users
    private final AccountFile usersFileManger = new AccountFile();
    private final TweetFile tweetFile = new TweetFile();

    /**
     * setting method will help user to edit or remove one part of account
     */
    public Account setting(Account user, ClientHandler handler){

        while (true){
            updateUsers();
            String deletedAccount = user.getName();
            if(deletedAccount.equals("del 1")){
                break;
            }
            else{
                handler.outputStream("Setting :\n1)Edit Profile details\n2)Edit Tweet \n3)remove account\nEny Other Keyboard : EXIT");
                String choice = handler.inputStream();
                if(choice.equals("1")){
                    while (true){
                        handler.outputStream("Please choose one field :\n1)name\n2)lastName\n3)Password\n4)BirthDayDate\n5)Bio\nEny Other Keyboard : EXIT");
                        choice = handler.inputStream();
                        String change;
                        if(choice.equals("1")){
                            handler.outputStream("Please enter your new Name:");
                            change = handler.inputStream();
                            if (stringCheck(change)) {
                                user.setName(change);
                                usersFileManger.userUpdate(user);
                                break;
                            }else {
                                handler.outputStream("Error !\n Input name is incorrect due to incorrect characters or numbers\n");
                            }
                        }

                        if(choice.equals("2")){
                            handler.outputStream("Please enter your new Last Name:");
                            change = handler.inputStream();
                            if (stringCheck(change)) {
                                user.setLastName(change);
                                usersFileManger.userUpdate(user);
                                break;
                            }else{
                                handler.outputStream("Error !\nEntered Last Name is incorrect due to incorrect characters or numbers\n");
                            }
                        }

                        if(choice.equals("3")){
                            handler.outputStream("Please enter your new password:");
                            change = handler.inputStream();
                            boolean check = passwordCheck(change,handler);
                            if (check && passwordQualityCheck(change ,handler)) {
                                //user.setPassword(change,handler);
                                usersFileManger.userUpdate(user);
                                break;
                            } else if(!check){
                                handler.outputStream("Error !\n passwords did not match together\n");
                            }
                        }

                        if(choice.equals("4")){
                            user.setBirthDayDate(birthdayCheck(handler));
                            usersFileManger.userUpdate(user);
                        }

                        if(choice.equals("5")){

                            handler.outputStream("Please write your new Bio:");
                            change = handler.inputStream();
                            if (bioCheck(change)) {
                                usersFileManger.userUpdate(user);
                                break;
                            } else {
                                handler.outputStream("Error !\n Bio len is more than 256 char limit\n");
                            }
                        }
                        else {
                            break;
                        }
                    }

                }


                else if(choice.equals("2")){
                    int i = 1;
                    for (Tweet t : user.tweets) {
                        handler.outputStream(i+")"+t.toString());
                        i++;
                    }
                    handler.outputStream("Please type the number of the tweet you want to edit here:");
                    int index = Integer.parseInt(handler.inputStream());
                    handler.outputStream("Please type your edited tweet here:");
                    String text = handler.inputStream();
                    Tweet tweet = user.tweets.get(index-1);
                    Tweet updated = user.tweets.get(index-1).setText(text,handler);
                    user.tweets.remove(index-1);
                    user.tweets.add(index-1,updated);
                    tweetFile.writeAnUpdatedTweetToFile(updated,tweet.getText());
                    usersFileManger.userUpdate(user);
                }

                else if(choice.equals("3")){
                    String usernameFollow = user.getUserName();
                    Iterator<Account> it = twitterUsers.iterator();
                    while (it.hasNext()) {
                        Account account = it.next();
                        if (usernameFollow.equals(account.getUserName())) {

                            handler.outputStream("------------------------------------------------------------------\n"+account.toString()+"------------------------------------------------------------------\n"+"Are you sure?\n1) yes\netc.No");

                            String sure = handler.inputStream();

                            if (sure.equals("1")) {

                                it.remove();
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

    /**
     * Here we check that our string does not contain  a number or incorrect character.
     * ascii base ...
     * @param text --> checked str
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

    /**
     * Here we check password again .
     * @param password -
     * @return toCheck (false --> not similar)
     */
    public boolean passwordCheck(String password,ClientHandler handler) {

        boolean toCheck = true;
        handler.outputStream("please write your password again(equal check): ");
        String secondPassword = handler.inputStream();
        if (!password.equals(secondPassword) ){
            toCheck = false;
        }

        return toCheck;
    }

    /**
     * Here we check password quality for user secure
     * @param password -
     */
    public boolean passwordQualityCheck(String password,ClientHandler handler) {

        boolean toCheckQuality = true;
        int lowercaseRate=0;
        int NumericRate=0;
        int shapeRate=0;
        int uppercaseRate=0;

        for(Character character : password.toCharArray()){
            for(char charCheck = 'a' ; charCheck<'{';charCheck++){
                if(character == charCheck){
                    lowercaseRate = 20;
                }
            }

            for(char charCheck = 'A' ; charCheck<'[';charCheck++){
                if(character == charCheck){
                    uppercaseRate = 20;
                }
            }

            for(char charCheck = '1' ; charCheck<'9';charCheck++){
                if(character == charCheck){
                    NumericRate = 20;
                }
            }

            for(char charCheck = '!' ; charCheck<'/';charCheck++){
                if(character == charCheck){
                    shapeRate = 20;
                }
            }
        }

        int sumCheck = uppercaseRate + lowercaseRate + shapeRate + NumericRate;
        if (sumCheck < 60){
            toCheckQuality = false;
            String CheckQuality = "Error --> your password secure quality is %"+sumCheck+" and its low!\nplease try it again:\n";
            handler.outputStream(CheckQuality);
        }
        else {
            String CheckQuality = "--> \"Twitter\"\nyour password secure quality is %"+sumCheck+"  and its acceptable\n";
            handler.outputStream(CheckQuality);
        }

        return toCheckQuality;
    }

    /**
     * Bio check will check the length of user Bio
     * @param bio -
     * @return to check (false --> Bio len is more than 256 char limit )
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

    public String birthdayCheck(ClientHandler handler){
        String date = "";
        int year = 0;
        int month = 0;
        int day = 0;


        while (true){
            try {
                handler.outputStream("Please enter your year of birth:");

                year = Integer.parseInt(handler.inputStream());

                if(year>0 && year<2008){
                    date += year + " - ";
                    break;
                }
                else if(year>=2009){
                    handler.outputStream("Error --> You can not register due to being under the age of majority !");
                }
            }
            catch (Exception error){
                handler.outputStream("Error --> please enter number");
            }
        }

        while (true){
            try {
                handler.outputStream("Please enter your month of birth:");

                month = Integer.parseInt(handler.inputStream());

                if(month>0 && month<10){
                    date+="0" + month + " - ";
                    break;
                }
                if(month>9 && month<=12){
                    date += month + " - ";
                    break;
                }
                else {
                    handler.outputStream("Error --> We dont have more than 12 month in on year !");
                }
            }
            catch (Exception error){
                handler.outputStream("Error --> please enter number");
            }

        }


        while (true){
            try {
                handler.outputStream("Please enter your birthDay:");

                day = Integer.parseInt(handler.inputStream());

                if(day<0){

                    handler.outputStream("Error !");
                }
                else if(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){

                    if(day<32){

                        if(day<10){
                            date+="0" + day;
                            break;
                        }

                        else if(day>9){
                            date+=String.valueOf(day);
                            break;
                        }
                    }

                    else {
                        String DateCheck = "Error-->" + month + " th month have only 31 days";
                        handler.outputStream(DateCheck);
                    }
                }
                else if(month==11 || month == 9|| month == 6 || month == 4 ){
                    if(day<31){
                        if(day<10){
                            date+="0" + day;
                            break;
                        }
                        else if(day>9){
                            date+=String.valueOf(day);
                            break;
                        }
                    }
                    else {
                        String DateCheck = "Error -->"+month + " th month have only 30 days";
                        handler.outputStream(DateCheck);
                    }
                }
                else {
                    if (day < 10) {
                        date += "0" + day;
                        break;
                    } else if (day > 9) {
                        date += String.valueOf(day);
                        break;
                    } else if (day > 29) {
                        String DateCheck ="Error-->"+ month + " th month have only 29 days";
                        handler.outputStream(DateCheck);
                    }
                }
            }
            catch (Exception error){
                handler.outputStream("Error --> please enter number");
            }

        }

        return date;

    }

    /**
     * this method will update users list
     */
    public void updateUsers(){
        twitterUsers.clear();
        twitterUsers.addAll(usersFileManger.AllUsers());
    }
}