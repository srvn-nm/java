package com.example.twitterjavafx;

import org.ce.ap.com.company.server.model.Account;

import java.io.*;
import java.util.ArrayList;

/**
 * this class will help us to control chats file
 */

public class ChatRoomFile {

    private String propertiesPath = "./src/resources/chatroom properties/chatroom.txt";

    /**
     * This method will write a Text CHATROOM to a new file.
     * @param name
     */
    public void NewChatRoom(String name){
        String  path ="./files/model/chatroom/"+name+".txt";
        File file = new File(path);
        setPropertiesOfUsers(path,true);
    }

    /**
     * this method will add a new path for CHATROOM
     * @param path
     */
    public void setPropertiesOfUsers(String path,boolean append){
        File propertiesFile = new File(propertiesPath);
        if(append){
            try(FileWriter fileOutputStream = new FileWriter(propertiesFile,true)){
                String temp = "\r\n";
                fileOutputStream.write(path+temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try(FileOutputStream fileOutputStream = new FileOutputStream(propertiesFile); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
                objectOutputStream.writeObject(path);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This method will write a Text CHATROOM to a new file.
     * @param  ChatroomText
     * @param name
     */
    public void UpdateChatRoom(String ChatroomText,String name){
        String  path ="./files/model/chatroom/"+name+".txt";
        File file = new File(path);
        try(FileWriter fileOutputStream = new FileWriter(file,true)){
            fileOutputStream.write(ChatroomText+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /***
     * this method will return all users address on user properties file
     * @return
     */
    public boolean SearchChatroom(String name){

        String  path ="./files/model/chatroom/"+name+".txt";
        boolean ChatExist = false;
        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String Address ;
            while ((Address = propertiesAddress.readLine()) != null){
                if(path.equals(Address)){
                    ChatExist = true   ;
                }
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }

        return ChatExist;
    }

    public ArrayList <String> chatDetails(String name){
        String  path ="./files/model/chatroom/"+name+".txt";
        ArrayList<String> chats= new ArrayList<>();
        File file = new File(path);
        try(BufferedReader chat = new BufferedReader(new FileReader(file))){
            String str ;
            while ((str = chat.readLine()) != null){
                chats.add(str);
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }
        return chats;

    }
    /**
     * this method will remove a file with given details
     * @param deletedAccount
     */
    public void removeFile(Account deletedAccount){
        String  path ="./files/model/users/"+deletedAccount.getUserName()+".txt";
        File file = new File(path);
        file.delete();
        removePropertiesOfUsers(path);
    }

    /**
     * this method will delete a path of PropertiesOfUsers file
     * @param path
     */
    public void removePropertiesOfUsers(String path){
        File propertiesFile = new File(propertiesPath);
        ArrayList<String> usersAddress = new ArrayList<>();

        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String Address ;
            while ((Address = propertiesAddress.readLine()) != null){
                if(!Address.equals(path)){
                    usersAddress.add(Address);
                }
            }
            setPropertiesOfUsers(usersAddress.get(0),false);
            for(int index = 1 ; index < usersAddress.size() ; index-=-1){
                setPropertiesOfUsers(usersAddress.get(index),true);
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }
    }

}