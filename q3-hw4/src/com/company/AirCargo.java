package com.company;

public class AirCargo extends Cargo {

    /**
     * Create a new AirCargo
     */
    public AirCargo(boolean insurance,float weight) {
        super();
        this.type = "Air Cargo";
        this.insurance = insurance;
        this.setWeight(weight);
    }

    /**
     * Calculate total price of AirCargo
     * @return price
     */
    @Override
    public int calculatePrice() {
        int in = 0;
        if (this.insurance)
            in = 1;
        return super.calculatePrice() + in * weight * 7000;
    }
    @Override
    public boolean setDestination(int destination){
        if (destination == 21 || destination == 20 ||destination == 13 || destination == 12 || destination == 11 || destination == 10) {
            super.setDestination(destination);
            return true;
        }
        else {
            System.out.println("Air cargo doesn't support this code!");
            return false;
        }
    }
    @Override
    public boolean setOrigin(int origin){
        if (origin == 21 || origin == 20 ||origin == 13 || origin == 12 || origin == 11 || origin == 10) {
            super.setOrigin(origin);
            return true;
        }
        else {
            System.out.println("Air cargo doesn't support this code!");
            return false;
        }
    }
}