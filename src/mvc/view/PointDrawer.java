/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Point;
import mvc.model.Shape;

/**
 * Class that draws point
 * @author loshmi
 */
public class PointDrawer extends ShapeDrawer
{

    public PointDrawer() {
    }
    
    /**
     * Draws point
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        g.setColor(shape.getBorder());
        Point point = (Point) shape;
        g.drawLine(point.getX() - ViewConstants.CROSS_SIZE, point.getY(), point.getX() + ViewConstants.CROSS_SIZE, point.getY());
        g.drawLine(point.getX(), point.getY() + ViewConstants.CROSS_SIZE, point.getX(), point.getY() - ViewConstants.CROSS_SIZE);
    }
    
    /**
     * Calculates color of selection
     * @param shape
     * @return 
     */
    @Override
    public Color getSelectionFillColor (Shape shape)
    {
        int red = 255 - shape.getBorder().getRed();
        int green = 255 - shape.getBorder().getGreen();
        int blue = 255 - shape.getBorder().getBlue();
        
        if (red == 255 && green == 255 && blue == 255)
        {
            red = white;
            green = white;
            blue = white;
        }
        
        return new Color (red, green, blue, alpha);
    }

    /**
     * Draws selection on point
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        g.setColor(getSelectionBorderColor(shape));
        Point point = (Point) shape;
        
        g.setColor(getSelectionFillColor(point));
        g.fillOval(point.getX() - ViewConstants.CROSS_SIZE, point.getY() - ViewConstants.CROSS_SIZE, ViewConstants.CROSS_SIZE * 2, ViewConstants.CROSS_SIZE * 2);
        g.setColor(getSelectionBorderColor(shape));
        g.drawOval(point.getX() - ViewConstants.CROSS_SIZE, point.getY() - ViewConstants.CROSS_SIZE, ViewConstants.CROSS_SIZE * 2, ViewConstants.CROSS_SIZE * 2);
    }
    
}
