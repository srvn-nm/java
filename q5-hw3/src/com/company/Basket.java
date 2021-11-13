package com.company;
import java.util.*;
public class Basket {
    private final ArrayList<Product> products;
    public Basket() {
        this.products = new ArrayList<>();
    }

    /**
     * This method will add a product to the arraylist.
     * @param product
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * This method will remove a product from the arraylist.
     * @param product
     */
    public void removeProduct(Product product) {
        if (!products.contains(product)) {
            System.out.println("Product doesnt exists!");
            return;
        }

        products.remove(product);
    }

    /**
     * This method will print all the products.
     */
    public void printAllProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%2d) %s\n", i + 1, products.get(i).toString());
        }
    }

    /**
     * This method will return the products arraylist.
     * @return products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * This method will return the balance of the whole products in our list.
     * @return sum
     */
    public double balance() {
        double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice();
        }

        return sum;
    }

    /**
     * This method will return a product of a specific index.
     * @param index
     * @return null or a product
     */
    public Product getProduct(int index) {
        if (index < 0 || index >= products.size()) return null;
        return products.get(index);
    }
}