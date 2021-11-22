package com.company;

import java.util.ArrayList;

public class CinemaBookingSystem {
    public ArrayList<Show> shows;
    public ArrayList<Theater> theaters;

    public CinemaBookingSystem(){
        shows = new ArrayList<Show>();
        theaters = new ArrayList<Theater>();

    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public void addShow(Show show){
        shows.add(show);
    }
    public void removeShow(Show show){
        shows.remove(show);
    }
    public ArrayList<Theater> getTheaters() {
        return theaters;
    }

    public void addTheater(Theater theater){
        theaters.add(theater);
    }
    public void removeTheater(Theater theater){
        theaters.remove(theater);
    }
    public void showStatus(Show show){
        show.printStatus();
    }
}
