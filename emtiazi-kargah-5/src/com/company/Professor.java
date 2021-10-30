package com.company;
import java.util.*;
public class Professor extends Person{
    private int hindex;
    private Student TA;
    public Professor(){
        super();
    }
    /**
     * This method will help to do the work.
     *
     * @param work
     */
    @Override
    public void practice(String work) {
        System.out.println("New practice uploaded successfully!");
    }

    /**
     * This method will return the hindex.
     * @return hindex
     */
    public int getHindex() {
        return hindex;
    }

    /**
     * This method will set the hindex.
     * @param hindex
     */
    public void setHindex(int hindex) {
        this.hindex = hindex;
    }

    /**
     * This method will set the TA name.
     * @param TA
     */
    public void setTA(Student TA) {
        this.TA = TA;
    }

    /**
     * This method will return the TA name.
     * @return TA
     */
    public Student getTA() {
        return TA;
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
    /**
     * This method will add courses.
     * @param course
     */
    @Override
    public void addCourse(Courses course){
        if (courses.isEmpty()){
            courses.add(course);
        }
        else {
            Iterator<Courses> it = courses.iterator();
            boolean t = true;
            while (it.hasNext()){
                Courses c = (Courses) it.next();
                if (c.equals(course) || !course.getProfessor().equals(this.name)){
                    t = false;
                    System.out.println("This course has already added! ");
                    break;
                }
            }
            if (t && course.getProfessor().equals(this.name)){
                courses.add(course);
                System.out.println("Course added successfully.");
            }
        }
    }

    /**
     * This method will print professor's information.
     */
    public void printInfo(){
        System.out.println("name : " + this.name + "\nlast name : " + this.lastName + "\nsalary : " + this.financial + "\nid :" + this.id + "\nbank balance : " + this.bankBalance + "\nhindex :" + this.hindex + "\nTA name : " + this.TA.toString());
        printCourses();
    }
    public String toString(){
        return "name : " + this.name + "\nlast name : " + this.lastName + "\nsalary : " + this.financial + "\nid :" + this.id + "\nhindex :" + this.hindex;
    }
}