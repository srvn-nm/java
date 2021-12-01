package com.company;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * the FileUtils class connects the INote app to files and has some methods to write to file and  ,
 * read from it.
 */
public class FileUtils {
    //TODO: Phase1: define method here for reading file with InputStream
    //TODO: Phase1: define method here for writing file with OutputStream
//    /**
//     * the fileReader reads some data from a file using fileInputStream.
//     *
//     * @param file the file want to read items from it.
//     * @return content of a file.
//     * @throws IOException when could not open the file.
//     */
//    public static String fileReader(File file) throws IOException {
//        //TODO: Phase1: read content from file
//        FileInputStream in = null;
//        char[] array = new char[1024];
//        String text;
//        try {
//            in = new FileInputStream(file);
//            int c;
//            int i = 0;
//            while ((c = in.read()) != -1) {
//                array[i] = (char) c;
//                i++;
//            }
//            text = new String(array);
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//        }
//        return text;
//    }
//    /**
//     * the fileWriter writes some data to a file using fileOutputStream.
//     *
//     * @param content the content want to write to the file.
//     * @throws IOException when could not make the file.
//     */
//    public static void fileWriter(String content) throws IOException {
//        //TODO: write content on file
//        FileOutputStream out = null;
//        String fileName = getProperFileName(content);
//        try {
//            byte[] data = content.getBytes();
//            out = new FileOutputStream(NOTES_PATH + fileName + ".txt");
//            out.write(data);
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//
//    /**
//     * the fileReaderWithBuffer reads some data from a file using BufferedReader.
//     *
//     * @param file the file want to read items from it.
//     * @return content of a file.
//     */
    public static String fileReaderWithBuffer(File file) {
        //TODO: Phase1: read content from file
        String text = " ";
        char[] array = new char[1024];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int c;
            int i = 0;
            while ((c = bufferedReader.read()) != -1) {
                array[i] = (char) c;
                i++;
            }
            text = new String(array);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("cannot open the file");
        }
        return text;
    }
    /**
     * the fileWriterWithBuffer writes some data to a file using BufferedWriter.
     *
     * @param content the content want to write items to file..
     */
    public static void fileWriterWithBuffer(String content) {
        //TODO: write content on file
        String fileName = getProperFileName(content);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./notes/" + fileName + ".txt"))) {
            bufferedWriter.write(content);

        } catch (IOException e) {
            System.out.println("Failed to write on file");
        }
    }
    //TODO: Phase2: proper methods for handling serialization
    /**
     * the writeFileObject take an object and write it to the file.
     *
     * @param note the Note object want to write on a file.
     */
    public static void writeFileObject(Note note) {
        WriteObjectToFile write = null;
        try {
            write = new WriteObjectToFile("notes.ser");
            write.writeToFile(note);
            write.closeConnection();
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }
    /**
     * the readFileObject takes a file and read the binary data from it.
     *
     * @return content of the read file.
     */
    public static String readFileObject() {
        ReadObjectFromFile read = null;
        try {
            read = new ReadObjectFromFile("./notes/");
            while (true) {
                Note note1 = (Note) read.readFromFile();
                System.out.print(note1.getTitle() + "\t");
                System.out.print(note1.getDate() + "\t");
                System.out.print(note1.getContent() + "\t");
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return " ";
    }
    /**
     * get the name of the file.
     *
     * @param content data of the file.
     * @return name of the file.
     */
    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
    public void saveObject(String note) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm:ss ");
        LocalDateTime now = LocalDateTime.now();
        Note noteObject = new Note(note,note,dtf.format(now));
        if (!note.isEmpty()) {
            try {
                FileUtils.writeFileObject(noteObject);
            } catch (Exception e) {
                System.out.println("failed to write the object");
            }
        }
    }
    public static void deleteANote(String title) throws IOException, ClassNotFoundException {
        ReadObjectFromFile read = new ReadObjectFromFile("notes.ser");
        Note note = (Note) read.readFromFile() ;
        PrintWriter pw = new PrintWriter("copy.ser");
        BufferedReader br1 = new BufferedReader(new FileReader("notes.ser"));
        String line1;
        boolean flag = false;
        if (!note.getTitle().equals(title)) {
            line1 = br1.readLine();
            while (line1 != null) {
                String line2 = note.getTitle() + note.getDate() + note.getContent();
                if (line1.equals(line2)){
                    flag= true;
                    break;
                }
                if (!flag)
                    pw.println(line1);
                line1 = br1.readLine();
            }
            pw.flush();
            ReadObjectFromFile read1 = new ReadObjectFromFile("copy.ser");
            Note note1 = (Note) read1.readFromFile() ;
            PrintWriter pw1 = new PrintWriter("notes.ser");
            BufferedReader br2 = new BufferedReader(new FileReader("copy.ser"));
            String line2 = br2.readLine();
            while (line2 != null) {
                pw1.println(line2);
                line2 = br2.readLine();
            }
            pw1.flush();
            br2.close();
            pw1.close();
            br1.close();
            pw.close();
            System.out.println("deleting note successfully done.");
        }
    }
}
