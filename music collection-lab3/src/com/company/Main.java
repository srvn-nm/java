package com.company;

import java.util.Scanner;
/**
 * the Music Organizer program provides a simple organizing system which have 4 categories pop , jazz ,rock and country.
 *
 * @author Sarvin Nami
 */
public class Main {
    //this method shows the categories.
    public static void showMenu() {
        System.out.println();
        System.out.println("Choose your category : ");
        System.out.println();
        System.out.println("***************");
        System.out.println("* 1) Pop      *");
        System.out.println("* 2) Jazz     *");
        System.out.println("* 3) Rock     *");
        System.out.println("* 4) Country  *");
        System.out.println("* 5) Favorite *");
        System.out.println("* 6) Quit     *");
        System.out.println("***************");
        System.out.println();
    }

    //this method shows the main menu.
    public static void showMenu2() {
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ 1) Add a music                       +");
        System.out.println("+ 2) Remove a music                    +");
        System.out.println("+ 3) Number of musics in this category +");
        System.out.println("+ 4) search a music in the list (index)+");
        System.out.println("+ 5) search a music in the list (name) +");
        System.out.println("+ 6) All the musics in the list        +");
        System.out.println("+ 7) Play a music                      +");
        System.out.println("+ 8) Exit                              +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }

    public static void main(String[] args) {
        int year;
        String singer;
        String fileName;
        //making instance for each category.
        MusicCollection pop = new MusicCollection();
        MusicCollection jazz = new MusicCollection();
        MusicCollection rock = new MusicCollection();
        MusicCollection country = new MusicCollection();
        MusicCollection favorites = new MusicCollection();
        //an array to store categories.
        MusicCollection[] categories = new MusicCollection[5];

        categories[0] = pop;
        categories[1] = jazz;
        categories[2] = rock;
        categories[3] = country;
        categories[4] = favorites;

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Music Organizer system!");
        while (true) {
            showMenu();
            int i = 0;
            String name = "name";
            int inputMenu = scan.nextInt();
            switch (inputMenu) {
                case 1:
                    i = 0;
                    name = "POP";
                    break;
                case 2:
                    i = 1;
                    name = "JAZZ";
                    break;
                case 3:
                    i = 2;
                    name = "ROCK";
                    break;
                case 4:
                    i = 3;
                    name = "COUNTRY";
                    break;
                case 5:
                    name = "FAVORITES";
                    i = 4;
                    break;
                case 6:
                    System.out.println("Goodbye! ^-^");
                    return;
            }
            System.out.println("----------------------------------------");
            if (name.equals("COUNTRY")) System.out.println("|                " + name + "               |");
            if (name.equals("FAVORITES")) System.out.println("|                " + name + "              |");
            if (!name.equals("COUNTRY") && !name.equals("FAVORITES"))
                System.out.println("|                 " + name + "                  |");
            System.out.println("----------------------------------------");
            showMenu2();
            switch (scan.nextInt()) {
                case 1:
                    System.out.println("Enter your music File Name :");
                    fileName = scan.next();
                    System.out.println("Enter your music Singer Name :");
                    singer = scan.next();
                    System.out.println("Enter your music Release Year :");
                    year = scan.nextInt();

                    if (i != 4) {
                        System.out.println("Do you want to add this music in your favorite list ? (yes or no)");
                        if (scan.next().equals("yes")) categories[4].addFile(fileName, singer, year);
                    }
                    categories[i].addFile(fileName, singer, year);
                    break;
                case 2:
                    System.out.println("Choose the music you want to remove :");
                    categories[i].listAllFiles();
                    categories[i].removeFile(scan.nextInt() - 1);
                    break;
                case 3:
                    System.out.println("Number of musics in this category is : " + categories[i].getNumberOfFiles());
                    break;
                case 4:
                    System.out.print("Enter a number from 1 to " + categories[i].getNumberOfFiles() + " : ");
                    categories[i].listFile(scan.nextInt()-1);
                    break;
                case 5:
                    System.out.println("Enter the file name : ");
                    categories[i].searchMusic(scan.next());
                    break;
                case 6:
                    categories[i].listAllFiles();
                    break;
                case 7:
                    System.out.println("Choose a music to play : ");
                    categories[i].listAllFiles();
                    categories[i].startPlaying(scan.nextInt() - 1);
                    System.out.println();
                    System.out.println("Enter 0 to stop playing!");
                    if (scan.nextInt() == 0) {
                        categories[i].stopPlaying();
                    }
                    break;
                case 8:
                    System.out.println("Goodbye! ^-^\n");
                    break;
            }
        }
    }
}