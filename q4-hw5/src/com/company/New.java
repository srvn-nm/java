package com.company;

import java.util.Date;

/**
 * this is a class for news
 * @author sarvin
 * @version 001
 * @since late at night
 */
public class New {
    private String title;
    private String body;
    private Date date;

    public New(String title, String body,Date date){
        this.title=title;
        this.body=body;
        this.date=date;
    }

    /**
     * getter method
     * @return date of new
     */
    public Date getDate() {
        return date;
    }

    /**
     * getter method
     * @return body of new
     */
    public String getBody() {
        return body;
    }

    /**
     * getter method
     * @return title of new
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "New{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
