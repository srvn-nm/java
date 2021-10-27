package com.company;

import java.util.*;

/**
 * polygon class show the rectangle and triangle information
 * @author Sarvin Nami
 */
public abstract class Polygon extends Shape  {
    //sides of each polygon
    ArrayList<Integer> sides;
    /**
     * constructor for polygon class
     * @param args sides
     */
    public Polygon(Integer ... args)
    {
        sides=new ArrayList<Integer>();
        Collections.addAll(sides, args);
    }

    /**
     * getter for sides
     * @return sides of each polygon
     */
    public ArrayList<Integer> getSides() {
        return sides;
    }
}
