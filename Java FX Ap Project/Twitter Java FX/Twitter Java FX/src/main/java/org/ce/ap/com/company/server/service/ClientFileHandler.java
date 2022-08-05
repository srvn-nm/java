package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Account;

import java.io.*;
import java.util.ArrayList;

/**
 * this class will help us to control the fxml files and clients status
 */
public class ClientFileHandler {

    //client status Properties

    /**
     * this method will open a new client status file
     * @param clientNumber ,
     */
    public void newClient(int clientNumber){
        String path ="./src/main/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
        File file = new File(path);
        try(FileWriter fileOutputStream = new FileWriter(file,false)){
            fileOutputStream.write("FirstMenu");
        } catch (IOException e) {
            System.out.println("1");
        }

    }

    /***
     * this method will help us to update client Status
     * @param clientNumber ,
     * @param NewStatus ,
     */
    public void updateClient(int clientNumber,String NewStatus ){
        String path ="./src/main/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
        File file = new File(path);
        try(FileWriter fileOutputStream = new FileWriter(file,false)){
            fileOutputStream.write(NewStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will get client status
     * @param clientNumber ,
     * @return client Status
     */
    public String getStatus(int clientNumber){
        String path ="./src/main/resources/ClientStatusProperties/"+"client-"+clientNumber+".txt";
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

    //Current Client
    /**
     * this  method will help us to get the client number
     * @return clientNumber
     */
    public int getCurrentClient(){
        String path ="./src/main/resources/currentClient/CurrentClients.txt";
        int ClientNumber = 0 ;
        File file = new File(path);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String ClientNum ;
            while ((ClientNum = propertiesAddress.readLine()) != null){
                ClientNumber = Integer.parseInt(ClientNum);
            }
        }
        catch (Exception error){
            System.out.println();
        }

        return ClientNumber;

    }

    /***
     * this method will set current client number
     * @param currentClient ,
     */
    public void CurrentClient(int currentClient){
        String path ="./src/main/resources/currentClient/CurrentClients.txt";
        File file = new File(path);
        String number = String.valueOf(currentClient);
        try(FileWriter fileOutputStream = new FileWriter(file,false)){
            fileOutputStream.write(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will return the int value of client number
     * @return number
     */
    public int CurrentClientNumber(String fxml){
        int number = 0 ;
        String path ="./src/main/resources/currentClient/"+fxml+".txt";
        File file = new File(path);
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

    /***
     * this method will update Fxml files for client
     * @param clientNumber ,
     * @param FxmlName ,
     * @param newMassage ,
     */
    public void updateServerFXML(int clientNumber,String FxmlName,String newMassage){
        String path ="./src/main/resources/ServerClientFxml/"+FxmlName+"-"+clientNumber+".txt";
        try(FileWriter fileOutputStream = new FileWriter(path,false)){
            fileOutputStream.write(newMassage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * this method will return Fxml details
     * @param clientNumber ,
     * @param FxmlName  ,
     * @return Fxml details
     */
    public ArrayList<String> getFXMLDetails(int clientNumber, String FxmlName){
        String path ="./src/main/resources/ServerClientFxml/"+FxmlName+"-"+clientNumber+".txt";
        ArrayList<String> FXMLDetails = new ArrayList<>();
        File file = new File(path);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String text ;
            while ((text = propertiesAddress.readLine()) != null){
                FXMLDetails.add(text);
            }
        }
        catch (Exception error){
            System.out.println();
        }
        return FXMLDetails;
    }

    /**
     * this method will set Fxml client number
     * @param FxmlName ,
     * @param clientNumber ,
     */
    public void setAllFxmlsStats(String FxmlName,int clientNumber){
        String path ="./src/main/resources/AllFxmlFiles/"+FxmlName+".txt";
        String number = String.valueOf(clientNumber);
        try(FileWriter fileWriter = new FileWriter(path,false)){
            fileWriter.write(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * this mehtod will get client number of fxml
     * @param FxmlName ,
     * @return fxml client number
     */
    public int getFxmlState(String FxmlName){
        String path = "./src/main/resources/AllFxmlFiles/"+FxmlName+".txt";
        int ClientNumber = 0 ;
        File file = new File(path);
        try(BufferedReader propertiesAddress = new BufferedReader(new FileReader(file))){
            String ClientNum ;
            while ((ClientNum = propertiesAddress.readLine()) != null){
                ClientNumber = Integer.parseInt(ClientNum);
            }
        }
        catch (Exception error){
            System.out.println();
        }

        return ClientNumber;
    }

    /**
     * this methods will help us to save Client Handler New Account
     * @param clientNumber ,
     * @param NewUser ,
     */
    public void NewUserAccount(int clientNumber, String NewUser){
        String path ="./src/main/resources/ClientAccounts/Account-"+clientNumber+".txt";
        try(FileWriter fileOutputStream = new FileWriter(path,false)){
            fileOutputStream.write(NewUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will return the Account UserName Of Client
     * @param clientNumber ,
     * @return the Account UserName Of Client
     */
    public String getUserAccount(int clientNumber){
        String path ="./src/main/resources/ClientAccounts/Account-"+clientNumber+".txt";
        File file = new File(path);
        String status = "" ;
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




}