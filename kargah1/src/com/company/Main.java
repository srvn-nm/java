package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number1 = in.nextInt();
        int number2 = in.nextInt();
        int greatCommonFactor = 0;
        while(number2 != 0){
            if (number1 < number2){
                greatCommonFactor = number1;
                number1 =number2;
                number2 = greatCommonFactor;
            }
            greatCommonFactor = number2;
            number2 = number1 % number2;
            number1 = greatCommonFactor;
        }
        String output =  number1 == 1 ? "They are ralatively prime to eachother" : "They are not relatively prime with the greatest common factor " + number1;
        System.out.println(output);
    }
}
