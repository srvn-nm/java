package com.company;
import java.util.*;
public class Main {
    public static void menu(){
        System.out.println("Hi !\nWelcome to the portal of university ^-^\nChoose what post you have:\n1)Professor\n2)Student\n3)Employee");
    }
    public static void menu1(){
        System.out.println("Choose what you want to do:\n1)set information\n2)add course\n3)remove course\n4)salary\n5)set TA\n6)print info\n7)do practice\n8)Exit");
    }
    public static void menu2(){
        System.out.println("Choose what you want to do:\n1)set information\n2)add course\n3)remove course\n4)tuition\n5)set TA\n6)print info\n7)new practice\n8)Exit");
    }
    public static void menu3(){
        System.out.println("Choose what you want to do:\n1)set information\n2)salary\n3)print info\n4)work\n5)Exit");
    }
    public static void main(String[] args) {
        Professor p = new Professor();
        Student s = new Student();
        Professor p2 = new Professor();
        Student s2 = new Student();
        Employee e = new Employee();
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        menu();
        String choice = in.next();
            switch (choice) {
                case "1" -> {
                        menu1();
                        String choice2 = in.next();
                    while (true) {
                        switch (choice2) {
                            case "1" -> {
                                System.out.println("Type your name :");
                                p.setName(input.next());
                                System.out.println("Type your last name :");
                                p.setLastName(input.next());
                                System.out.println("Type your ID :");
                                p.setId(input.nextInt());
                                System.out.println("Type your bank balance :");
                                p.setBankBalance(input.nextInt());
                                System.out.println("Type your hindex :");
                                p.setHindex(input.nextInt());
                                System.out.println("Professor successfully added! ^-^");
                                menu1();
                                choice2 = in.next();
                            }
                            case "2" -> {
                                System.out.println("Type the course name : ");
                                Courses c = new Courses();
                                c.setName(input.next());
                                System.out.println("Type the professor name : ");
                                c.setProfessor(input.next());
                                System.out.println("Type the reference name : ");
                                c.setBook(input.next());
                                p.addCourse(c);
                                System.out.println("Course successfully added! ^-^");
                                menu1();
                                choice2 = in.next();
                            }
                            case "3" -> {
                                System.out.println("Type the course name : ");
                                p.removeCourse(input.next(), p.getName());
                                menu1();
                                choice2 = in.next();
                            }
                            case "4" -> {
                                System.out.println("Type the salary here");
                                p.setFinancial(input.nextInt());
                                menu1();
                                choice2 = in.next();
                            }
                            case "5" -> {
                                System.out.println("Type name : ");
                                s.setName(input.next());
                                System.out.println("Type last name : ");
                                s.setLastName(input.next());
                                System.out.println("Type ID : ");
                                s.setId(input.nextInt());
                                System.out.println("Type Dorm : ");
                                s.setDorm(input.next());
                                p.setTA(s);
                                menu1();
                                choice2 = in.next();
                            }
                            case "6" -> {
                                p.printInfo();
                                menu1();
                                choice2 = in.next();
                            }
                            case "7" -> {
                                System.out.println("Type the new homework : ");
                                p.practice(input.next());
                                menu1();
                                choice2 = in.next();
                            }
                            case "8" -> {
                                System.out.println("Goodbye Professor ^-^");
                                return;
                            }
                        }
                    }
                }
                case "2" -> {
                        menu2();
                        String choice3 = in.next();
                    while (true) {
                        switch (choice3) {
                            case "1" -> {
                                System.out.println("Type your name :");
                                s2.setName(input.next());
                                System.out.println("Type your last name :");
                                s2.setLastName(input.next());
                                System.out.println("Type your ID :");
                                s2.setId(input.nextInt());
                                System.out.println("Type your bank balance :");
                                s2.setBankBalance(input.nextInt());
                                System.out.println("Type your Dorm :");
                                s2.setDorm(input.next());
                                System.out.println("Student successfully added! ^-^");
                                menu2();
                                choice3 = in.next();
                            }
                            case "2" -> {
                                System.out.println("Type the course name : ");
                                Courses c = new Courses();
                                c.setName(input.next());
                                System.out.println("Type the professor name : ");
                                c.setProfessor(input.next());
                                System.out.println("Type the reference name : ");
                                c.setBook(input.next());
                                System.out.println("Type the grade : ");
                                c.setAvg(input.nextInt());
                                s2.addCourse(c);
                                System.out.println("Course successfully added! ^-^");
                                menu2();
                                choice3 = in.next();
                            }
                            case "3" -> {
                                System.out.println("Type the course name then the professor name : ");
                                s2.removeCourse(input.next(), input.next());
                                menu2();
                                choice3 = in.next();
                            }
                            case "4" -> {
                                System.out.println("Type the tuition here");
                                s2.setFinancial(input.nextInt());
                                menu2();
                                choice3 = in.next();
                            }
                            case "5" -> {
                                System.out.println("Type name : ");
                                p2.setName(input.next());
                                System.out.println("Type last name : ");
                                p2.setLastName(input.next());
                                System.out.println("Type ID : ");
                                p2.setId(input.nextInt());
                                System.out.println("Type hindex : ");
                                p2.setHindex(input.nextInt());
                                s2.setTA(p2);
                                menu2();
                                choice3 = in.next();
                            }
                            case "6" -> {
                                s2.printInfo();
                                menu2();
                                choice3 = in.next();
                            }
                            case "7" -> {
                                System.out.println("Type the answer of homework : ");
                                s2.practice(input.next());
                                menu2();
                                choice3 = in.next();
                            }
                            case "8" -> {
                                System.out.println("Goodbye Student ^-^");
                                return;
                            }
                        }
                    }
                }
                case "3" ->{
                    menu3();
                    String choice4 = in.next();
                    while (true) {
                        switch (choice4) {
                            case "1" -> {
                                System.out.println("Type your name :");
                                e.setName(input.next());
                                System.out.println("Type your last name :");
                                e.setLastName(input.next());
                                System.out.println("Type your ID :");
                                e.setId(input.nextInt());
                                System.out.println("Type your bank balance :");
                                e.setBankBalance(input.nextInt());
                                System.out.println("Employee successfully added! ^-^");
                                menu3();
                                choice4 = in.next();
                            }
                            case "2" -> {
                                System.out.println("Type the salary here");
                                e.setFinancial(input.nextInt());
                                menu3();
                                choice4 = in.next();
                            }
                            case "3" -> {
                                e.printInfo();
                                menu3();
                                choice4 = in.next();
                            }
                            case "4" -> {
                                System.out.println("Type the title of your duty : ");
                                e.practice(input.next());
                                menu3();
                                choice4 = in.next();
                            }
                            case "5" -> {
                                System.out.println("Goodbye Employee ^-^");
                                return;
                            }
                        }
                    }
                }
        }
    }
}