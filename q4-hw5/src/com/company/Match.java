package com.company;

import java.util.Date;

/**
 * this is a class which defines matches of club
 * @version 001
 * @author sarvin
 * @since late at night
 */
public class Match {
    private Date dateOfMatch;
    private String rivalTeam;
    private int numberOfSpectator;

    /**
     * constructor
     * @param dateOfMatch of match
     * @param rivalTeam of match
     * @param numberOfSpectator of match
     */
    public Match(Date dateOfMatch,String rivalTeam, int numberOfSpectator){
        this.dateOfMatch=dateOfMatch;
        this.rivalTeam=rivalTeam;
        this.numberOfSpectator=numberOfSpectator;
    }

    /**
     * getter method
     * @return date of match
     */
    public Date getDateOfMatch() {
        return dateOfMatch;
    }

    /**
     * getter method
     * @return number of spectator
     */
    public int getNumberOfSpectator() {
        return numberOfSpectator;
    }

    /**
     * getter method
     * @return rival team
     */
    public String getRivalTeam() {
        return rivalTeam;
    }
}
