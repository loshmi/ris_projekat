/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Line class
 * @author Milos
 */
public class Line extends Shape
{
    private int x1;
    private int y1;

    private static final int epsilon = 4;
    /**
     * Constructor
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param border 
     */
    public Line(int x, int y, int x1, int y1, Color border) {
        super(x, y, border);
        this.x1 = x1;
        this.y1 = y1;
    }

    public Line()
    {
        super ();
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }
    
    @Override
    public void moveTo (int x, int y)
    {
        int tempX = getX();
        int tempY = getY();
        setX (x);
        setY (y);
        setX1(x1 + (x - tempX));
        setY1(y1 + (y - tempY));
    }
    
    @Override
    public void moveBy (int x, int y)
    {
        setX (getX() + x);
        setY (getY() + y);
        setX1 (x1 + x);
        setY1 (y1 + y);
    }

    /**
     * Method that checks if line containes given point
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        return p(point) * 2 / calculateDistance() <= epsilon;
    }
    
    private int p (Point point)
    {
        return (int) (0.5 * Math.abs(super.getX() * y1 + super.getY() * point.getX() + x1 * point.getY() - point.getX() * y1 - point.getY() * super.getX() - x1 * super.getY()));
    }
    
    private int calculateDistance ()
    {
        double temp = Math.sqrt(Math.pow((x1 - super.getX()), 2) + Math.pow((y1 - super.getY()), 2));
        return (int) temp;
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Line (getX(), getY(), x1, y1, getBorder());
    }
    
    @Override
    public String toString ()
    {
        return "Line: [(" + getX() + ", " + getY() + "), (" + x1 + ", " + y1 + ")]";
    }

    /**
     * Method that sets all attributes based on values of another line
     * @param shape 
     */
    @Override
    public void setShape(Shape shape)
    {
        Line line = (Line) shape;
        
        setX(line.getX());
        setY(line.getY());
        setX1(line.getX1());
        setY1(line.getY1());
        setBorder(line.getBorder());
    }
}
