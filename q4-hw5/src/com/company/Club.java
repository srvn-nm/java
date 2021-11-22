package com.company;

import java.util.ArrayList;

/**
 * this is class which defines club and all its staffs
 * club is a publisher class
 * fans is a subscriber class
 * @version 001
 * @author sarvin
 * @since late at night
 */
public class Club {
    private String clubName;
    private ArrayList<Player> team;
    private ArrayList<Match> matches;
    private ArrayList<Fan> matchFollower;
    private ArrayList<Fan> playersFollower;
    private ArrayList<Fan> clubFollower;
    private ArrayList<New> matchState;
    private ArrayList<New> playersState;
    private ArrayList<New> clubState;

    /**
     * constructor
     * @param clubName which is immutable
     */
    public Club(String clubName){
        this.clubName=clubName;
        matchFollower=new ArrayList<>();
        playersFollower=new ArrayList<>();
        clubFollower=new ArrayList<>();
        matchState=new ArrayList<>();
        playersState=new ArrayList<>();
        clubState=new ArrayList<>();
        matches=new ArrayList<>();
        team=new ArrayList<Player>();
    }

    /**
     * getter method
     * @return club state
     */
    public ArrayList<New> getClubState() {
        return clubState;
    }

    /**
     * getter method
     * @return match state
     */
    public ArrayList<New> getMatchState() {
        return matchState;
    }

    /**
     * getter method
     * @return plater state
     */
    public ArrayList<New> getPlayersState() {
        return playersState;
    }

    /**
     * this is add method for new subscribers of club news
     * @param fan new one
     */
    public void addSubscriberClub(Fan fan){
        clubFollower.add(fan);
    }
    /**
     * this is add method for new subscribers of match news
     * @param fan new one
     */
    public void addSubscriberMatch(Fan fan){
        matchFollower.add(fan);
    }
    /**
     * this is add method for new subscribers of players news
     * @param fan new one
     */
    public void addSubscriberPlayers(Fan fan){
        playersFollower.add(fan);
    }

    /**
     * this is remove method
     * @param fan which doesn't want to follow  any more
     */
    public void removeSubscriberPlayers(Fan fan){
        playersFollower.remove(fan);
    }
    /**
     * this is remove method
     * @param fan which doesn't want to follow  any more
     */
    public void removeSubscriberMatch(Fan fan){
        matchFollower.remove(fan);
    }
    /**
     * this is remove method
     * @param fan which doesn't want to follow  any more
     */
    public void removeSubscriberClub(Fan fan){
        clubFollower.remove(fan);
    }

    /**
     * this one of the most important method of publisher class
     * which informs all subscribers
     */
    public void notifySubscribersClub(){
        for (Fan fan:clubFollower) {
            fan.update();
        }
    }
    /**
     * this one of the most important method of publisher class
     * which informs all subscribers
     */
    public void notifySubscribersMatch(){
        for (Fan fan:matchFollower) {
            fan.update();
        }
    }
    /**
     * this one of the most important method of publisher class
     * which informs all subscribers
     */
    public void notifySubscribersPlayers(){
        for (Fan fan:playersFollower) {
            fan.update();
        }
    }

    /**
     * this is setter method
     * @param news that have been recently released
     */
    public void setClubState(New news){
        clubState.add(news);
        notifySubscribersClub();
    }
    /**
     * this is setter method
     * @param news that have been recently released
     */
    public void setPlayerState(New news){
        playersState.add(news);
        notifySubscribersPlayers();
    }
    /**
     * this is setter method
     * @param news that have been recently released
     */
    public void setMatchState(New news){
        matchState.add(news);
        notifySubscribersMatch();
    }

    /**
     * this is  method for adding new player to team
     * @param player new one
     */
    public void addPlayerToTeam(Player player){
        team.add(player);
    }

    /**
     * this a an add method for adding new matches
     * @param match new one
     */
    public void addMatch(Match match){
        matches.add(match);
    }

}
