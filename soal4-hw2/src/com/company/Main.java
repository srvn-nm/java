package com.company;
import java.util.*;
public class Main {

    public static void menu(){
        System.out.println("\nHere you can choose what you want to do!\n1)print all production lines' information\n2)add a production line\n3)remove Production Line\n4)print All Storage Products\n5)add Product To Store\n6)remove Product From Store\n7)print Value Of Store\n8)Exit");
    }
    public static void main(String[] args) {
    FinancialSystem f = new FinancialSystem();
    String choice;
    Scanner in = new Scanner(System.in);
    menu();
    choice = in.next();
    while (true){
        switch (choice) {
            case "1" -> {
                f.printAllProductionLines();
                menu();
                choice = in.next();
            }
            case "2" -> {
                System.out.println("Please type the new production name here:");
                String LineName = in.next();
                System.out.println("Please type the product's name here:");
                String productName = in.next();
                System.out.println("Please type the product's price here:");
                int ProductPrice = in.nextInt();
                f.addProductionLine(LineName, productName, ProductPrice);
                System.out.println("Your changes successfully performed!");
                menu();
                choice = in.next();
            }
            case "3" -> {
                System.out.println("Please type the production name here:");
                String lineName = in.next();
                f.removeProductionLine(lineName);
                System.out.println("Your changes successfully performed!");
                menu();
                choice = in.next();
            }
            case "4" -> {
                f.printAllStorageProducts();
                menu();
                choice = in.next();
            }
            case "5" -> {
                System.out.println("Please type the product's name here:");
                String ProductName = in.next();
                System.out.println("Please type the product's price here:");
                int productPrice = in.nextInt();
                System.out.println("Please type the number of products to store here:");
                int ProductNumber = in.nextInt();
                f.addProductToStore(ProductName, ProductNumber, productPrice);
                System.out.println("Your changes successfully performed!");
                menu();
                choice = in.next();
            }
            case "6" -> {
                System.out.println("Please type the product's name here:");
                String Productname = in.next();
                System.out.println("Please type the number of products to remove here:");
                int Productnumber = in.nextInt();
                f.removeProductFromStore(Productname, Productnumber);
                System.out.println("Your changes successfully performed!");
                menu();
                choice = in.next();
            }
            case "7" -> {
                f.printValueOfStore();
                menu();
                choice = in.next();
            }
            case "8" -> {
                System.out.println("Have a Good time bookkeeper!\nGoodbye ^-^");
                return;
            }
        }
    }
    }
}