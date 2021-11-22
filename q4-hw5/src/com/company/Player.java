package com.company;

/**
 * this is a class for defining player of club 's team
 * @version 001
 * @author sarvin
 * @since late at night
 */
public class Player {
    private String firstName;
    private String lastName;
    private int idNumber;
    private int age;
    private int numberOfPlayer;

    /**
     * constructor of player class
     * @param firstName of player
     * @param lastName of player
     * @param idNumber of player
     * @param age of player
     * @param numberOfPlayer  of player
     */
    public Player(String firstName,String lastName, int idNumber,int age , int numberOfPlayer){
        this.firstName=firstName;
        this.lastName=lastName;
        this.idNumber=idNumber;
        this.age=age;
        this.numberOfPlayer=numberOfPlayer;
    }

    /**
     * getter method
     * @return first name of player
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * getter method
     * @return last name of player
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * getter method
     * @return age of player
     */
    public int getAge() {
        return age;
    }
    /**
     * getter method
     * @return id of player
     */
    public int getIdNumber() {
        return idNumber;
    }
    /**
     * getter method
     * @return number of player
     */
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }
    /**
     * getter method
     * @return age of player
     */
    public void setAge(int age) {
        this.age = age;
    }
}
