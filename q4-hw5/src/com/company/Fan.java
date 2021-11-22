package com.company;

import java.util.ArrayList;

/**
 * this is an abstract which is used for all kind of fans of clubs
 * namely club fan , players fan and match fan
 * @author sarvin
 * @version 001
 * @since late at night
 */
public abstract class Fan {
    private String fullName;
    protected Club club;

    /**
     * this is a constructor
     * @param club which wants to follow
     */
    public Fan(Club club,String fullName){
        this.fullName=fullName;
        this.club=club;
    }

    /**
     * getter method
     * @return full name of fan
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * this a method for getting the latest news
     */
    public abstract void update();
}
