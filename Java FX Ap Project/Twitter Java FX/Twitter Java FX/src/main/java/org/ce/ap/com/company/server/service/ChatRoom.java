package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Account;

import java.util.ArrayList;

/**
 * chatroom class for handling chats
 * @author ABTIN ZANDI
 */
public class ChatRoom extends ChatRoomFile {

    ArrayList<String> Title = new ArrayList<>(); // ChatRoom Titles

    /**
     * this method will help us to show all titles
     * @param handler
     */
    public void getTitles(ClientHandler handler) {
        String showAllTitles = "";
        for(String title : Title){
            showAllTitles += title+"\n";
        }
    }

    public void setTitle(String title) {
        Title.add(title);
    }

    /**
     * this method will add a new chat room to server
     * @param handler
     */
    public void chatBuilder(ClientHandler handler){
        handler.outputStream("Please Enter the name of Chatroom:");
        String title = handler.inputStream();
        if(SearchChatroom(title)){
            handler.outputStream("--> \"Twitter\"\nChat room("+title+") is already exist");
        }
        else{
            NewChatRoom(title);
            setTitle(title);
            handler.outputStream("--> \"Twitter\"\nChat room("+title+") Have been created Successfully");
        }
    }

    /***
     * this method will help us to Join to chat ...
     */
    public void JoinChat(Account user , ClientHandler handler){
        handler.outputStream("Please Enter the name of Chatroom:");
        String title = handler.inputStream();
        if(!SearchChatroom(title)){
            handler.outputStream("--> \"Twitter\"\nChat room("+title+") isnt already exist");
        }
        else{
            chatRoom(user , handler,title);
        }

    }

    /**
     * this method will help us to chat in specific chat room
     * @param user
     * @param handler
     * @param title
     */
    public void chatRoom(Account user , ClientHandler handler,String title){
        boolean Refresh = false;
        String chatMassages = chatDetails(title);
        while (true){
            handler.outputStream("--> \"Twitter\"\nChat room("+title+") :\n"+chatDetails(title));
            handler.outputStream("\n1)Refresh\n2)new massage\nEny Other Keyboard : Exit");
            String choice = handler.inputStream();
            if (choice.equals("1")) {
                Refresh = true;
            } else if (choice.equals("2")) {
                handler.outputStream("Please Write your new massage:");
                String massage = handler.inputStream();

                UpdateChatRoom(user.getUserName()+": " + massage,title);
            }
            else{
                break;
            }
        }

        if(Refresh){
            chatRoom(user ,handler,title);
        }
    }


}
