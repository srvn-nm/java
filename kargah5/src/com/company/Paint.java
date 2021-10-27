package com.company;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Paint class represents a paint program with three types of shape : triangle , circle , rectangle
 * @author Sarvin Nami
 */
public class Paint {
    //List of shapes
    private ArrayList<Shape> shapes;
    /**
     * constructor for paint class
     */
    public Paint()
    {
        shapes=new ArrayList<Shape>();
    }
    /**
     * method drawall for show the information of the shape
     */
    public void drawAll()
    {
        for(Shape shps : shapes)
            shps.draw();
        System.out.println();
    }

    /**
     * add a shape to the list
     * @param shape
     */
    public void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    /**
     * print all the shapes of the list in string version
     */
    public void printAll()
    {
        for(Shape shape : shapes)
        {
            System.out.println(shape.toString());
            System.out.println();
        }
    }

    /**
     * this method check if we have square or equilateral in our list of shapes
     */
    public void describeEqualSides()
    {
        for(Shape shape : shapes)
        {
            if(shape instanceof Triangle && ((Triangle) shape).isEquilateral())
            {
                System.out.println("This Triangle is Equilateral");
                System.out.println(shape.toString());
                System.out.println();
            }
            if(shape instanceof Rectangle && ((Rectangle) shape).isSquare())
                System.out.println("This Rectangle Is Square");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Paint))
            return false;
        Paint paint = (Paint) o;
        return Objects.equals(shapes, paint.shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapes);
    }
}
