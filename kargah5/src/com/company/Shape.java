package com.company;

import java.text.DecimalFormat;

/**
 * shape class that extends from paint class
 * @author Sarvin Nami
 */
public abstract class Shape extends Paint {
    /**
     * method calculatePerimeter calculate the perimeter of shape
     * @return perimeter of the shape
     */
    public abstract double calculatePerimeter();

    /**
     * method calculateArea claculate the area of the shape
     * @return area of the shape
     */
    public abstract double calculateArea();

    /**
     * this method draw the information of the shapes such as perimeter and area and the shape kind
     */
    public abstract void draw();

    @Override
    public String toString()
    {
        return "Shape{}";
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}
