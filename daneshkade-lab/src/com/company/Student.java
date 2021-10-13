package com.company;

/***
 * @author sarvin nami
 * this class will save student's informatino.
 */

public class Student {
    // the student's first name
    private String firstName;
    // the student's last name
    private String lastName;
    // the student's ID
    private String id;
    //the student's grade
    private float grade;

    /***
     * This constructor will set 3 fields of a student
     * @param name first name of student
     * @param lastname last name of student
     * @param id id of student
     *  set grade = 0
     */

    public Student(String name, String lastname, String id) {
        this.firstName = name;
        this.lastName = lastname;
        this.id = id;
        this.grade = 0;

    }

    /**
     * This method will return student's first name.
     * @return this.firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

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
    public void setGrade(float grade) {
        this.grade = grade;
    }

    /**
     * This method will print the student's information.
     */
    public void print() {
        System.out.println(this.firstName + this.lastName + ", student ID: " + this.id + ", grade: " + this.grade);
    }
}