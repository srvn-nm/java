package com.company;
/**
 * @author sarvin nami
 * this class will save student's informatino.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String id;
    private int grade;

    /**
     * This constructor will set 3 fields of a student
     * @param fName
     * @param lname
     * @param sID
     */
    public Student(String fName, String lname, String sID) {
        this.firstName = fName;
        this.lastName = lname;
        this.id = sID;
        this.grade = 0;
    }

    /**
     * This method will return student's first name.
     * @return this.firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    public int getGrade(){return this.grade;}

    /**
     * This method will set student's first name.
     * @param fName
     */
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    /**
     * This method will set student's grade.
     * @param grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * This method will print the student's information.
     */
    public void print() {
        System.out.println(this.firstName + this.lastName + ", student ID: " + this.id + ", grade: " + this.grade);
    }
}
