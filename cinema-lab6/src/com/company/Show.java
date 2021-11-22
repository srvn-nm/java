package com.company;

import java.util.Date;

public class Show {
    private Date date;
    private int title;
    private int seats;

    public Show(Date date, int title , int seats) {
        this.date = date;
        this.title = title;
        this.seats = seats;
    }

    public Date getDate() {
        return date;
    }

    public int getTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    public void printStatus(){

    }
}
