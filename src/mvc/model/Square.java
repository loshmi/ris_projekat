/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Square class
 * @author Milos
 */
public class Square extends Shape
{
    private int a;
    private Color fill;

    /**
     * Constructor
     * @param x
     * @param y
     * @param a
     * @param border
     * @param fill 
     */
    public Square(int x, int y, int a, Color border, Color fill)
    {
        super(x, y, border);
        this.a = a;
        this.fill = fill;
    }

    public Square()
    {
        super ();
    }
    
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
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
     * Method that checks if Square is selected
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        return super.getX() <= point.getX() && super.getX() + a >= point.getX() &&
               super.getY() <= point.getY() && super.getY() + a >= point.getY();
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Square (getX(), getY(), a, getBorder(), fill);
    }
    
    @Override
    public String toString ()
    {
        return "Square: [(" + getX() + ", " + getY() + ") " + a + "]";
    }

    /**
     * Method that sets all attributes based on values of another square
     * @param shape 
     */
    @Override
    public void setShape(Shape shape)
    {
        Square square = (Square) shape;
        
        setX(square.getX());
        setY(square.getY());
        setA(square.getA());
        setBorder(square.getBorder());
        setFill(square.getFill());
    }
}
