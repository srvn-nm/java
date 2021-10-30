package com.company;

import java.util.*;

public class Employee extends Person{
    public Employee(){
        super();
    }
    /**
     * This method will help to do the work.
     *
     * @param work
     */
    @Override
    public void practice(String work) {
        System.out.println("work successfully done!");
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
            this.bankBalance += this.financial;
            System.out.println("Salary added to account successfully! updated bank balance: " + this.bankBalance);
        }
        return true;
    }
    public void printInfo(){
        System.out.println("name : " + this.name + "\n| last name : " + this.lastName + "\n| salary : " + this.financial + "\n| id :" + this.id + "\n| bank balance : " + this.bankBalance);
    }
}