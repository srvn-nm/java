package com.company;

/***
 * This class will store the products and their values.
 * @author Sarvin Nami
 */
public class Product{
    // The product's name
    private String name;
    // The product's value
    private int price;
    public Product(String productName , int productPrice){
        name = productName;
        price = productPrice;
    }

    /**
     * This method will set the value of the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will set the price.
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * This method will return the name of the product.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method will return the price of the product.
     * @return price
     */
    public int getPrice() {
        return price;
    }
}