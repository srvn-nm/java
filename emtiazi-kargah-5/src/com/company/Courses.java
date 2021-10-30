package com.company;

import java.util.ArrayList;

public class Courses {
    private String name;
    private String professor;
    private String book;
    private int avg;
    ArrayList<Student> students;
    public Courses(){
        students = new ArrayList<Student>();
    }

    /**
     * This method will return the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method will set the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will return the average.
     * @return avg
     */
    public int getAvg() {
        return avg;
    }

    /**
     * This method will return the book name.
     * @return book
     */
    public String getBook() {
        return book;
    }

    /**
     * This method will return the professor name.
     * @return professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * This method will set the average.
     * @param avg
     */
    public void setAvg(int avg) {
        this.avg = avg;
    }

    /**
     * This method will set the book name.
     * @param book
     */
    public void setBook(String book) {
        this.book = book;
    }

    /**
     * This method will set the professor name.
     * @param professor
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * This method will return the information.
     * @return info
     */
    public String toString(){
        return "name : " + this.name + " | professor : " + this.professor + " | reference : " + this.book + " | average of students : " + this.avg;
    }
}