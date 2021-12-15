package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        //how we connect to server ...
        try (Socket client = new Socket("127.0.0.1", 7657)) {
            System.out.println("Connected to server.");
            Scanner input = new Scanner(System.in);
            //out put st --> server to  save
            OutputStream out = client.getOutputStream();
            //server massage to client --> input st
            InputStream in = client.getInputStream();
            byte[] buffer = new byte[2048];
            ArrayList<String> messages = new ArrayList<String>();
            String n = "";
            int index = 0;
            while (true) {
                System.out.println("Please write your massage here :");
                String massage = input.nextLine();
                out.write(massage.getBytes());
                messages.add(massage);
                System.out.println("SENT: " + massage);
                int read = in.read(buffer);
                n += "\t" + new String(buffer, 0, read);
                System.out.println("RECV: " + n);
                if (messages.get(index).equals("Over"))break;
                index++;
            }
            System.out.print("All messages sent.\nClosing ... ");
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println("done.");
    }
}
