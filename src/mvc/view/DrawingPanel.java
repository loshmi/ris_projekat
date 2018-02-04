/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import helper.ShapeDrawerFactory;
import java.awt.Color;
import mvc.model.Shape;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Class that represents canvas
 * @author loshmi
 */
public class DrawingPanel extends JPanel
{
    private ArrayList<Shape> shapes;
    private ArrayList<Shape> selectedShapes;

    public DrawingPanel()
    {
        setBackground(Color.white);
        this.shapes = new ArrayList<>();
        this.selectedShapes = new ArrayList<>();
    }

    public void setShapes(ArrayList<Shape> shapes)
    {
        this.shapes = shapes;
    }
    
    public void setSelectedShapes(ArrayList<Shape> selectedShapes)
    {
        this.selectedShapes = selectedShapes;
    }
    
    @Override
    public void paint (Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        ShapeDrawer shapeDrawer;
        for (Shape shape : shapes)
        {
            shapeDrawer = ShapeDrawerFactory.getShapeDrawer(shape);
            shapeDrawer.drawShape(g, shape);
        }
        
        for (Shape selectedShape : selectedShapes)
        {
            shapeDrawer = ShapeDrawerFactory.getShapeDrawer(selectedShape);
            shapeDrawer.drawSelectedShape(g, selectedShape);
        }
        repaint();
    }
}
