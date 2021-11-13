package com.company;

import java.util.*;

public class Inventory {
    private final HashMap<Product, Integer> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    /**
     * This method will add specific number of products to hashmap.
     * @param product
     * @param quantity
     */
    public void addProduct(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            System.out.println("Product already exists!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        products.put(product, quantity);
    }

    /**
     * This method will update the quantity of a product.
     * @param product
     * @param newQuantity
     */
    public void updateStock(Product product, Integer newQuantity) {
        if (newQuantity < 0) {
            System.out.println("Invalid Stock!");
            return;
        }

        if (newQuantity == 0) {
            products.remove(product);
            return;
        }

        products.replace(product, newQuantity);
    }

    /**
     * This method will remove a product.
     * @param product
     */
    public void removeProduct(Product product) {
        if (!products.containsKey(product)) {
            System.out.println("Product does not exist!");
            return;
        }

        products.remove(product);
    }

    /**
     * This method will print the products and their quantities.
     */
    public void printAllProducts() {
        int i = 1;
        for (Product product : products.keySet()) {
            System.out.printf("%2d) %s \tQuantity: %d\n", i, product.toString(), products.get(product));
            i++;
        }
    }

    /**
     * This method will return a product of a specific index.
     * @param index
     * @return a product or null
     */
    public Product getProduct(int index) {
        if (index < 0 || index >= products.size()) return null;
        return (Product) products.keySet().toArray()[index];
    }

    /**
     * This method will return the whole hashmap.
     * @return products
     */
    public HashMap<Product, Integer> getProducts() {
        return products;
    }
}