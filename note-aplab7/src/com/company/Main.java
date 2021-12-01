package com.company;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.company.FileUtils.deleteANote;
import static com.company.FileUtils.writeFileObject;

/**
 * the Main class with manages the Notes.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("notes.ser");
        while (true){
            System.out.println();
            System.out.println("*If you want to save a new note enter 1.********");
            System.out.println("*If you want to see title of the notes enter 2.*");
            System.out.println("*If you want to see a full note enter 3.********");
            System.out.println("*If you want to delete a note enter 4.**********");
            System.out.println("*If you want to exit enter 5.*******************");
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input){
                case 1:
                    System.out.println("enter your note title and content.");
                    Note note = new Note(in.next(),in.next(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
                    writeFileObject(note);
                    break;
                case 2:
                    ReadObjectFromFile read = null;
                    try {
                        read = new ReadObjectFromFile("notes.ser");
                        while (true) {
                            Note note1 = (Note) read.readFromFile();
                            System.out.println(note1.getTitle() + "\t");
                        }
                    } catch (ClassNotFoundException | EOFException e) {
                        try {
                            assert read != null;
                            read.closeConnection();
                        } catch (IOException e1) {
                            System.out.println("error in closing file!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        assert read != null;
                        read.closeConnection();
                    }
                    break;
                case 3:
                    System.out.println("enter the title of the note that you want to see.");
                    String title = in.next();
                    ReadObjectFromFile read1 = null;
                    try {
                        read1 = new ReadObjectFromFile("notes.ser");
                        Note note3 = (Note) read1.readFromFile();
                        if (note3.getTitle().equals(title)) {
                            System.out.print(note3.getTitle() + "\t");
                            System.out.print(note3.getDate() + "\t");
                            System.out.print(note3.getContent() + "\t");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("enter the title of the note you want to clear");
                    String title1 = in.next();
                    deleteANote(title1);
                    break;
                case 5:
                    return;
            }
        }
    }
}
