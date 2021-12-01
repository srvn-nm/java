package com.company;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObjectFromFile {
    private ObjectInputStream in;

    public ReadObjectFromFile(String fileAddress) throws FileNotFoundException, IOException {
        in = new ObjectInputStream(new FileInputStream(fileAddress));
    }

    public Object readFromFile() throws ClassNotFoundException, IOException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        in.close();
    }
}
