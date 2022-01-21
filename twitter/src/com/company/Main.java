package com.company;

import java.util.Scanner;
import com.company.service.*;

public class Main {

    public static void main(String[] args) {
        TwitterService twitterService = new TwitterService();
        while (true){
            System.out.println("Twitter :\n1) Log In\n2) Sign Up\nEny Other Keyboard : EXIT");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equals("1")){
                twitterService.serverLogIn();
            }

            else if(choice.equals("2")){
                twitterService.newMember();
            }

            else {
                break;
            }
        }
    }
}