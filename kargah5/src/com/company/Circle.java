package com.company;

import java.text.DecimalFormat;

public class Circle extends Shape{
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
