package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Type the number of the numbers you want to reverse their order : ");
        int maxSize = in.nextInt();
        Stack s = new Stack(maxSize);
        LinkedList<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++){
            l.push(in.nextInt());
        }
        for (Integer j:l) {
            s.push(j);
        }
        s.print();
        for (int i = 0; i < maxSize; i++) {
            s.pop();
        }
        s.print();
        for(int i = maxSize - 1; i >= 0; i--){
            s.push(l.get(i));
        }
        s.print();
    }
}
