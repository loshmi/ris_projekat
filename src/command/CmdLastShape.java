/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.DrawingModel;
import mvc.model.Shape;

/**
 * Class used for Command pattern. It sends selected shape to the bottom of z axis
 * @author loshmi
 */
public class CmdLastShape implements Command
{
    private DrawingModel drawingModel;
    int index;
    Shape shape;

    /**
     * Constructor that takes DrawingModel and index of a shape that will be sent to the last position on z axis
     * @param drawingModel
     * @param index 
     */
    public CmdLastShape(DrawingModel drawingModel, int index)
    {
        this.drawingModel = drawingModel;
        this.index = index;
        shape = drawingModel.getSelectedShape(0);
    }
    
    /**
     * Method that is sending shape to the last place on z axis.
     */
    @Override
    public void execute()
    {
        drawingModel.add(0, shape);
        drawingModel.removeShape(index + 1);
    }
    
    /**
     * Undo method. It bring selected shape from last position to the previous position on z axis.
     */
    @Override
    public void unexecute()
    {
        drawingModel.removeShape(0);
        drawingModel.add(index, shape);
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return "Sent last: " + shape;
    }
}
