package com.company;

public class Stack {
    int maxSize;
    int top;
    int[] array;
    public Stack(int maxSize) {
        top = -1;
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * this method will return true if the stack is empty and vice versa.
     * @return true or false
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * this method will return true if the stack is full and vice versa.
     * @return true or false
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * This method will add x to the stack.
     * @param x
     */
    public void push(int x) {
        if (top > maxSize) {
            System.out.println("Stack Overflow");
            return;
        }

        array[++top] = x;
    }

    /**
     * This method will remove the top element of the stack.
     * @return the new top.
     */
    public int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        return array[top--];

    }

    /**
     * This method will return the top item.
     * @return the top
     */
    public int peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }

        return array[top];
    }

    /**
     * This method will print the stack
     */
    public void print(){
        if (this.isEmpty()){
            System.out.println("  empty  ");
            return;
        }
        System.out.println("\n********************");
        System.out.println("         top        ");
        System.out.println("********************");
        for (int i = top; i >= 0; i--){
            System.out.println(" " + array[i] + " ");
        }
        System.out.println("********************");

    }
}