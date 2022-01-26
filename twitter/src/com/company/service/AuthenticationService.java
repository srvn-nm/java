package com.company.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDate;
import com.company.model.*;

/**
 * AuthenticationService class for get users information like :
 *             name , userName , Password , BirthDayDate , signUpDate , Bio , ...
 *             and sing up in twitter system
 *
 * AuthenticationService class will provide log in section too ...
 *
 *this class is collaborating with Account class which will help user to sign up or sign in
 * @author Abtin Zandi , Sarvin Nami
 * @version 3.0
 */

public class AuthenticationService {

    public ArrayList<Account> twitterUsers; //this array will save all sign up users

    /***
     * AuthenticationService constructor method
     */

    public AuthenticationService() {
        twitterUsers = new ArrayList<Account>();
    }


    /***
     * log in  system will return our user username to open user panel in twitter
     * @return NewUser or "You are not logged in. If you want you can re-login or register from the main menu\n\"Twitter\""
     */
    public Account logIn(){
        String userName ="";
        String password;

        Account NewUser = new Account("","","");

        boolean Exit = false;
        boolean finaExit = false;

        while (1==1){
            System.out.println("Please enter your Username : ");
            Scanner input = new Scanner(System.in);
            userName = input.nextLine();
            if(!usernameCheck(userName)){
                break;
            }
            else {
                System.out.println("Invalid Username !\n would you like to Sign Up ?\n1) Yes\n2) NO, Repeat again\nEny Other Keyboard : EXIT");
                String choice = input.nextLine();
                if(choice.equals("1")){
                    NewUser = SignUp();
                }
                else if(choice.equals("2")){
                    continue;
                }
                else {
                    Exit = true;
                    break;
                }
            }
        }

        if(!Exit){
            while (1==1){
                System.out.println("Please enter your password:");
                Scanner input = new Scanner(System.in);
                password = input.nextLine();
                if(passwordCheckForLogIn(password)){
                    NewUser.setUserName(userName);
                    break;
                }
                else {
                    System.out.println("Invalid Password !\n would you like to exit ?\n1) Yes\nEny Other Keyboard : try again");
                    String choice = input.nextLine();
                    if(choice.equals("1")){
                        finaExit = true;
                        break;
                    }
                }
            }
        }

        if(finaExit || Exit){
            NewUser.setName("You are not logged in. If you want you can re-login or register from the main menu\n--> \"Twitter\"");
            return NewUser;
        }

        else{
            return NewUser;
        }

    }
    /***
     * this class will provide the  signUp section
     * in this method we will check :
     * repeated userName
     * Password
     * Bio length
     * BirthDayDate
     *
     * #collebrating --> strCheck , PasswordCheck , usernameCheck
     */
    public Account SignUp() {

        System.out.println("Welcome to Twitter :\nPlease enter your Personal Information :");
        Scanner input = new Scanner(System.in);
        String name; // the name of user
        String lastName; // the name of user
        String userName; // the userName of user Account
        String password; // the Password  of user Account
        String birthDayDate; // the Birth day date of user
        LocalDate signUpDate = LocalDate.now(); // the signUp date of user
        String Bio; // the Biography of user

        while (1 == 1) {
            System.out.print("Name: ");
            name = input.nextLine();
            if (stringCheck(name)) {
                break;
            } else {
                System.out.println("Error !\n Input name is incorrect due to incorrect characters or numbers\n");
            }
        }

        while (1 == 1) {
            System.out.print("Lastname: ");
            lastName = input.nextLine();
            if (stringCheck(lastName)) {
                break;
            } else {
                System.out.println("Error !\n Input Lastname is incorrect due to incorrect characters or numbers\n");
            }
        }

        while (1 == 1) {
            System.out.print("userName: ");
            userName = input.nextLine();
            if (usernameCheck(userName)) {
                break;
            } else {
                System.out.println("Error !\n Input userName is repeated\n");
            }
        }

        while (1 == 1) {
            System.out.print("password: ");
            password = input.nextLine();
            boolean check = passwordCheck(password);
            if (check && passwordQualityCheck(password)) {
                break;
            } else {
                if(!check){
                    System.out.println("Error !\n passwords did not match together\n");
                }
            }
        }

        Account newUser = new Account(name,lastName,userName);
        newUser.setPassword(password);
        newUser.setSignUpDate(signUpDate);

        newUser.setBirthDayDate(birthdayCheck());
        while (1 == 1) {
            System.out.print("Bio: ");
            Bio = input.nextLine();
            if (bioCheck(Bio)) {
                break;
            } else {
                System.out.println("Error !\n Bio len is more than 256 char limit\n");
            }
        }
        newUser.setBio(Bio);

        twitterUsers.add(newUser);

        return newUser;
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


        while (true){
            try {
                System.out.println("Please enter your year of birth:");
                Scanner input = new Scanner(System.in);
                year = input.nextInt();

                if(year>0 && year<2008){
                    date +=String.valueOf(year) + " - ";
                    break;
                }
                else if(year>=2009){
                    System.out.println("You can not register due to being under the age of majority !");
                }
            }
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }
        }

        while (true){
            try {
                System.out.println("Please enter your month of birth:");
                Scanner input = new Scanner(System.in);
                month = input.nextInt();

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
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }

        }


        while (true){
            try {
                System.out.println("Please enter your birthDay:");
                Scanner input = new Scanner(System.in);
                day = input.nextInt();

                if(day<0){

                    System.out.println("Error !");
                }
                else if(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){

                    if(day<32){

                        if(day<10){
                            date+="0" + String.valueOf(day);
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
                            date+="0" + String.valueOf(day);
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
                else {
                    if (day < 10) {
                        date += "0" + String.valueOf(day);
                        break;
                    } else if (day > 9) {
                        date += String.valueOf(day);
                        break;
                    } else if (day > 29) {
                        System.out.printf("%d th month have only 29 days", month);
                    }
                }
            }
            catch (Exception error){
                System.out.println("Error --> please enter number");
            }

        }

        return date;

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
     * Here we check that our username is repeated or not .
     * @param password
     * @return toCheck (true --> repeated)
     */
    public boolean passwordCheckForLogIn(String password) {
        String Pass = null;
        try {
            Pass = toHexString(getSHA(password));
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        boolean toCheck = false;
        for (Account account : twitterUsers) {
            if (Pass.equals(account.getPassword())) {
                toCheck = true;
            }
        }
        return toCheck;
    }
    /**
     * method to get the sha of a string
     * @param input
     * @return md.digest(input.getBytes(StandardCharsets.UTF_8))
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called to calculate message digest of an input and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * method to convert a byte array a hexadecimal string value.
     * @param hash
     * @return hexString.toString()
     */
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /***
     * Here we check that our username is repeated or not .
     * @param userName
     * @return toCheck (false --> repeated)
     */
    public boolean usernameCheck(String userName) {

        boolean toCheck = true;
        for (Account account : twitterUsers) {
            if (userName.equals(account.getUserName())) {
                toCheck = false;
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


    /***
     * Here we check password quality for user secure
     * @param password
     */
    public boolean passwordQualityCheck(String password) {

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
            System.out.printf("Error --> your password secure quality is %d%% and its low!\nplease try it again:\n",sumCheck);
        }
        else {
            toCheckQuality = true;
            System.out.printf("your password secure quality is %d%% and its acceptable\n",sumCheck);
        }

        return toCheckQuality;
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
}