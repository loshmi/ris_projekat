/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Point class
 * @author loshmi
 */
public class Point extends Shape
{
    private static final int epsilon = 4;

    /**
     * Constructor
     * @param x
     * @param y
     * @param border 
     */
    public Point(int x, int y, Color border)
    {
        super(x, y, border);
    }

    
    @Override
    public void moveTo (int x, int y)
    {
        setX (x);
        setY (y);
    }
    
    @Override
    public void moveBy (int x, int y)
    {
        setX (getX() + x);
        setY (getY() + y);
    }
    
    /**
     * Method that checks if line is selected
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        return super.getX() - epsilon <= point.getX() && super.getX() + epsilon >= point.getX() &&
               super.getY() - epsilon <= point.getY() && super.getY() + epsilon >= point.getY();
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Point)
        {
            Point point = (Point)obj;
            if (getX() == point.getX() && getY() == point.getY())
            {
                return true;
            }
            else
            {
                return false;
            }
	}
        else
        {
            return false;
        }
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Point (getX(), getY(), getBorder());
    }
    
    @Override
    public String toString ()
    {
       return "Point: (" + getX () + ", " + getY() + ")"; 
    }
    
    /**
     * Method that sets all attributes based on values of another point
     * @param shape 
     */
    @Override
    public void setShape (Shape shape)
    {
        Point point = (Point) shape;
        
        this.setX(point.getX());
        this.setY(point.getY());
        this.setBorder(point.getBorder());
    }
}
