/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.DrawingModel;
import mvc.model.Shape;

/**
 * Class used for Command pattern. It brings selected shape to the top of z axis.
 * @author loshmi
 */
public class CmdFirstShape implements Command
{
    private DrawingModel drawingModel;
    private int index;
    private Shape shape;

    /**
     * Constructor that takes DrawingModel and index of shape that will be bring to the top of z axes.
     * @param drawingModel
     * @param index 
     */
    public CmdFirstShape(DrawingModel drawingModel, int index) 
    {
        this.drawingModel = drawingModel;
        this.index = index;
        this.shape = drawingModel.getSelectedShape(0);
    }
    
    /**
     * Method that is bringing selected shape to the front.
     */
    @Override
    public void execute()
    {
        drawingModel.add(shape);
        drawingModel.removeShape(drawingModel.getShape(index));
    }

    /**
     * Undo method, it brings shape back.
     */
    @Override
    public void unexecute()
    {
        drawingModel.removeShape(shape);
        drawingModel.add(index, shape);
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return "Sent first: " + shape;
    }
}
