package com.company;
import java.io.Serializable;
//TODO: Phase2: uncomment this code
/**
 * the Note class that implements Serializable makes a text with title , content and current date.
 */
public class Note implements Serializable {
    //title of the text.
    private String title;
    //content of the text.
    private String content;
    //date of the data created.
    private String date;
    /**
     * this constructor makes a note with given details.
     *
     * @param title   title of the text.
     * @param content content of the text.
     * @param date    date of the data created.
     */
    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
    /**
     * get the title.
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * get the date.
     *
     * @return date.
     */
    public String getDate() {
        return date;
    }
    public String getContent() {
        return content;
    }
    //override to string method.
    @Override
    public String toString() {
        return "title = " + title + "\ncontent = " + content + "\ndate = " + date;
    }
}