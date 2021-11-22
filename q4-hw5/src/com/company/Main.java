package com.company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();
        Club barca = new Club("Barca");
        Club manchester = new Club("manchester");
        Club real=new Club("real madrid");
        New newsClub=new New("collapse","ronaldo left the club",date);
        Fan niloofar=new ClubFan(real,"niloofar nami");
        real.addSubscriberClub(niloofar);
        Match match = new Match(date, "Liverpool", 100000);
        barca.addMatch(match);
        manchester.addMatch(match);
        Player messi = new Player("leo", "messi", 1234, 34, 10);
        barca.addPlayerToTeam(messi);
        Fan sarvin_nami = new playersFan(barca, "sarvin nami");
        barca.addSubscriberPlayers(sarvin_nami);
        New newOfPlayer = new New("goodbye", "next season messi will leave barca :)", date);
        barca.setPlayerState(newOfPlayer);
        New newOfMatch = new New("derbi of England", "the most important football match has been canceled because of virus", date);
        Fan negar = new MatchFan(manchester, "negar nami");
        real.setClubState(newsClub);
        manchester.addSubscriberMatch(negar);
        manchester.setMatchState(newOfMatch);
        barca.setMatchState(newOfMatch);
    }
}
