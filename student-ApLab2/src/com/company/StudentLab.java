package com.company;
/**
 * @author sarvin nami
 * this class will save a laboratory's informatino.
 */
public class StudentLab {
    private int capacity;
    private Student[] students;
    private int avg;
    private String day;
    private int currentSize;

    /**
     * This contructor will set the lab information.
     * @param cap
     * @param d
     */
    public StudentLab(int cap, String d) {
        this.capacity = cap;
        this.day = d;
        students = new Student[this.capacity];
    }

    /**
     * This method will enroll a student.
     * @param std
     */
    public void enrollStudent(Student std) {
        if (this.currentSize < this.capacity) {
            this.currentSize++;
            this.students[this.currentSize-1] = std;
        } else {
            System.out.println("Lab is full!!!");
        }

    }

    /**
     * This method will print the lab information
     */
    public void print() {
        for(int i = 0; i < students.length; i++) {
            this.students[i].print();
        }
    }

    /**
     * This method will return the lab's currents size student.
     * @return this.students[this.currentSize]
     */
    public Student getStudents() {
        return this.students[this.currentSize];
    }

    /**
     * This mehod will set the lab's student.
     * @param students
     */
    public void setStudents(Student[] students) {
        for(int i = 0; i < students.length; i++) {
            System.out.println("All Of The Students are :" + students[i]);
        }

    }

    /**
     * This method will return the average of students.
     * @return average
     */
    public int getAvg() {
        int average = 0;
        for (int i = 0; i< currentSize;i++){
            average += students[i].getGrade();
        }
        return average/ this.currentSize;
    }

    /**
     * This method will return the day.
     * @return this.day
     */
    public String getDay() {
        return this.day;
    }

    /**
     * This method will set and print the day.
     * @param day
     */
    public void setDay(String day) {
        this.day = day;
        System.out.println("Today is :" + day);
    }

    /**
     * This method will return the capacity of the lab.
     * @return this.capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * This method will set and print the capacity.
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;

        for(int i = 0; i < capacity; ++i) {
            System.out.println("The Capacity Of The Lab Is : " + capacity);
        }

    }
}