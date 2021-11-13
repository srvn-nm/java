package com.company;
import java.time.*;
import java.util.Objects;

public class Product {
    private final String name;
    private final String category;
    private final double weight;
    private final double price;
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;

    public Product(String name, String category, double weight, double price, LocalDate manufacture_date, LocalDate expiration_date) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
        manufactureDate = manufacture_date;
        expirationDate = expiration_date;
    }

    /**
     * This method will return the name.
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * This method will return the price.
     * @return price
     */
    public double getPrice() {
        return price;
    }
    /**
     * This method will return the price.
     * @return price
     */
    public double getWeight() {
        return weight;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.weight, weight) == 0 && Double.compare(product.price, price) == 0 && Objects.equals(product.name, name) && Objects.equals(category, product.category) && Objects.equals(manufactureDate, product.manufactureDate) && Objects.equals(expirationDate, product.expirationDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, category, weight, price, manufactureDate, expirationDate);
    }
    @Override
    public String toString() {
        return "Product:\n" + "Name : " + name + " | Category : " + category + " | Weight : " + weight + " |  Price : " + price + " |  ManufactureDate : " + manufactureDate + " |  ExpirationDate : " + expirationDate + ".\n";
    }
}