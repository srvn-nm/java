package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please type the information of the first student : ");
        Student std1 = new Student(in.next(), in.next(), in.next());
        System.out.println("Please type the information of the second student : ");
        Student std2 = new Student(in.next(), in.next(), in.next());
        System.out.println("Please type the information of the third student : ");
        Student std3 = new Student(in.next(), in.next(), in.next());
        System.out.println("Please type the grade of the first student : ");
        std1.setGrade(in.nextInt());
        std1.print();
        std2.setGrade(in.nextInt());
        System.out.println("Please type the grade of the second student : ");
        std2.print();
        System.out.println("Please type the grade of the third student : ");
        std3.setGrade(in.nextInt());
        std3.print();
        System.out.println("Here you can change the third student's name : ");
        std3.setFirstName(in.next());
        std3.print();
        StudentLab lab = new StudentLab(3, "Thursday");
        lab.enrollStudent(std1);
        lab.enrollStudent(std2);
        lab.enrollStudent(std3);
        System.out.println("There are the laboratory's students : ");
        lab.print();
    }
}
