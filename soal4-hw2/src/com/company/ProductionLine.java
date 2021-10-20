package com.company;

/***
 * This class Will store the production lines and the products where will be produced.
 * @author Sarvin Nami
 */
public class ProductionLine {
    //Production line name
    private String name;
    //Production line Product
    private Product type;

    /**
     * This method will return the name of the production line.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method will set the name of the production line.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will set the type of the production line.
     * @param type
     */
    public void setType(Product type) {
        this.type = type;
    }

    /**
     * This method will return the type of the production line.
     * @return type
     */
    public Product getType() {
        return type;
    }

    /**
     * This method will print the production line's information.
     */
    public void print(){
        System.out.println("\nName: " + getName() + " | Product: " + getType().getName() + " | Price: " + getType().getPrice() + "\n");
    }
    public ProductionLine(String name , Product type){
        this.name = name;
        this.type = type;
    }
}