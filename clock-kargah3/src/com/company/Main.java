package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        ClockDisplay clockDisplay = new ClockDisplay();
        while (true){
            Thread.sleep(1000);
            clockDisplay.timeTick();
            System.out.println(clockDisplay.getTime());
        }
    }
}
