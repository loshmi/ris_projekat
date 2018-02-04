/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 * Hexagon class
 * @author loshmi
 */
public class Hexagon extends Shape
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
    public Hexagon(int x, int y, int a, Color border, Color fill)
    {
        super(x, y, border);
        this.a = a;
        this.fill = fill;
    }

    public Hexagon()
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
     * Adapter design pattern used
     * @param point
     * @return 
     */
    @Override
    public boolean containsPoint(Point point)
    {
        heksagon.Hexagon hex = new heksagon.Hexagon (getX(), getY(), a);
        
        return hex.doesContain(point.getX(), point.getY());
    }
    
    @Override
    public Shape cloneShape ()
    {
        return new Hexagon (getX(), getY(), a, getBorder(), fill);
    }
    
    @Override
    public String toString ()
    {
        return "Hexagon: [(" + getX() + ", " + getY() + ") " + a + "]";
    }

    /**
     * Method that sets all attributes based on values of another haxagon
     * @param shape 
     */
    @Override
    public void setShape(Shape shape)
    {
        Hexagon hexagon = (Hexagon) shape;
        
        setX(hexagon.getX());
        setY(hexagon.getY());
        setA(hexagon.getA());
        setBorder(hexagon.getBorder());
        setFill(hexagon.getFill());
    }
}
