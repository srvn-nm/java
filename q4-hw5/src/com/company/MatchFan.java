package com.company;

public class MatchFan extends Fan {


    /**
     * this is a constructor
     *
     * @param club     which wants to follow
     * @param fullName
     */
    public MatchFan(Club club, String fullName) {
        super(club, fullName);
    }

    /**
     * this a method for getting the latest news
     */
    @Override
    public void update() {
        System.out.println(getFullName());
        System.out.println("latest news about match");
        System.out.println(club.getMatchState().get(club.getMatchState().size()-1).toString());
        System.out.println("******************************************************************************");
    }
}
