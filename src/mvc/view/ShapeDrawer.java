/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Shape;

/**
 * Abstract class that handles drawing of shapes
 * @author loshmi
 */
public abstract class ShapeDrawer
{
    protected int alpha = 80;
    protected int white = 220;
    public abstract void drawShape (Graphics g, Shape shape);
    public abstract void drawSelectedShape (Graphics g, Shape shape);
    protected abstract Color getSelectionFillColor (Shape shape);
    
    /**
     * Method that calculates border color for selection
     * @param shape
     * @return 
     */
    protected Color getSelectionBorderColor (Shape shape)
    {
        return new Color (255 - shape.getBorder().getRed(), 255 - shape.getBorder().getGreen(), 255 - shape.getBorder().getBlue(), alpha);
    }
}
