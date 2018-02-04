/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Circle class
 * @author Milos
 */
public class Circle extends Shape
{
    private int r;
    private Color fill;

    /**
     * Constructor
     * @param x
     * @param y
     * @param r
     * @param border
     * @param fill 
     */
    public Circle(int x, int y, int r, Color border, Color fill)
    {
        super(x, y, border);
        this.r = r;
        this.fill = fill;
    }
    
    public Circle ()
    {
        super ();
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
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
     * Method that checks if circle contains specified point
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        return super.getX() - r <= point.getX() && super.getX() + r >= point.getX() &&
               super.getY() - r <= point.getY() && super.getY() + r >= point.getY();
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Circle (getX(), getY(), r, getBorder(), fill);
    }
    
    @Override
    public String toString ()
    {
        return "Circle: [(" + getX() + ", " + getY() + ") " + r + "]";
    }

    /**
     * Method that sets all attributes based on values of another circle
     * @param shape 
     */
    @Override
    public void setShape(Shape shape)
    {
        Circle circle = (Circle) shape;
        
        setX(circle.getX());
        setY(circle.getY());
        setR(circle.getR());
        setBorder(circle.getBorder());
        setFill(circle.getFill());
    }
}
