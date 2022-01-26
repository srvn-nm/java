package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Account;
import java.io.*;
import java.util.ArrayList;

public class ClentFileHandler {

    private final String propertiesPath = "./src/resources/ClientStatusProperties/ClientProperties.txt";

    public void newClient(int clientNumber){
        String path ="./src/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
        File file = new File(path);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject("FirstMenu.fxml");
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPropertiesOfUsers(path,true);
    }

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

    /***
     * this method will help us to update client Status
     * @param clientNumber ,
     * @param NewStatus ,
     */
    public void updateClient(int clientNumber,String NewStatus){
        String path ="./src/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
        try(FileWriter fileOutputStream = new FileWriter(path,false)){
            fileOutputStream.write(NewStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStatus(int clientNumber){
        String path ="./src/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
        File file = new File(path);
        String status = "FirstMenu.fxml" ;
        try(BufferedReader StatusFile = new BufferedReader(new FileReader(file))){
            String readString ;
            while ((readString = StatusFile.readLine()) != null){
                status =  readString;
            }
        }
        catch (Exception error){
            System.out.println();
        }

        return status;
    }

    /***
     * this method will set current client number
     * @param currentClient
     */
    public void CurrentClient(int currentClient){
        String path ="./src/main/resources/currentClient/CurrentClients.txt";
        try(FileWriter fileOutputStream = new FileWriter(path,false)){
            fileOutputStream.write(currentClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will return the int value of client number
     * @return number
     */
    public int CurrentClientNumber(){
        int number = 0 ;
        String path ="./src/main/resources/currentClient/CurrentClients.txt";
        File file = new File(propertiesPath);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String ClientNum ;
            while ((ClientNum = propertiesAddress.readLine()) != null){
                number = Integer.parseInt(ClientNum);
            }
        }
        catch (Exception error){
            System.out.println();
        }

        return number;
    }
}