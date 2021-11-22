package com.company;

import java.util.ArrayList;


public class Row {
    ArrayList<Seat> seats;

    public Row(ArrayList<Seat> seats) {
        this.seats = new ArrayList<>();
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void checkMultipleSeats(){ }

    public void reserve(int rowOfSeat, int NumOfSeat){ }

    public void changeReserve() {}

    public void findSeat(int index){}
}
