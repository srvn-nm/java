package com.company;
import java.util.*;

/***
 * @author sarvin nami
 * this class will save Departeman informatino.
 */

public class Department {

    //the name of Department
    private String name ;
    // the Department student
    private int capacity ;
    //the courses of  our Department
    private Student[] students ;
    //the amount of Department student
    private ArrayList<String> courses;
    // the date of Department foundation
    private String date ;

    /***
     * Create a new Department with a given details .
     * @param name
     * @param capacity
     * @param date the date of lab class
     */
    public Department(int capacity, String date, String name){
        this.capacity=capacity;
        this.name=name;
        this.date=date;
        students = new Student[capacity] ;
        courses = new ArrayList<String>();
    }

    /**
     * This method will return Department name
     * @return name
     */
    public String getName(){return name;}

    /***
     * This method will return Department foundation date
     * @return date
     */
    public String getDate() { return date; }

    /***
     * This method will return Department capacity
     * @return capacity
     */
    public int getCapacity() { return capacity; }

    /***
     * take a new course
     * Create a new Student with details .
     */
    public void toCreateDepartment(){

        while (true){
            System.out.printf("Course name: ");
            Scanner courseName = new Scanner(System.in);
            String course = courseName.nextLine();
            courses.add(course);
            System.out.printf("1) New Courses\n2) Exit");
            Scanner choiceInput = new Scanner(System.in);
            int choice = choiceInput.nextInt();
            if(choice == 2){
                break;
            }

        }
        Scanner input = new Scanner(System.in);
        for(int i=0;i<capacity;i-=-1){
            System.out.printf("Please Enter your Student Details: \nStudent Details:\n");
            //Student name
            System.out.print("Name: ");
            String name = input.nextLine();

            //Student last name
            System.out.print("Sure name: ");
            String lastName = input.nextLine();

            //Student id
            System.out.print("ID: ");
            String id = input.nextLine();

            Student person = new Student(name,lastName,id);

            //Student grade
            System.out.print("grade: ");
            float grade = input.nextFloat();
            person.setGrade(grade);
            students[i]=person;
        }
    }


    /***
     * This method will print the Department information.
     */

    public void printDepartment(){

        System.out.printf("Department Name : %s\nFoundation: %s\nCapacity: %d\n",getName(),getDate(),getCapacity());
        for(Student student : students){
            student.print();
        }
        System.out.printf("Courses:");
        for(String course : courses)
            System.out.println(course);
    }

}
