package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        while (true){
            System.out.println("Twitter :\n1) Log In\n2) Sign Up\nEny Other Keyboard : EXIT");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equals("1")){
                server.serverLogIn();
            }

            else if(choice.equals("2")){
                server.newMember();
            }

            else {
                break;
            }
        }
    }
}