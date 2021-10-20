package com.company;
import java.util.*;

/***
 * This class is our store where we can find out how many of which product we have.
 * @author Sarvin Nami
 */
public class Store {
    //List of products
    public ArrayList<Product> products;
    //List of quantities
    public ArrayList<Integer> numbers;

    public Store() {
        products = new ArrayList<Product>();
        numbers = new ArrayList<Integer>();
    }

    /**
     * This method will add products and their quantities to the store.
     * @param name
     * @param price
     * @param number
     */
    public void addProduct(String name, int price, int number) {
        if(products.size() > 0){
            for (int i = 0; i <= products.size(); i++) {
                if (i > 0 && i == products.size())
                    break;
                else if (products.get(i).getName().equals(name) && products.get(i).getPrice() == price) {
                    numbers.set(i, numbers.get(i) + number);
                    break;
                } else if (i == products.size() - 1 && !(products.get(i).getName().equals(name)) && products.get(i).getPrice() != price) {
                    products.add(new Product(name, price));
                    numbers.add(number);
                    break;
                }
            }
        } else {
            products.add(new Product(name, price));
            numbers.add(number);
        }
    }

    /**
     * This method will remove the specific number of products in our store.
     * @param name
     * @param number
     */
    public void removeProduct(String name, int number) {
        if(products.size() == 0)
            System.out.println("Store is already empty!");
        else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().equals(name) && numbers.get(i) > number) {
                    numbers.set(i, numbers.get(i) - number);
                    break;
                } else {
                    Iterator<Product> i1 = products.iterator();
                    Iterator<Integer> i3 = numbers.iterator();
                    while (i1.hasNext() && i3.hasNext()) {
                        Product j = i1.next();
                        int x = i3.next();
                        if (j.getName().equals(name) && x == number) {
                            i1.remove();
                            i3.remove();
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will return the whole value of our store.
     * @return result
     */
    public int calcValue(){
        int result = 0;
        for (int i = 0; i < products.size(); i++) {
            result += products.get(i).getPrice() * numbers.get(i);
        }
        return result;
    }
}