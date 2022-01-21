package org.ce.ap.com.company.server.impl;

import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientHandler;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface AuthenticationService {
    public ArrayList<Account> twitterUsers = new ArrayList<Account>(); //this array will save all sign up users
    AccountFile usersFileManger = new AccountFile();
    /**
     * log in  system will return our user username to open user panel in twitter
     * @return NewUser or "You are not logged in. If you want you can re-login or register from the main menu\n\"Twitter\""
     */
    public Account logIn(ClientHandler handler);
    /**
     * this class will provide the  signUp section
     * in this method we will check :
     * repeated userName
     * Password
     * Bio length
     * BirthDayDate
     *
     * #collebrating --> strCheck , PasswordCheck , usernameCheck
     */
    public Account SignUp(ClientHandler handler);
    /**
     * in this method we will check input date of user birthday
     * @return date of user birthday
     */

    public String birthdayCheck(ClientHandler handler);
    /**
     * Here we check that our string does not contain  a number or incorrect character.
     * ascii base ...
     * @param text
     * @return toCheck
     */
    public boolean stringCheck(String text);
    /**
     * Here we check that our username is repeated or not .
     * @param password
     * @return toCheck (true --> repeated)
     */
    public boolean passwordCheckForLogIn(String password);
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
    public boolean usernameCheck(String userName);
    /**
     * Here we check password again .
     * @param password
     * @return toCheck (false --> not similar)
     */
    public boolean passwordCheck(String password,ClientHandler handler);
    /**
     * Here we check password quality for user secure
     * @param password
     */
    public boolean passwordQualityCheck(String password,ClientHandler handler);
    /**
     * Bio check will check the length of user Bio
     * @param bio
     * @return tocheck (false --> Bio len is more than 256 char limit )
     */
    public boolean bioCheck(String bio);
    /**
     * this method will help us to delete on specific account
     * @param user --> deleted account
     */

    public void removeAccount(Account user);
    /**
     * this method will update users list
     */
    public void updateUsers();
}
