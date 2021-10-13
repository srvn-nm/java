package com.company;
import java.util.*;
public class Main {
    public static boolean checker(String str1 , String str2){
        int replacements = 0 , editions = 0 , additions = 0 ,deletions = 0 , i = 0 , j = 0;
        char s1 = 0,s2 = 0;
        if(Math.abs(str1.length() - str2.length()) > 1)
            return false;
        while (i < str1.length() && j < str2.length()){
            if (str1.charAt(i) != str2.charAt(j)) {
                if (str1.length() > str2.length()) {
                    i++;
                    deletions++;
                }
                else if (str1.length() < str2.length()){
                    additions++;
                    j++;
                }
                else{
                    if (replacements == 0) {
                        s1 = str1.charAt(i);
                        s2 = str2.charAt(j);
                    } else if (s1 != str1.charAt(i) || s2 != str2.charAt(j))
                        return false;
                    replacements++;
                    i++;
                    j++;
                }
            }
            else {
                i++;
                j++;
            }
        }
        if (i < str1.length())
            deletions++;
        if (j < str2.length())
            additions++;
        editions = replacements + deletions + additions;
        if (editions == 1)
            return true;
        else if (editions == 0)
            return false;
            return replacements == editions;
    }
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
    System.out.println("Please type the first string here : ");
    String str1 = in.next();
    System.out.println("Please type the second string here : ");
    String str2 = in.next();
    System.out.println(checker(str1,str2));
      }
    }
