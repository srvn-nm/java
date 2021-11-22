package com.company;

import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("0.Return\n1.Cargo list\n2.Client list\n3.Set a cargo\n4.Add a client\n5.Turn off");
    }

    public static void menu1() {
        System.out.println("0.Return\nOr type the name of each client to see the cargo list of them:  ");
    }
    public static void menu2(){System.out.println("0.Reurn\nOr type the name of each cargo to see the details of them: ");}
    public static void main(String[] args) {
        FreightAgency fa = new FreightAgency();
        Scanner in = new Scanner(System.in);
        Scanner in1 = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        System.out.println("Hello.\nHope you'll enjoy working with us.^-^");
        menu();
        String choice = in.next();
        while (true) {
            switch (choice) {
                case "0":
                    menu();
                    choice = in.next();
                    break;
                case "1":
                    fa.showCargoList();
                    menu();
                    choice = in.next();
                    break;
                case "2":
                    fa.showClientList();
                    menu1();
                    String choice1 = in1.next();
                    while (true) {
                        if (choice1.equals("0")) {
                            menu();
                            choice = in.next();
                            break;
                        } else {
                            boolean test = false;
                            for (Client c : fa.clients) {
                                if (c.toString().equals(choice1)) {
                                    c.showCargoList();
                                    test = true;
                                }
                            }
                            if (!test) {
                                System.out.println("Invalid input!\nTry again:");
                                choice1 = in1.next();
                                break;
                            }
                            if (test)
                                break;
                        }
                        menu2();
                        String choice2 = in2.next();
                        while (true) {
                            if (choice2.equals("0")) {
                                menu1();
                                choice1 = in.next();
                                break;
                            } else {
                                Client client = null;
                                for (Client c : fa.clients) {
                                    if (c.toString().equals(choice1)) {
                                        client = c;
                                    }
                                }
                                if (client != null) {
                                    boolean test = false;
                                    for (String s : fa.clients.get(fa.clients.indexOf(client)).cargoes.keySet()) {
                                        if (s.equals(choice2)) {
                                            System.out.println(fa.clients.get(fa.clients.indexOf(client)).cargoes.get(s).toString());
                                            test = true;
                                        }
                                    }
                                    if (!test) {
                                        System.out.println("Invalid input!\nTry again:");
                                        choice2 = in2.next();
                                        break;
                                    }
                                    if (test)
                                        break;
                                }
                            }
                        }
                    }
                    menu();
                    choice = in.next();
                    break;
                case "3":
                    System.out.println("Please enter the first name of the client:");
                    String fn = in3.next();
                    System.out.println("Please enter the last name of the client:");
                    String ln = in3.next();
                    Client c = new Client(fn, ln);
                    fa.addClients(c);
                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                    System.out.println("Please type the code of origin according to the table above:");
                    int origin = in3.nextInt();
                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                    System.out.println("Please type the code of destination according to the table above:");
                    int destination = in3.nextInt();
                    System.out.println("Please type the weight of cargo:");
                    float weight = in3.nextFloat();
                    System.out.println("If you want insurance type Yes, else type No:");
                    boolean insurance = false;
                    String string = in3.next();
                    if (string.equals("Yes"))
                        insurance = true;
                    System.out.println("1)Air | 2)Sea | 3)Breakable Ground | 4) Fast Ground | 5) Normal Ground");
                    System.out.println("Please type the number of the type of transportation you want to add according to the table above:");
                    String choice3 = in3.next();
                    switch (choice3) {
                        case "1" -> {
                            AirCargo a = new AirCargo(insurance, weight);
                            while (true) {
                                if (a.setOrigin(origin)) {
                                    a.setOrigin(origin);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of origin according to the table above. Last one was incorrect:");
                                    origin = in3.nextInt();
                                }
                            }
                            while (true) {
                                if (a.setDestination(destination)) {
                                    a.setDestination(destination);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of destination according to the table above. Last one was incorrect:");
                                    destination = in3.nextInt();
                                }
                            }
                            a.setDistance();
                            a.setPrice();
                            c.addCargo(a);
                            fa.addCargo(a);
                            System.out.println("Your cargo is registered with the total price " + a.getPrice());
                        }
                        case "2" -> {
                            SeaCargo s = new SeaCargo(insurance, weight);
                            while (true) {
                                if (s.setOrigin(origin)) {
                                    s.setOrigin(origin);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of origin according to the table above. Last one was incorrect:");
                                    origin = in3.nextInt();
                                }
                            }
                            while (true) {
                                if (s.setDestination(destination)) {
                                    s.setDestination(destination);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of destination according to the table above. Last one was incorrect:");
                                    destination = in3.nextInt();
                                }
                            }
                            s.setDistance();
                            s.setPrice();
                            c.addCargo(s);
                            fa.addCargo(s);
                            System.out.println("Your cargo is registered with the total price " + s.getPrice());
                        }
                        case "3" -> {
                            BreakableGroundCargo bg = new BreakableGroundCargo(insurance, weight);
                            while (true) {
                                if (bg.setOrigin(origin)) {
                                    bg.setOrigin(origin);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of origin according to the table above. Last one was incorrect:");
                                    origin = in3.nextInt();
                                }
                            }
                            while (true) {
                                if (bg.setDestination(destination)) {
                                    bg.setDestination(destination);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of destination according to the table above. Last one was incorrect:");
                                    destination = in3.nextInt();
                                }
                            }
                            bg.setDistance();
                            bg.setPrice();
                            c.addCargo(bg);
                            fa.addCargo(bg);
                            System.out.println("Your cargo is registered with the total price " + bg.getPrice());
                        }
                        case "4" -> {
                            FastGroundCargo fg = new FastGroundCargo(insurance, weight);
                            while (true) {
                                if (fg.setOrigin(origin)) {
                                    fg.setOrigin(origin);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of origin according to the table above. Last one was incorrect:");
                                    origin = in3.nextInt();
                                }
                            }
                            while (true) {
                                if (fg.setDestination(destination)) {
                                    fg.setDestination(destination);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of destination according to the table above. Last one was incorrect:");
                                    destination = in3.nextInt();
                                }
                            }
                            fg.setDistance();
                            fg.setPrice();
                            c.addCargo(fg);
                            fa.addCargo(fg);
                            System.out.println("Your cargo is registered with the total price " + fg.getPrice());
                        }
                        case "5" -> {
                            NormalGroundCargo ng = new NormalGroundCargo(insurance, weight);
                            while (true) {
                                if (ng.setOrigin(origin)) {
                                    ng.setOrigin(origin);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of origin according to the table above. Last one was incorrect:");
                                    origin = in3.nextInt();
                                }
                            }
                            while (true) {
                                if (ng.setDestination(destination)) {
                                    ng.setDestination(destination);
                                    break;
                                } else {
                                    System.out.println("Hormoz:21 | Qeshm:20 | Hamedan:17 | Kerman:16 | Mashhad:13 | BandarAbbas:12 | Sari:11 | Tehran:10");
                                    System.out.println("Please type the code of destination according to the table above. Last one was incorrect:");
                                    destination = in3.nextInt();
                                }
                            }
                            ng.setDistance();
                            ng.setPrice();
                            c.addCargo(ng);
                            fa.addCargo(ng);
                            System.out.println("Your cargo is registered with the total price " + ng.getPrice());
                        }
                    }
                    menu();
                    choice = in.next();
                    break;
                case "4":
                    System.out.println("Please enter the first name of the client:");
                    String fn1 = in3.next();
                    System.out.println("Please enter the last name of the client:");
                    String ln1 = in3.next();
                    Client c1 = new Client(fn1, ln1);
                    fa.addClients(c1);
                    menu();
                    choice = in.next();
                    break;
                case "5":
                    System.out.println("Goodbye.\nIt was a pleasure working with you.^-^");
                    return;
            }
        }
    }
}