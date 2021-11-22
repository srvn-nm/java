package com.company;

public class Cargo {

    //Total cost of each cargo transportation
    public int price;
    //Distance of origin and destination of cargo transportation
    public int distance;
    //check cargo insurance
    public boolean insurance;
    //keeps destination of cargo as a code
    public int destination;
    //keeps origin of cargo as a code
    public int origin;
    //keeps weight of cargo
    public int weight;
    public String type;

    /**
     * Create a new Cargo
     */
    public Cargo() {
        //Default value of insurance is false - should be checked
        insurance = false;
    }

    /**
     * Set Price  of cargo
     */
    public void setPrice() {
        this.price = calculatePrice();
    }

    /**
     * Get price of Cargo
     *
     * @return Price of cargo
     */
    public int getPrice() {
        return price;
    }

    /**
     * Calculate the Total cost of each cargo
     * @return
     */
    public int calculatePrice() {
        return ((distance * 5000) + calculateWeightPrice() + calculateOverload());
    }

    /**
     * Calculate cost of cargo weight
     * @return
     */
    public int calculateWeightPrice() {
        int result = weight;
        if (weight > 20)
            result = 20;
        if (weight <= 1)
            result = 1;
        return result * 2500;

    }

    /**
     * Check if the cargo insured or not
     *
     * @return true or false
     */
    public boolean isInsurance() {
        return insurance;
    }


    /**
     * Calculate overload of each Cargo
     */
    public int calculateOverload() {
        int result = 0;
        if (weight > 20)
            result = weight - 20;
        return result * 3000;
    }

    /**
     * Set the weight value
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = (int) Math.ceil(weight);
    }

    /**
     * Get the destination of cargo
     * @return destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Get the distance of transportation
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the distance
     */
    public void setDistance() {
        this.distance = Math.abs(destination - origin);
    }

    /**
     * Get the weight of cargo
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Get the origin of cargo
     * @return origin
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * Set the destination of cargo
     * @param destination
     * @return
     */
    public boolean setDestination(int destination) {
        this.destination = destination;
        return true;
    }

    /**
     * Set the insurance of cargo
     * @param insurance
     */
    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    /**
     * Set the origin of cargo
     * @param origin
     * @return
     */
    public boolean setOrigin(int origin) {
        this.origin = origin;
        return true;
    }
    @Override
    public String toString(){
        return "Price: "+this.price+" insurance: "+(this.insurance ? "Yes " : "No ")+"Origin: "+this.origin+" Destination: "+this.destination+" Type: "+this.type;
    }
}