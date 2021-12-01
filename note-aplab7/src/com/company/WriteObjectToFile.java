package com.company;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjectToFile {
    private ObjectOutputStream out;

    public WriteObjectToFile(String fileAddress) throws FileNotFoundException, IOException {
        out = new ObjectOutputStream(new FileOutputStream(fileAddress));
    }

    public void writeToFile(Object o) throws IOException {
        out.writeObject(o);
    }

    public void closeConnection() throws IOException {
        out.close();
    }
}