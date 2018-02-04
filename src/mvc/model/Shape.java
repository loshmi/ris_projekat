/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;
import java.io.Serializable;

/**
 * Shape super class
 * @author loshmi
 */
public abstract class Shape implements Moveable, Serializable
{
    private static final long serialVersionUID = 7526472295622776147L;
    public static final Color DEFAULT_BORDER = Color.BLACK;
    public static final Color DEFAULT_FILL = Color.RED;
    
    private int x;
    private int y;
    private Color border;
    
    public Shape ()
    {
        
    }
    
    public Shape(Color border) {
        this.border = border;
    }
    
    public Shape(int x, int y, Color border) {
        this.x = x;
        this.y = y;
        this.border = border;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }
    
    @Override
    public String toString ()
    {
        return border.toString();
    }
    
    public abstract boolean containsPoint (Point point);
    
    public abstract Shape cloneShape ();
    
    public abstract void setShape (Shape shape);
}
