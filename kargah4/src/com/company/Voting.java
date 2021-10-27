package com.company;
import ir.huri.jcal.JalaliCalendar;
import java.util.*;
/**
 * this class represents a Voting with 2 types : first that each person can give just one Vote
 * and second that Voter can give even more than one Vote.
 * @author Sarvin Nami
 */

public class Voting {
    //type of voting.
    private int type;
    //question of voting.
    private String question;

    ArrayList<Person> voters;
    ArrayList<String> choices;
    HashSet<Vote> votes;
    //list of votes to choices.
    HashMap<String, HashSet<Vote>> listOfVotesToChoices = new HashMap<String, HashSet<Vote>>();

    /**
     * this constructor makes a Voting with the given type , choices and question.
     *
     * @param type     type of Voting.
     * @param question question of Voting.
     * @param choices  choices of each Voting.
     */
    public Voting(int type, String question, ArrayList<String> choices) {
        this.type = type;
        this.question = question;
        this.choices = choices;
        voters = new ArrayList<Person>();
        for (int i = 0; i < choices.size(); i++) {
            votes = new HashSet<Vote>();
            listOfVotesToChoices.put(choices.get(i), votes);
        }
    }

    /**
     * this Vote method take a Voter and their Votes and add them to list.
     *
     * @param person Voter.
     * @param votes  the choices that Voter , Voted for.
     */
    public void Vote(Person person, ArrayList<String> votes) {
        JalaliCalendar jalaliCalendar = new JalaliCalendar();

        for (int j = 0; j < votes.size(); j++) {
            Vote vote = new Vote(person, jalaliCalendar.toString());

            if (type == 0 && !voters.contains(person))
                listOfVotesToChoices.get(votes.get(j)).add(vote);
            voters.add(person);
            if (type == 1) listOfVotesToChoices.get(votes.get(j)).add(vote);
            voters.add(person);


        }
    }

    /**
     * get the Voting question.
     *
     * @return question of Voting.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * this method take a choice and add it to the choices list.
     *
     * @param newChoice new choice.
     */
    public void createChoice(String newChoice) {
        choices.add(newChoice);
    }

    /**
     * the printVotes method takes a choice and prints the Voter'details and the date of Vote.
     *
     * @param choice given choice.
     */
    public void printVotes(String choice) {

        for (Vote vote : listOfVotesToChoices.get(choice))
            System.out.println("   Voter : " + vote.getPerson().getFirstName() + " " + vote.getPerson().getLastName() + " | Date : " + vote.getDate());
    }

}
