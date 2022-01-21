package org.ce.ap.com.company.server.service;


import org.ce.ap.com.company.server.model.Account;
import org.ce.ap.com.company.server.model.Tweet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLHandler {

    private Connection SQLConnection = null; //SQL connection
    private Statement statement = null;
    private ResultSet Result = null;

    /**
     * this method will be able us to connect to our sql database file ...
     * @return sql connection
     */
    public Connection getSQLConnection() {
        String url = "jdbc:sqlite:usersManager.db";
        Connection connection = null;
        try{
            connection= DriverManager.getConnection(url);
        }catch (Exception error){
            error.printStackTrace();
        }
        return connection;
    }

    /**
     * this method will print all users details
     */
    public void allAccounts(){
        String SQLRes = "SELECT * FROM user";
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            Result = statement.executeQuery(SQLRes);
            while (Result.next()){
                System.out.println("---------------------------");
                System.out.println("NAME:"+Result.getString("UserName"));
                System.out.println("LAST NAME:"+Result.getString("UserLastName"));
                System.out.println("BIRTHDAY DATE:"+Result.getString("UseerBirthday"));
                System.out.println("SIGN UP DATE:"+Result.getString("UserSignUpDate"));
                System.out.println("BIO:"+Result.getString("UserBio"));
                System.out.println("USERNAME:"+Result.getString("UserUserName"));
                System.out.println("PASSWORD:"+Result.getString("UserPassword"));
            }
            try{
                Result.close();
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }

        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * this method will print all Tweets details
     */
    public void allTweets(){
        String SQLRes = "SELECT * FROM Tweets";
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            Result = statement.executeQuery(SQLRes);
            while (Result.next()){
                System.out.println("---------------------------");
                System.out.println("USERNAME:"+Result.getString("USERNAME"));
                System.out.println("TWEET:"+Result.getString("TWEETTEXT"));
                System.out.println("DATE:"+Result.getString("Time"));
            }
            try{
                Result.close();
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }

        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * this method will save a new account in sql lite
     * @param user --> NEW ACCOUNT
     */
    public void NewUser(Account user){

        String SQLRes = String.format("INSERT INTO user(UserName,UserLastName,UseerBirthday,UserSignUpDate,UserBio,UserUserName,UserPassword) VALUES('%s','%s','%s','%s','%s','%s','%s')",user.getName(),user.getLastName(),user.getBirthDayDate(),user.getSignUpDate(),user.getBio(),user.getUserName(),user.getPassword());
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            statement.execute(SQLRes);
            try{
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * this method will remove an account of sqllite table
     * @param user --> NEW ACCOUNT
     */
    public void RemoveAccount(Account user){

        String SQLRes = String.format("DELETE FROM user WHERE UserUserName = %s",user.getUserName());
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            statement.execute(SQLRes);
            try{
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }
        }catch (Exception error){
            error.printStackTrace();
        }

    }

    /**
     * this method will save a new Tweet in sqllite table
     * @param tweet --> NewTweet
     */
    public void NewTweet(Tweet tweet){

        String SQLRes = String.format("INSERT INTO Tweets(USERNAME,TWEETTEXT,Time) VALUES('%s','%s','%s')",tweet.getUsername(),tweet.getText(),tweet.getTime());
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            statement.execute(SQLRes);
            try{
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }
        }catch (Exception error){
            error.printStackTrace();
        }

    }

    /**
     * this method will remove an tweet of sqllite table
     * @param tweet --> NEW tweet
     */
    public void RemoveTweet(Tweet tweet){

        String SQLRes = String.format("DELETE FROM Tweets WHERE USERNAME = %s",tweet.getUsername());
        try{
            SQLConnection = getSQLConnection();
            statement = SQLConnection.createStatement();
            statement.execute(SQLRes);
            try{
                SQLConnection.close();
            }catch (Exception er){
                er.printStackTrace();
            }
        }catch (Exception error){
            error.printStackTrace();
        }

    }
}
