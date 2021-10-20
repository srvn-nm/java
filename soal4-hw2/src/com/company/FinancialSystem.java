package com.company;
import java.util.*;

/***
 * This class will manage the store and the production lines we have.
 */
public class FinancialSystem {
    //List of Production lines
    private ArrayList<ProductionLine> productionLines;
    //store
    private Store store;

    public FinancialSystem(){
        productionLines = new ArrayList<ProductionLine>();
        store = new Store();
    }

    /**
     * This method will set the company's store.
     * @param store
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * This method will return the store.
     * @return store
     */
    public Store getStore() {
        return store;
    }

    /**
     * This method will print all the production lines we have.
     */
    public void printAllProductionLines(){
        if (productionLines.size() == 0)
            System.out.println("There is no production line!");
        else {
            for (ProductionLine p: productionLines) {
                p.print();
            }
        }
    }

    /**
     * This method will add production lines to the company.
     * @param lineName
     * @param productName
     * @param price
     */
    public void addProductionLine(String lineName , String productName , int price){
        productionLines.add(new ProductionLine(lineName , new Product(productName , price)));
    }

    /**
     *This method will remove a production line from the company.
     * @param name
     */
    public void removeProductionLine(String name){
        if (productionLines.size() == 0)
            System.out.println("There is no production lines!");
        else {
            Iterator<ProductionLine> i = productionLines.iterator();
            while (i.hasNext()){
                ProductionLine j = i.next();
                if (j.getName().equals(name)){
                    i.remove();
                    break;
                }
            }
        }
    }

    /**
     * This method will print all the storage products we have.
     */
    public void printAllStorageProducts(){
        if (store.products.size() == 0)
            System.out.println("The store is empty!");
        else {
            for (int i = 0; i < store.products.size(); i++){
                System.out.println("\nProductName: " + store.products.get(i).getName() + " | Quantity: " + store.numbers.get(i) + "\n");
            }
        }
    }

    /**
     * This method will add products to the store.
     * @param name
     * @param number
     * @param price
     */
    public void addProductToStore(String name , int number , int price){
        store.addProduct(name,price,number);
    }

    /**
     * This method will remove a specific number of products from the store.
     * @param name
     * @param number
     */
    public void removeProductFromStore(String name , int number){
        store.removeProduct(name , number);
    }

    /**
     * This method will print the whole value of the store.
     */
    public void printValueOfStore(){
        System.out.println("\nThe value of all products in store is " + store.calcValue() + ".\n");
    }
}