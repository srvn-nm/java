package com.company;

public class BreakableGroundCargo extends GroundCargo {

    public BreakableGroundCargo(boolean insurance,float weight) {
        super();
        this.type = "Breakable Ground Cargo";
        this.insurance = insurance;
        this.setWeight(weight);
    }

    @Override
    public int calculatePrice() {
        return 3 * super.calculatePrice();
    }
}