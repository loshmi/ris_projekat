/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import mvc.model.Hexagon;
import mvc.model.Point;
import mvc.model.Shape;

/**
 * Class that draws hexagon
 * @author loshmi
 */
public class HexagonDrawer extends ShapeDrawer
{
    /**
     * Draws hexagon
     * Adapter pattern used
     * @param g
     * @param shape 
     */
    @Override
    public void drawShape(Graphics g, Shape shape)
    {
        Hexagon hexagon = (Hexagon) shape;
        heksagon.Hexagon hex = new heksagon.Hexagon (hexagon.getX(), hexagon.getY(), hexagon.getA());
        hex.setBorderColor(hexagon.getBorder());
        hex.setAreaColor(hexagon.getFill());
        
        hex.paint(g);
    }

    /**
     * Draws selection on hexagon
     * @param g
     * @param shape 
     */
    @Override
    public void drawSelectedShape(Graphics g, Shape shape)
    {
        Hexagon hexagon = (Hexagon) shape;
        heksagon.Hexagon hex = new heksagon.Hexagon (hexagon.getX(), hexagon.getY(), hexagon.getA());
        hex.setBorderColor(getSelectionBorderColor(hexagon));
        hex.setAreaColor(getSelectionFillColor(hexagon));
        
        hex.paint(g);
        
        Point point = new Point (hexagon.getX(), hexagon.getY(), hexagon.getBorder());
        
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
        Hexagon hexagon = (Hexagon) shape;
        int red = 255 - hexagon.getFill().getRed();
        int green = 255 - hexagon.getFill().getGreen();
        int blue = 255 - hexagon.getFill().getBlue();
        
        if (red == 255 && green == 255 && blue == 255)
        {
            red = white;
            green = white;
            blue = white;
        }
        
        return new Color (red, green, blue, alpha);
    }
}
