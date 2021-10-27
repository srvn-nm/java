package com.company;

import java.text.DecimalFormat;

/**
 * Triangle class extends from shape and represents a triangle with 3 sides
 */
public class Triangle extends Polygon {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    /**
     * constructor for triangle class
     * @param args sides
     */
    public Triangle(Integer ... args)
    {
        super(args);
    }
    /**
     * the methos that checks if the triangle is equilateral
     * @return true if it is equilateral
     */
    public boolean isEquilateral()
    {
        return sides.get(0) == sides.get(1) && sides.get(0) == sides.get(2);
    }
    /**
     * calculate triangle's perimeter
     * @return the perimeter
     */

    @Override
    public double calculatePerimeter()
    {
        double perimeter=0;
        for (Integer side : sides) perimeter += side;
        return perimeter;
    }
    /**
     * calculate triangle's area
     * @return  Math.sqrt(p * (p - sides.get(0)) * (p - sides.get(1)) * (p - sides.get(2)))
     */
    @Override
    public double calculateArea()
    {
        double p = calculatePerimeter() / 2;
        return  Math.sqrt(p * (p - sides.get(0)) * (p - sides.get(1)) * (p - sides.get(2)));
    }

    /**
     * show the states of triangle
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Triangle. ");
        System.out.println("-------------------------");
        System.out.println("Perimeter : " + calculatePerimeter());
        System.out.println("Area : " + df2.format(calculateArea()));
        System.out.println("is Equilateral : " + isEquilateral());
        System.out.println();
    }
    @Override
    public String toString()
    {
        return "Triangle- sides : side1 =" + sides.get(0) + " |side2 = " + sides.get(1) + " |side3 = " + sides.get(2);
    }
}
