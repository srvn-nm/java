package com.company;

import java.util.*;
public abstract class Person {
    protected int financial;
    protected String name;
    protected String lastName;
    ArrayList<Courses> courses;
    protected int id;
    protected long bankBalance;
    public Person(){
        courses = new ArrayList<Courses>();
        financial = 0;
        name = "";
        lastName = "";
        id = 0;
        bankBalance = 0;
    }
    /**
     * This method will set the name.
     * @param name
     */
    public boolean setName(String name) {
        boolean test = false;
        for (char c : name.trim().toCharArray()){
            if (!Character.isDigit(c))
                test = true;
        }
        if (!test) {
            System.out.println("invalid name. try again!");
            return false;
        }
        else
            this.name = name;
        return true;
    }

    /**
     * This method will return the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method will return the id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This method will return the financial.
     * @return financial
     */
    public int getFinancial() {
        return financial;
    }

    /**
     * This method will return the last name.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method will set the id.
     * @param id
     * @return
     */
    public boolean setId(int id) {
        boolean test = false;
        for (char c : Integer.toString(id).trim().toCharArray()){
            if (Character.isDigit(c))
                test = true;
        }
        if (!test) {
            System.out.println("invalid amount of money. try again!");
            return false;
        }
        else if(test && checkId(id))
            this.id = id;
        return true;
    }

    /**
     * This method will set the last name.
     * @param lastName
     * @return true or false
     */
    public boolean setLastName(String lastName) {
        boolean test = false;
        for (char c : lastName.trim().toCharArray()){
            if (!Character.isDigit(c))
                test = true;
        }
        if (!test) {
            System.out.println("invalid last name. try again!");
            return false;
        }
        else
            this.lastName = lastName;
        return true;
        }

    /**
     * this method will check if the id is valid.
     * @param id
     * @return true or false
     */
    public boolean checkId(int id){
        return (String.valueOf(id)).length() == 7 || (String.valueOf(id)).length() == 8 ;
    }

    /**
     * This method will receive and store the given message.
     * @param message
     */
    public void sendMessage(String message){
        System.out.println("Your message saved successfully!");
    }

    /**
     * This method will help to do the work.
     * @param work
     */
    public abstract void practice(String work);

    /**
     * This method will return the bank balance.
     * @return bankBalance
     */
    public long getBankBalance() {
        return bankBalance;
    }

    /**
     * This method will set the bank balance.
     * @param bankBalance
     * @return true or false
     */
    public boolean setBankBalance(long bankBalance) {
        boolean test = false;
        for (char c : Long.toString(bankBalance).trim().toCharArray()){
            if (Character.isDigit(c) && bankBalance > 0)
                test = true;
        }
        if (!test) {
            System.out.println("invalid amount of money. try again!");
            return false;
        }
        else
            this.bankBalance = bankBalance;
        return true;
    }

    /**
     * This method will add courses.
     * @param course
     */
    public void addCourse(Courses course){
        if (courses.size() == 0){
            courses.add(course);
        }
        else {
            Iterator<Courses> it = courses.iterator();
            boolean t = true;
            while (it.hasNext()){
                Courses c = (Courses) it.next();
                if (c.equals(course)){
                    t = false;
                    System.out.println("This course has already added! ");
                    break;
                }
            }
            if (t){
                courses.add(course);
                System.out.println("Course added successfully.");
            }
        }
    }

    /**
     * This method will remove a course.
     * @param courseName
     * @param professorName
     */
    public void removeCourse(String courseName, String professorName){
        Iterator<Courses> it = courses.iterator();
        boolean t = false;
        while(it.hasNext()){
            Courses c = (Courses)it.next();
            if (c.getName().equals(courseName) && c.getProfessor().equals(professorName)){
                it.remove();
                t = true;
            }
        }
        if (t){
            System.out.println("Course removed successfully!");
        }
        else {
            System.out.println("Course not found!");
        }
    }

    /**
     * This method will print the courses.
     */
    public void printCourses(){
        for (Courses c : courses){
            System.out.println(c.toString());
        }
    }
}