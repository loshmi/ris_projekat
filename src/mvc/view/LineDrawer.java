/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Line;
import mvc.model.Point;
import mvc.model.Shape;

/**
 * Class that draws line
 * @author loshmi
 */
public class LineDrawer extends ShapeDrawer
{
    /**
     * Draws line
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        Line line = (Line) shape;
        g.setColor(line.getBorder());
        g.drawLine(line.getX(), line.getY(), line.getX1(), line.getY1());
    }

    /**
     * Draws selection on line
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        Line line = (Line) shape;
        Point point1 = new Point (line.getX(), line.getY(), line.getBorder());
        
        PointDrawer pointDrawer = new PointDrawer();
        pointDrawer.drawSelectedShape(g, point1);
        
        Point point2 = new Point (line.getX1(), line.getY1(), line.getBorder());
        
        pointDrawer.drawSelectedShape(g, point2);
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
}
