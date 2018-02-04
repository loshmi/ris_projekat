/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Point;
import mvc.model.Rectangle;
import mvc.model.Shape;

/**
 * Class that draws rectangle
 * @author loshmi
 */
public class RectangleDrawer extends ShapeDrawer
{
    /**
     * Draws rectangle
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        g.setColor(rectangle.getFill());
        g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getA(), rectangle.getB());
        g.setColor(rectangle.getBorder());
        g.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getA(), rectangle.getB());
    }

    /**
     * Draws selection on rectangle
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        g.setColor(getSelectionFillColor(rectangle));
        g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getA(), rectangle.getB());
        g.setColor(getSelectionBorderColor(rectangle));
        g.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getA(), rectangle.getB());
        
        Point point1 = new Point (rectangle.getX(), rectangle.getY(), rectangle.getBorder());
        
        PointDrawer pointDrawer = new PointDrawer();
        pointDrawer.drawSelectedShape(g, point1);
        
        Point point2 = new Point (rectangle.getX() + rectangle.getA(), rectangle.getY(), rectangle.getBorder());
        
        pointDrawer.drawSelectedShape(g, point2);
        
        Point point3 = new Point (rectangle.getX(), rectangle.getY() + rectangle.getB(), rectangle.getBorder());
        
        pointDrawer.drawSelectedShape(g, point3);
        
        Point point4 = new Point (rectangle.getX() + rectangle.getA(), rectangle.getY() + rectangle.getB(), rectangle.getBorder());
        
        pointDrawer.drawSelectedShape(g, point4);
    }

    /**
     * Calculates color of selection
     * @param shape
     * @return 
     */
    @Override
    public Color getSelectionFillColor (Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        int red = 255 - rectangle.getFill().getRed();
        int green = 255 - rectangle.getFill().getGreen();
        int blue = 255 - rectangle.getFill().getBlue();
        
        if (red == 255 && green == 255 && blue == 255)
        {
            red = white;
            green = white;
            blue = white;
        }
        
        return new Color (red, green, blue, alpha);
    }
}
