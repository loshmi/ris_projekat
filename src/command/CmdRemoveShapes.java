/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import mvc.model.DrawingModel;
import mvc.model.Shape;

/**
 * Class used for Command pattern. It removes multiple shapes from the canvas.
 * @author loshmi
 */
public class CmdRemoveShapes implements Command
{
    private DrawingModel drawingModel;
    private ArrayList<Shape> oldShapes;
    private ArrayList<Shape> selectedShapes;

    /**
     * Constructor that takes DrawingModel and ArrayList of selected shapes.
     * @param drawingModel
     * @param selectedShapes 
     */
    public CmdRemoveShapes(DrawingModel drawingModel, ArrayList<Shape> selectedShapes)
    {
        this.drawingModel = drawingModel;
        this.selectedShapes = selectedShapes;
        this.oldShapes = new ArrayList <> ();
        
        for (Shape shape : selectedShapes)
        {
            oldShapes.add(shape);
        }
    }
    
    /**
     * Method that removing selected shapes from canvas
     */
    @Override
    public void execute()
    {
        if (drawingModel.getSelectedShapes().size() > 0)
        {
            for (Shape shape : selectedShapes)
            {
                drawingModel.removeShape(shape);
            }
        }
        else if (drawingModel.getSelectedShapes().size() == 0)
        {
            for (Shape shape : oldShapes)
            {
                drawingModel.removeShape(shape);
            }
        }
    }

    /**
     * Undo method. It brings back removed shapes
     */
    @Override
    public void unexecute()
    {
        for (Shape shape : oldShapes)
        {
            drawingModel.add(shape);
        }
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return "Multiple shapes removed";
    }
}
