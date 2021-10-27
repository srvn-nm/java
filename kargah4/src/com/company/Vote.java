package com.company;
/**
 * this vote class represents a Vote that voters give to a Voting's choice.
 * @author Sarvin Nami
 */
public class Vote {
    //voter.
    private Person person;
    //date of  given vote.
    private String date;

    /**
     * this constructor makes a Vote.
     *
     * @param person Voter.
     * @param date   date of given Vote.
     */
    public Vote(Person person, String date) {
        this.person = person;
        this.date = date;
    }

    /**
     * returns Voter.
     *
     * @return Voter.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * this method returns date of Vote.
     *
     * @return date of Vote
     */
    public String getDate() {
        return date;
    }

    /**
     * makes a string.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return "Vote{" +
                "date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        if (getPerson() != null ? !getPerson().equals(vote.getPerson()) : vote.getPerson() != null) return false;
        return getDate() != null ? getDate().equals(vote.getDate()) : vote.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getPerson() != null ? getPerson().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
