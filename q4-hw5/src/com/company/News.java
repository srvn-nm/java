package com.company;

import java.util.Date;

/**
 * this is a class for news
 * @author sarvin
 * @version 001
 * @since late at night
 */
public class News {
    private String title;
    private String body;
    private Date date;

    public News(String title, String body, Date date){
        this.title=title;
        this.body=body;
        this.date=date;
    }

    /**
     * getter method
     * @return date of news
     */
    public Date getDate() {
        return date;
    }

    /**
     * getter method
     * @return body of news
     */
    public String getBody() {
        return body;
    }

    /**
     * getter method
     * @return title of news
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
