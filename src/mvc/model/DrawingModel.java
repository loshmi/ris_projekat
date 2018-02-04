/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class for MVC pattern
 * @author loshmi
 */
public class DrawingModel implements Serializable
{
    private static final long serialVersionUID = 6926472295622776147L;
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Shape> selectedShapes = new ArrayList<>();

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    public void add (Shape shape)
    {
        shapes.add(shape);
    }
    
    public void add (int index, Shape shape)
    {
        shapes.add(index, shape);
    }
    
    public Shape getShape (int index)
    {
        return shapes.get(index);
    }
    
    public boolean removeShape (Shape shape)
    {
        return shapes.remove(shape);
    }
    
    public Shape removeShape (int index)
    {
        return shapes.remove(index);
    }
    
    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }
    
    public void addSelected (Shape shape)
    {
        selectedShapes.add(shape);
    }
    
    public Shape getSelectedShape (int index)
    {
        return selectedShapes.get(index);
    }
    
    public boolean removeSelectedShape (Shape shape)
    {
        return selectedShapes.remove(shape);
    }
    
    public void copyShapes (ArrayList<Shape> newShapes)
    {
        shapes = new ArrayList<> (newShapes);
    }
    
    public void selectAll ()
    {
        selectedShapes.clear();
        for (Shape shape : shapes)
        {
            selectedShapes.add(shape);
        }
    }
}
