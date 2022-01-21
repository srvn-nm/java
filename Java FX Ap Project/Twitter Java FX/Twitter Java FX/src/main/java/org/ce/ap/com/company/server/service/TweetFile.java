package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Tweet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/***
 * Tweet File class will help us to connect to our Tweets files easier
 * @author Sarvin Nami
 */
public class TweetFile{
    SQLHandler handler = new SQLHandler();
    private String propertiesPath = "./src/resources/tweet properties/tweetProperties.txt";
    /**
     * This method will write a tweet to a new file.
     */
    public void writeNewTweetToFile(Tweet tweet){
        int index = 0;
        String path;
        Tweet t = null;
        while (true){
            path ="./files/model/tweets/"+tweet.getUsername()+"-"+index+".txt";
            if (Files.exists(Paths.get(path))){
                File file = new File(path);
                try(FileInputStream fileInputStream = new FileInputStream(file);ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                    t = (Tweet) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (Files.exists(Paths.get(path)) && !t.getText().equals(tweet.getText()))
                index++;
            else if (Files.exists(Paths.get(path)) && t.getText().equals(tweet.getText()))
                return;
            else
                break;
        }
        File file = new File(path);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(tweet);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPropertiesOfTweets(path,true);
        handler.NewTweet(tweet);
    }

    /**
     * This method will read a tweet of a specific username with a specific text.
     * @param userName
     * @param text
     * @return read tweet
     */
    public Tweet readFromFile(String userName, String text){
        int index = 0;
        String path;
        Tweet t = null;
        while (true){
            path ="./files/model/tweets/"+userName+"-"+index+".txt";
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))){
                t = (Tweet) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!Files.exists(Paths.get(path)) || t.getText().equals(text))
                break;
            else
                index++;
        }
        return t;
    }

    /**
     * This method will update the property
     * @param path
     * @param append
     */
    public void setPropertiesOfTweets(String path,boolean append){
        File propertiesFile = new File(propertiesPath);
        boolean write = true;
        if(append){
            try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFile))){
                String Address ;
                while ((Address = reader.readLine()) != null){
                    if (Address.equals(path))
                        write = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (write){
                try(FileWriter fileOutputStream = new FileWriter(propertiesFile,true)){
                    String temp = "\r\n";
                    fileOutputStream.write(path+temp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            try(FileWriter fileOutputStream = new FileWriter(propertiesFile,false)){
                String temp = "\r\n";
                fileOutputStream.write(path+temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will update a specific tweet's file.
     * @param tweet
     * @param oldText
     */
    public void writeAnUpdatedTweetToFile(Tweet tweet, String oldText){
        int index = 0;
        String path;
        Tweet t = null;
        while (true){
            path ="./files/model/tweets/"+tweet.getUsername()+"-"+index+".txt";
            if (Files.exists(Paths.get(path))){
                File file = new File(path);
                try(FileInputStream fileInputStream = new FileInputStream(file);ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                    t = (Tweet) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (Files.exists(Paths.get(path)) && !t.getText().equals(oldText))
                index++;
            else if (Files.exists(Paths.get(path)) && t.getText().equals(oldText))
                break;
            else
                return;
        }
        File file = new File(path);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(tweet);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPropertiesOfTweets(path,true);
    }
    /***
     * this method will return all tweet's address on tweet properties file
     * @return userAddress
     */
    public ArrayList<String> getTweetsAddress(){
        ArrayList<String> tweetsAddress = new ArrayList<>();

        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String Address ;
            while ((Address = propertiesAddress.readLine()) != null){
                tweetsAddress.add(Address);
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }

        return tweetsAddress;
    }

    /**
     * This method will return all tweets of one user
     * @param userName
     * @return AllTweets
     */
    public ArrayList<Tweet> AllTweetsOfOneUser(String userName){
        ArrayList<String> TweetsAddress = new ArrayList<String>(getTweetsAddress());
        ArrayList<Tweet> AllTweets = new ArrayList<Tweet>();
        for(String path : TweetsAddress){
            File file = new File(path);
            try(FileInputStream fileInputStream = new FileInputStream(file); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                try{
                    Tweet t = (Tweet) objectInputStream.readObject();
                    if (t.getUsername().equals(userName))
                        AllTweets.add(t);

                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return AllTweets;
    }

    /**
     * this method will remove a file with given details
     * @param userName
     * @param text
     */
    public void removeFile(String userName,String text){
        String  path = null;
        Tweet t = null;
        for (String address:getTweetsAddress()) {
            File file = new File(address);
            try(FileInputStream fileInputStream = new FileInputStream(file); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                try{
                    t = (Tweet) objectInputStream.readObject();
                    if (t.getUsername().equals(userName) && t.getText().equals(text))
                        path = address;

                }catch (Exception e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(path);
        file.delete();
        removePropertiesOfTweets(path);
        handler.RemoveTweet(t);
    }

    /**
     * this method will delete a path of PropertiesOfTweets file
     * @param path
     */
    public void removePropertiesOfTweets(String path){
        ArrayList<String> tweetsAddress = new ArrayList<>();

        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String Address ;
            while ((Address = propertiesAddress.readLine()) != null){
                if(!Address.equals(path)){
                    tweetsAddress.add(Address);
                }
            }
            setPropertiesOfTweets(tweetsAddress.get(0),false);
            for(int index = 1 ; index < tweetsAddress.size() ; index-=-1){
                setPropertiesOfTweets(tweetsAddress.get(index),true);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}