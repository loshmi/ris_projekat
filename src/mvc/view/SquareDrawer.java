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
import mvc.model.Square;

/**
 * Class that draws square
 * @author loshmi
 */
public class SquareDrawer extends ShapeDrawer
{
    /**
     * Draws square
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        Square square = (Square) shape;
        g.setColor(square.getFill());
        g.fillRect(square.getX(), square.getY(), square.getA(), square.getA());
        g.setColor(square.getBorder());
        g.drawRect(square.getX(), square.getY(), square.getA(), square.getA());
    }

    /**
     * Draws selection on square
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        Square square = (Square) shape;
        g.setColor(getSelectionFillColor(square));
        g.fillRect(square.getX(), square.getY(), square.getA(), square.getA());
        g.setColor(getSelectionBorderColor(square));
        g.drawRect(square.getX(), square.getY(), square.getA(), square.getA());
        
        Point point1 = new Point (square.getX(), square.getY(), square.getBorder());
        
        PointDrawer pointDrawer = new PointDrawer();
        pointDrawer.drawSelectedShape(g, point1);
        
        Point point2 = new Point (square.getX() + square.getA(), square.getY(), square.getBorder());
        
        pointDrawer.drawSelectedShape(g, point2);
        
        Point point3 = new Point (square.getX(), square.getY() + square.getA(), square.getBorder());
        
        pointDrawer.drawSelectedShape(g, point3);
        
        Point point4 = new Point (square.getX() + square.getA(), square.getY() + square.getA(), square.getBorder());
        
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
        Square square = (Square) shape;
        int red = 255 - square.getFill().getRed();
        int green = 255 - square.getFill().getGreen();
        int blue = 255 - square.getFill().getBlue();
        
        if (red == 255 && green == 255 && blue == 255)
        {
            red = white;
            green = white;
            blue = white;
        }
        
        return new Color (red, green, blue, alpha);
    }
}
