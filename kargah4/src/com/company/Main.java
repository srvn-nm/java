package com.company;
import ir.huri.jcal.JalaliCalendar;

import java.util.*;

/**
 * the VotingSystem class manages the Voting process and creates or removes a Voting.
 *
 * @author Sarvin Nami
 */

class VotingSystem {
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

/**
 * this class represents a Voting with 2 types : first that each person can give just one Vote
 * and second that Voter can give even more than one Vote.
 *
 * @author Sarvin Nami
 */

class Voting {
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
/**
 * this vote class represents a Vote that voters give to a Voting's choice.
 *
 * @author Sarvin Nami
 */

class Vote {
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
/**
 * this class shows the voter who voted for a voting.
 *
 * @author Sarvin Nami
 */

class Person {
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
class Main{

    public static void main(String[] args) {
        VotingSystem votingSystem2 = new VotingSystem();
//making instances of person class.
        Person person12 = new Person("Sarvin", "Nami");
        Person person22 = new Person("Sara", "Habibi");
//creating voting.
        ArrayList<String> choices1 = new ArrayList<String>();
        ArrayList<String> choices2 = new ArrayList<String>();

        choices1.add("are");
        choices1.add("na");

        choices2.add("shanbe");
        choices2.add("yekshanbe");
        choices2.add("doshanbe");
        votingSystem2.createVoting("berim biron ?", 1, choices1);
        votingSystem2.createVoting("key berim ?", 0, choices2);

        ArrayList votes12 = new ArrayList();
        ArrayList votes22 = new ArrayList();
        ArrayList votes3 = new ArrayList();
        ArrayList votes4 = new ArrayList();
        Random random1 = new Random();
        //adding votes.
        votes12.add("are");

        votes12.add("na");

        votes3.add("are");


        //voting with a random choice.
        votes4.add("yekshanbe");

        votes22.add(choices2.get(random1.nextInt(choices2.size())));

        votingSystem2.vote(0, person12, votes12);
        votingSystem2.vote(0, person22, votes3);
        votingSystem2.vote(1, person22, votes22);
        votingSystem2.vote(1, person12, votes4);
        while (true) {
            int i = 0;
            System.out.println();
            System.out.println("Choose your category : ");
            System.out.println();
            System.out.println("***************************");
            System.out.println("* 1) add voter            *");
            System.out.println("* 2) create voting & vote *");
            System.out.println("* 3) print voting         *");
            System.out.println("* 4) remove voting        *");
            System.out.println("* 5) Quit                 *");
            System.out.println("***************************");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            switch (scan.nextInt()) {
                case 1:
                    System.out.println("Enter your FirstName :");
                    List<Person> personList = new ArrayList<Person>();
                    personList.add(new Person(scan.next(), scan.next()));
                    i = 0;
                    break;
                case 2:
                    Person person1 = new Person("Sarvin", "Nami");
                    Person person2 = new Person("Sara", "Habibi");
                    System.out.println("write the number of choices you want to add. :");
                    ArrayList<String> choices = new ArrayList<String>();
                    int maxChoices = scan.nextInt();
                    for (int j = 0; j < maxChoices; j++)
                        choices.add(scan.next());
                    System.out.println("write the question you want to add. :");
                    System.out.println("if you want the voting system to be on-option enter 0, else enter 1");
                    votingSystem2.createVoting(scan.next(), scan.nextInt(), choices);
                    System.out.println();
                    System.out.println("Printing voting question : ");
                    System.out.println("------------------");
                    votingSystem2.printVotingQuestions();
                    System.out.println();
                    System.out.println("Printing voting : ");
                    System.out.println("------------------");
                    votingSystem2.printVoting(2);
                    System.out.println();
                    System.out.println("Adding votes : ");
                    System.out.println("---------------");
                    System.out.println("write the votes");
                    ArrayList votes1 = new ArrayList();
                    ArrayList votes2 = new ArrayList();
                    Random random = new Random();
                    votes1.add(scan.next());
                    //voting with a random choice.
                    votes2.add(choices.get(random.nextInt(choices.size())));
                    votingSystem2.vote(2, person1, votes1);
                    votingSystem2.vote(2, person2, votes2);
                    System.out.println();
                    System.out.println("Printing results : ");
                    System.out.println("-------------------");
                    votingSystem2.printResults(2);
                    System.out.println();
                    System.out.println("Checking equals method overriding : ");
                    System.out.println("------------------------------------");
                    Vote vote1 = new Vote(person1, "2021");
                    Vote vote2 = new Vote(person2, "2021");
                    System.out.println("if two votes are equal : " + vote1.equals(vote2));
                    i = 1;
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Printing voting questions : ");
                    System.out.println("------------------");
                    votingSystem2.printVotingQuestions();
                    System.out.println();

                    System.out.println("Printing voting : ");
                    System.out.println("------------------");

                    votingSystem2.printVoting(0);
                    votingSystem2.printVoting(1);
                    System.out.println();

                    System.out.println("Adding votes : ");
                    System.out.println("---------------");

                    System.out.println();

                    System.out.println("Printing results : ");
                    System.out.println("-------------------");
                    votingSystem2.printResults(0);
                    System.out.println();

                    votingSystem2.printResults(1);
                    System.out.println();
//checking equals method overriding.
                    System.out.println("Checking equals method overriding : ");
                    System.out.println("------------------------------------");
                    Vote vote12 = new Vote(person12, "2021");
                    Vote vote22 = new Vote(person12, "2021");
                    System.out.println("if two votes are equal : " + vote12.equals(vote22));
                    i = 2;
                    break;
                case 4:
                    votingSystem2.removeVoting(scan.nextInt() - 1);
                    i = 3;
                    break;
                case 5:
                    return;
            }
        }
    }
}