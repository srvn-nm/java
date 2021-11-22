package com.company;

public class FastGroundCargo extends GroundCargo {

    public FastGroundCargo(boolean insurance,float weight) {
        super();
        this.type = "Fast Ground Cargo";
        this.insurance = insurance;
        this.setWeight(weight);
    }

    @Override
    public int calculatePrice() {
        return 2 * super.calculatePrice();
    }
}