package com.company;

public class SeaCargo extends Cargo {

    public SeaCargo(boolean insurance,float weight) {
        super();
        this.type = "Sea Cargo";
        this.insurance = insurance;
        this.setWeight(weight);

    }

    @Override
    public int calculatePrice() {
        int in = 0;
        if (this.insurance)
            in = 1;
        return super.calculatePrice() + in * weight * 12000;
    }
    @Override
    public boolean setDestination(int destination){
        if (destination == 21 || destination == 20) {
            super.setDestination(destination);
            return true;
        }
        else {
            System.out.println("Sea cargo doesn't support this code!");
            return false;
        }
    }
    @Override
    public boolean setOrigin(int origin){
        if (origin == 21 || origin == 20) {
            super.setOrigin(origin);
            return true;
        }
        else {
            System.out.println("Sea cargo doesn't support this code!");
            return false;
        }
    }
}