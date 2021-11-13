package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Basket basket = new Basket();

        String[] categories = {"Vegetables", "Fruits", "Grains", "Seafood", "Meat", "Dairy", "Eggs"};

        Product product1 = new Product("Carrot", categories[0], 5, 20.0, LocalDate.parse("15-3-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("15-3-2021", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product2 = new Product("Apple", categories[1], 10, 50.0, LocalDate.parse("01-4-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("01-8-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product3 = new Product("12xEgg", categories[6], 100, 40.0, LocalDate.parse("01-1-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("01-6-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product4 = new Product("Oats", categories[2], 70, 100.0, LocalDate.parse("01-6-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("01-1-2021", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product5 = new Product("Salmon", categories[3], 150, 250.0, LocalDate.parse("01-1-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("01-2-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product6 = new Product("Stake", categories[4], 800, 1000.0, LocalDate.parse("01-3-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("01-9-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product7 = new Product("Milk", categories[5], 100, 20.0, LocalDate.parse("10-1-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("25-1-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));
        Product product8 = new Product("Cheese", categories[5], 150, 10.0, LocalDate.parse("01-2-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")), LocalDate.parse("15-3-2020", DateTimeFormatter.ofPattern("dd-M-yyyy")));

        inventory.addProduct(product1, 10);
        inventory.addProduct(product2, 50);
        inventory.addProduct(product3, 20);
        inventory.addProduct(product4, 45);
        inventory.addProduct(product5, 5);
        inventory.addProduct(product6, 5);
        inventory.addProduct(product7, 20);
        inventory.addProduct(product8, 50);

        loop(inventory, basket);
    }

    private static void loop(Inventory inventory, Basket basket) {
        inventory.printAllProducts();
        Scanner input = new Scanner(System.in);

        System.out.println("\nHello, How can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");

        String in = input.nextLine();
        while (true) {
            String[] command = in.split(" ");
            switch (command[0]) {
                case "add"-> {
                    if (command.length != 2) {
                        System.out.println("Invalid input!");
                    } else {
                        Product temp = inventory.getProduct(Integer.parseInt(command[1])-1);
                        if (temp == null) {
                            System.out.println("Product doesnt exist!");
                        } else {
                            basket.addProduct(temp);
                            inventory.updateStock(temp, inventory.getProducts().get(temp)-1);
                            System.out.println("Product successfully added to your cart. ^-^");
                        }
                    }
                    System.out.println("\nHow can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");
                }
                case "remove" -> {
                    if (command.length != 2) {
                        System.out.println("Invalid input!");
                    } else {
                        Product temp = basket.getProduct(Integer.parseInt(command[1])-1);
                        if (temp != null){
                            basket.removeProduct(temp);
                            inventory.updateStock(temp, inventory.getProducts().get(temp)+1);
                        }
                        else if (basket.getProducts().size() == 0)
                            System.out.println("List is empty.");
                        else
                            System.out.println("Invalid input!\nYou have just " + (basket.getProducts().size() + 1) + " products in your cart.");
                    }
                    System.out.println("\nHow can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");
                }
                case "cart" -> {
                    if (command.length != 1) {
                        System.out.println("Invalid input!");
                    } else {
                        if (basket.getProducts().size() == 0)
                            System.out.println("Cart is empty >-<");
                        else {
                            System.out.println("Items in cart: ");
                            basket.printAllProducts();
                        }
                    }
                    System.out.println("\nHow can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");
                }
                case "balance" -> {
                    if (command.length != 1) {
                        System.out.println("Invalid input!");
                    } else {
                        System.out.println("Basket Balance: " + basket.balance());
                    }
                    System.out.println("\nHow can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");
                }
                case "products" -> {
                    if (command.length != 1) {
                        System.out.println("Invalid input!");
                    } else {
                        if (inventory.getProducts().keySet().size() == 0)
                            System.out.println("We are out of stock!");
                        else
                            inventory.printAllProducts();
                    }
                    System.out.println("\nHow can I help you?\nadd\nremove\ncart\nbalance\nproducts\ncheckout");
                }
                case "checkout" -> {
                    if (command.length != 1) {
                        System.out.println("Invalid input!");
                    } else {
                        System.out.println("It was a pleasure doing business with you. ^-^");
                        return;
                    }
                }
            }
            in = input.nextLine();
        }
    }
}