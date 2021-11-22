package com.company;

import java.util.HashMap;

public class Theater {
    public HashMap<String,Seat> reservations;
    public Theater(){
        reservations = new HashMap<String,Seat>();
    }
    public HashMap<String, Seat> getReservations() {
        return reservations;
    }
    public void addReservation(String name,Seat seat){
        reservations.put(name,seat);
    }
    public void removeReservation(String name,Seat seat){
        reservations.remove(name,seat);
    }
    public void changeReservation(String name,Seat oldSeat,Seat newSeat){
        reservations.replace(name,oldSeat,newSeat);
    }
}
