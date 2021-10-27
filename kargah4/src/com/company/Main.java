package com.company;
import java.util.*;
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
                    System.out.println("Enter your Name :");
                    List<Person> personList = new ArrayList<Person>();
                    personList.add(new Person(scan.next(), scan.next()));
                    System.out.println("ُSuccessfully added!");
                    i = 0;
                    break;
                case 2:
                    Person person1 = new Person("Sarvin", "Nami");
                    Person person2 = new Person("Sara", "Habibi");
                    System.out.println("write the number of choices you want to add :");
                    ArrayList<String> choices = new ArrayList<String>();
                    int maxChoices = scan.nextInt();
                    System.out.println("Enter your choices here :");
                    for (int j = 0; j < maxChoices; j++)
                        choices.add(scan.next());
                    Scanner scan1 = new Scanner(System.in);
                    System.out.println("write the question you want to add :");
                    System.out.println("if you want the voting system to be one-option enter 0, else enter 1");
                    votingSystem2.createVoting(scan1.nextLine(), scan.nextInt(), choices);
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
                    System.out.println("Enter the number of voting :");
                    votingSystem2.removeVoting(scan.nextInt() - 1);
                    i = 3;
                    break;
                case 5:
                    System.out.println("Goodbye ^-^ !");
                    return;
            }
        }
    }
}