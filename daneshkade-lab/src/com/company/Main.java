package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Department name:");
        String name = input.nextLine();
        System.out.print("foundation date:");
        String date = input.nextLine();
        System.out.print("Department capacity:");
        int capacity = input.nextInt();
        Department department = new Department(capacity,date,name);
        department.toCreateDepartment();
        department.printDepartment();

    }
}
