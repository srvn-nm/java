package com.company;

public class NormalGroundCargo extends GroundCargo {

    public NormalGroundCargo(boolean insurance,float weight) {
        super();
        this.type = "Normal Ground Cargo";
        this.insurance = insurance;
        this.setWeight(weight);
    }
}