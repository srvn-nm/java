package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Account;

import java.io.*;
import java.util.ArrayList;

/***
 * Account File class will help us to connect to our users files easily
 * @author Abtin Zandi
 */

public class AccountFile {

    SQLHandler handler = new SQLHandler();
    private final String propertiesPath = "./src/resources/users properties/userProperties.txt";

    /**
     * This method will write a user to a new file.
     * @param user --> new user
     */
    public void newUser(Account user){
        String  path ="./files/model/users/"+user.getUserName()+".txt";
        File file = new File(path);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPropertiesOfUsers(path,true);
        handler.NewUser(user);

    }

    /**
     * this method will add a new path for users
     * @param path --> properties path
     */
    public void setPropertiesOfUsers(String path,boolean append){
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
     * This method will update a user to a new file.
     * @param user --> updated user
     */
    public void userUpdate(Account user){
        String  path ="./files/model/users/"+user.getUserName()+".txt";
        File file = new File(path);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * this method will return all users address on user properties file
     * @return all users address
     */
    public ArrayList<String> getUsersAddress(){
        ArrayList<String> usersAddress = new ArrayList<>();

        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String Address ;
            while ((Address = propertiesAddress.readLine()) != null){
                usersAddress.add(Address);
                System.out.println(Address);
            }
        }
        catch (Exception error){
            System.out.println();
        }

        return usersAddress;
    }

    /***
     * this method will he;p us tp read all the users path and information for files
     * then AllUsers method will return an ArrayList of Accounts for twitter service ...
     * @return all users account
     */
    public ArrayList<Account> AllUsers(){
        ArrayList<String> usersAddress = new ArrayList<>(getUsersAddress());
        ArrayList<Account> AllAccounts = new ArrayList<>();
        for(String address : usersAddress){
            File file = new File(address);
            try(FileInputStream fileInputStream = new FileInputStream(file); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                try{
                    Account account = (Account) objectInputStream.readObject();
                    AllAccounts.add(account);

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println();
            }
        }

        return AllAccounts;
    }

    /**
     * this method will remove a file with given details
     * @param deletedAccount --> deleted Account
     */
    public void removeFile(Account deletedAccount){
        String  path ="./files/model/users/"+deletedAccount.getUserName()+".txt";
        File file = new File(path);
        boolean delete = file.delete();
        if(delete){
            removePropertiesOfUsers(path);
            handler.RemoveAccount(deletedAccount);
        }
    }

    /**
     * this method will delete a path of PropertiesOfUsers file
     * @param path --> Properties Of Users
     */
    public void removePropertiesOfUsers(String path){
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