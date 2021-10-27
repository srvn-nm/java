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
class Circle extends Shape{
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    //radius of the circle
    private int radius;
    /**
     * constructor for circle class
     */
    public Circle(int radius)
    {
        this.radius=radius;
    }
    /**
     * getter for radius
     * @return radius
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * overriding toString method for circle
     * @return circle info in string type
     */
    @Override
    public String toString()
    {
        return "Circle - radius : " +radius;
    }

    /**
     * overriding calculatePerimeter
     * @return perimeter
     */
    @Override
    public double calculatePerimeter()
    {
        double perimeter;
        perimeter=2*3.14*radius;
        return perimeter;
    }

    /**
     * overriding calculateArea
     * @return Area
     */
    @Override
    public double calculateArea()
    {
        double area;
        area=3.14*radius*radius;
        return area;
    }

    /**
     * overriding draw method
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Circle. ");
        System.out.println("-----------------------");
        System.out.println("Perimeter : " + df2.format(calculatePerimeter()));
        System.out.println("Area : " + df2.format(calculateArea()));
        System.out.println();
    }

}
