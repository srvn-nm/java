package com.company;
/**
 * Rectangle class represents a rectangle with four sides
 * this class inherits from polygan class
 * @author Sarvin Nami
 */
public class Rectangle extends Polygon {
    /**
     * constructor for rectangle class
     * @param args sides
     */
    public Rectangle(Integer ... args)
    {
        super(args);
    }
    /**
     * this method checks if rectangle is square or not
     * @return true if it is a square
     */
    public boolean isSquare()
    {
        return sides.get(0) == sides.get(1) && sides.get(0) == sides.get(3);
    }
    /**
     * method that calculate perimeter of the rectangle
     * @return perimeter of rectangle
     */
    @Override
    public double calculatePerimeter()
    {
        double perimeter=0;
        for (Integer side : sides) {
            if (!isSquare()) {
                perimeter += side;
            }
            if (isSquare()) {
                perimeter = 4 * side;
            }
        }
        return perimeter;
    }
    /**
     * method that calculate area of rectangle
     * @return area of rectangle
     */
    @Override
    public double calculateArea()
    {
        return Math.sqrt(sides.get(0) * sides.get(1) * sides.get(2) * sides.get(3) );
    }

    /**
     * draw the rectangle with its area and perimeter
     */
    @Override
    public void draw()
    {
        System.out.println();
        System.out.println("The shape is a Rectangle. ");
        System.out.println("--------------------------");
        System.out.println("Perimeter : " + calculatePerimeter());
        System.out.println("Area : " + calculateArea());
        System.out.println("is Square : " + isSquare());
        System.out.println();
    }

    @Override
    public String toString() {
        return "Rectangle - sides : side1 =" + sides.get(0) + " |side2 = " + sides.get(1) + " |side3 = " + sides.get(2) + " |side4 = " + sides.get(3);
    }
}
