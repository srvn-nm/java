package com.company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();
        Club barca = new Club("Barca");
        Club manchester = new Club("manchester");
        Club real=new Club("real madrid");
        News newsClub=new News("collapse","ronaldo left the club",date);
        Fan niloofar=new ClubFan(real,"niloofar nami");
        real.addSubscriberClub(niloofar);
        Match match = new Match(date, "Liverpool", 100000);
        barca.addMatch(match);
        manchester.addMatch(match);
        Player messi = new Player("leo", "messi", 1234, 34, 10);
        barca.addPlayerToTeam(messi);
        Fan sarvin_nami = new playersFan(barca, "sarvin nami");
        barca.addSubscriberPlayers(sarvin_nami);
        News newsOfPlayer = new News("goodbye", "next season messi will leave barca :)", date);
        barca.setPlayerState(newsOfPlayer);
        News newsOfMatch = new News("derbi of England", "the most important football match has been canceled because of virus", date);
        Fan negar = new MatchFan(manchester, "negar nami");
        real.setClubState(newsClub);
        manchester.addSubscriberMatch(negar);
        manchester.setMatchState(newsOfMatch);
        barca.setMatchState(newsOfMatch);
    }
}
