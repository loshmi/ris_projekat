/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Rectangle class
 * @author Milos
 */
public class Rectangle extends Shape
{
    private int a;
    private int b;
    private Color fill;
    
    public Rectangle ()
    {
        super ();
    }

    /**
     * Constructor
     * @param x
     * @param y
     * @param a
     * @param b
     * @param border
     * @param fill 
     */
    public Rectangle(int x, int y, int a, int b, Color border, Color fill) {
        super(x, y, border);
        this.a = a;
        this.b = b;
        this.fill = fill;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
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
    
    @Override
    public String toString ()
    {
        String out = "";
        
        out += "Rectangle: (" + super.getX() + ", " + super.getY() + ")" + "[" + a + ", " + b + "]";
        
        return out;
    }

    /**
     * Method that checks is rectangle is selected
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        return super.getX() <= point.getX() && super.getX() + a >= point.getX() &&
               super.getY() <= point.getY() && super.getY() + b >= point.getY();
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Rectangle (getX(), getY(), a, b, getBorder(), fill);
    }

    /**
     * Method that sets all attributes based on values of another rectangle
     * @param shape 
     */
    @Override
    public void setShape(Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        
        setX(rectangle.getX());
        setY(rectangle.getY());
        setA(rectangle.getA());
        setA(rectangle.getB());
        setBorder(rectangle.getBorder());
        setFill(rectangle.getFill());
    }
}
