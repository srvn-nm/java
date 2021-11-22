package com.company;

public class playersFan extends Fan{
    /**
     * this is a constructor
     *
     * @param club     which wants to follow
     * @param fullName
     */
    public playersFan(Club club, String fullName) {
        super(club, fullName);
    }

    /**
     * this a method for getting the latest news
     */
    @Override
    public void update() {
        System.out.println(getFullName());
        System.out.println("latest news about players");
        System.out.println(club.getPlayersState().get(club.getPlayersState().size()-1).toString());
        System.out.println("******************************************************************************");
    }
}
