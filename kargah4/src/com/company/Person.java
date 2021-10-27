package com.company;
/**
 * this class shows the voter who voted for a voting.
 * @author Sarvin Nami
 */
public class Person {
    private String firstName;
    private String lastName;

    /**
     * this constructor takes first name and last name , and makes a voter.
     *
     * @param firstName first name of voter.
     * @param lastName  last name of voter.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * get the first name of the voter.
     *
     * @return first name of voter.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the last name of the voter.
     *
     * @return last name of the voter.
     */
    public String getLastName() {
        return lastName;
    }
}
