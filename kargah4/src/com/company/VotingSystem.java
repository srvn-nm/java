package com.company;

import java.util.*;

public class VotingSystem {
    //list of created Voting.
    ArrayList<Voting> votingList = new ArrayList<Voting>();

    /**
     * the printVoting method takes an index and prints Voting'question and choices.
     *
     * @param index index of Voting in votingList.
     */
    public void printVoting(int index) {
        System.out.println("Voting " + (index + 1) + " :");
        System.out.println("++++++++++");
        System.out.println("Question : " + votingList.get(index).getQuestion());
        for (int i = 0; i < votingList.get(index).choices.size(); i++) {
            System.out.println(i + 1 + ") " + votingList.get(index).choices.get(i));
        }
        System.out.println();
    }

    /**
     * the printResult method takes an index and prints the Voting result of Voting.
     *
     * @param index index of Voting in the votingList.
     */
    public void printResults(int index) {
        for (int i = 0; i < votingList.get(index).choices.size(); i++) {
            System.out.print(i + 1 + ") " + votingList.get(index).choices.get(i) + " -> ");
            if (votingList.get(index).listOfVotesToChoices.get(votingList.get(index).choices.get(i)) != null) {
                System.out.println(votingList.get(index).listOfVotesToChoices.get(votingList.get(index).choices.get(i)).size() + " ");
                votingList.get(index).printVotes(votingList.get(index).choices.get(i));
            } else System.out.println("0");
        }
        System.out.println();
    }

    /**
     * this method prints the questions of the Voting.
     */
    public void printVotingQuestions() {

        for (int i = 0; i < votingList.size(); i++) {
            System.out.println(votingList.get(i).getQuestion());
        }
    }

    /**
     * this createVoting method takes the type , question and choices and
     * creates a Voting and add it to the votingList.
     *
     * @param question question of the Voting.
     * @param type     type of Voting.
     * @param choices  choices of the Voting.
     */
    public void createVoting(String question, int type, ArrayList<String> choices) {
        Voting voting = new Voting(type, question, choices);
        votingList.add(voting);
        voting.choices = choices;
    }

    /**
     * the vote method takes a index , voter , votes and submit a vote.
     *
     * @param index  index of Voting.
     * @param person voter.
     * @param votes  votes of the voter.
     */
    public void vote(int index, Person person, ArrayList<String> votes) {
        votingList.get(index).Vote(person, votes);
        System.out.println("Vote submitted !");
    }

    /**
     * this method take an index of Voting and remove it from the votingList.
     *
     * @param index index of the Voting want to remove.
     */
    public void removeVoting(int index) {
        votingList.remove(index);
    }

}
