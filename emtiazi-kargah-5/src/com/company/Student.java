package com.company;

import java.util.*;

public class Student extends Person{
    private int avg;
    private Professor TA;
    private String dorm;
    public Student(){
        super();
    }
    /**
     * This method will help to do the work.
     *
     * @param work
     */
    @Override
    public void practice(String work) {
        System.out.println("Practice successfully done!");
    }

    /**
     * This method will set the average.
     */
    public void setAvg() {
        int result = 0;
        if (courses.size() > 0){
            for (int i = 0; i < courses.size();i++){
                result += courses.get(i).getAvg();
            }
        }
        this.avg = result / courses.size();
    }

    /**
     * This method will return the average.
     * @return avg
     */
    public int getAvg() {
        return avg;
    }

    /**
     * This method will return the type of dorm.
     * @return Dorm
     */
    public String getDorm() {
        return dorm;
    }

    /**
     * This method will return the name of TA.
     * @return
     */
    public Professor getTA() {
        return TA;
    }

    /**
     * This method will set the type of dorm.
     * @param dorm
     */
    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    /**
     * This method will set the TA name.
     * @param TA
     */
    public void setTA(Professor TA) {
        this.TA = TA;
    }
    /**
     * This method will set the financial.
     * @param financial
     * @return true or false
     */
    public boolean setFinancial(int financial) {
        boolean test = false;
        for (char c : Integer.toString(financial).trim().toCharArray()){
            if (Character.isDigit(c) && financial > 0)
                test = true;
        }
        if (!test) {
            System.out.println("invalid amount of money. try again!");
            return false;
        }
        else
            this.financial = financial;
        if (new GregorianCalendar().get(Calendar.DAY_OF_MONTH) == 1) {
            this.bankBalance -= this.financial;
            System.out.println("Tuition deducted from account successfully! updated bank balance: " + this.bankBalance);
        }
        return true;
    }

    /**
     * This method will display the condition of the term.
     */
    public void condition(){
        if (this.avg >= 12)
            System.out.println("Student " + this.name + " " + this.lastName + " pass the term!\ncongratulations! ^-^");
        else
            System.out.println("Student " + this.name + " " + this.lastName + " does not pass the term! >-<");
    }
    /**
     * This method will print student's information.
     */
    public void printInfo(){
        System.out.println("name : " + this.name + "\nlast name : " + this.lastName + "\nsalary : " + this.financial + "\nid :" + this.id + "\nbank balance : " + this.bankBalance + "\nAverage :" + this.avg + "\nDorm : " + this.dorm + "\nTA name : " + this.TA.toString());
        condition();
        printCourses();
    }
    public String toString(){
        return "name : " + this.name + "\nlast name : " + this.lastName + "\nid :" + this.id + "\nDorm : " + this.dorm;
    }
}