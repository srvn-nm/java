package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please type the binary code here : ");
        Scanner in = new Scanner(System.in);
        String key1 = in.next();
        int key = Integer.parseInt(key1,2);
        String data = in.next();
        String out = "";
        for (char s : data.toCharArray()){
            if(s != ' '){
                out = out.concat(Integer.toBinaryString(s^key));
                out = out.concat(" ");
            }
        }
        System.out.println("The Coded String :\n" + out);
    }
}
