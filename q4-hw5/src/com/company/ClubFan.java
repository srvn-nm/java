package com.company;

public class ClubFan extends Fan {

    /**
     * this is a constructor
     *
     * @param club     which wants to follow
     * @param fullName
     */
    public ClubFan(Club club, String fullName) {
        super(club, fullName);
    }

    /**
     * this a method for getting the latest news
     */
    @Override
    public void update() {
        System.out.println(getFullName());
        System.out.println("latest news about club");
        System.out.println(club.getClubState().get(club.getClubState().size()-1).toString());
        System.out.println("******************************************************************************");
    }
}
