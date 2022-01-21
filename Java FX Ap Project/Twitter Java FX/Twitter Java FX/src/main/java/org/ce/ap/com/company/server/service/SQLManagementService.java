package org.ce.ap.com.company.server.service;

import java.util.Scanner;

/**
 * SQLManagementService will help us to see all users and tweets information
 * @author ABTIN Zandi
 * @version 1.0
 */
public class SQLManagementService {

    public static void main(String[] args) {

        SQLHandler sqlHandler = new SQLHandler();
        while (true){
            System.out.println("Welcome to the Twitter SQL management Service\n1) All Users\n2)All Tweets\nPress eny Key : Exit");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equals("1")){
                sqlHandler.allAccounts();
            }
            else if(choice.equals("2")){
                sqlHandler.allTweets();
            }
            else {
                break;
            }

        }

    }
}
