/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Circle;
import mvc.model.Point;
import mvc.model.Shape;

/**
 * Class that draws circle
 * @author loshmi
 */
public class CircleDrawer extends ShapeDrawer
{
    /**
     * Draws circle
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        Circle circle = (Circle) shape;
        int topLeftX = circle.getX() - circle.getR();
        int topLeftY = circle.getY() - circle.getR();
        g.setColor(circle.getFill());
        g.fillOval(topLeftX, topLeftY, 2 * circle.getR(), 2 * circle.getR());
        g.setColor(circle.getBorder());
        g.drawOval(topLeftX, topLeftY, 2 * circle.getR(), 2 * circle.getR());
    }

    /**
     * Draws selection on circle
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        Circle circle = (Circle) shape;
        int topLeftX = circle.getX() - circle.getR();
        int topLeftY = circle.getY() - circle.getR();
        g.setColor(getSelectionFillColor(shape));
        g.fillOval(topLeftX, topLeftY, 2 * circle.getR(), 2 * circle.getR());
        g.setColor(getSelectionBorderColor(shape));
        g.drawOval(topLeftX, topLeftY, 2 * circle.getR(), 2 * circle.getR());
        
        Point point = new Point (circle.getX(), circle.getY(), circle.getBorder());
        
        PointDrawer pointDrawer = new PointDrawer();
        pointDrawer.drawSelectedShape(g, point);
    }

    /**
     * Calculates color of selection
     * @param shape
     * @return 
     */
    @Override
    public Color getSelectionFillColor (Shape shape)
    {
        Circle circle = (Circle) shape;
        int red = 255 - circle.getFill().getRed();
        int green = 255 - circle.getFill().getGreen();
        int blue = 255 - circle.getFill().getBlue();
        
        if (red == 255 && green == 255 && blue == 255)
        {
            red = white;
            green = white;
            blue = white;
        }
        
        return new Color (red, green, blue, alpha);
    }
}
