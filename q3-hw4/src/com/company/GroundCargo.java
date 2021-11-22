package com.company;

public class GroundCargo extends Cargo {

    public GroundCargo() {
        super();
    }

    @Override
    public int calculatePrice() {
        int in = 0;
        if (insurance)
            in = 1;
       return super.calculatePrice() + in * weight * 1000;
    }
    @Override
    public boolean setDestination(int destination){
        if (destination == 17 || destination == 16 ||destination == 13 || destination == 12 || destination == 11 || destination == 10) {
            super.setDestination(destination);
            return true;
        }
        else {
            System.out.println("Ground cargo doesn't support this code!");
            return false;
        }
    }
    @Override
    public boolean setOrigin(int origin){
        if (origin == 17 || origin == 16 ||origin == 13 || origin == 12 || origin == 11 || origin == 10) {
            super.setOrigin(origin);
            return true;
        }
        else {
            System.out.println("Ground cargo doesn't support this code!");
            return false;
        }
    }
}