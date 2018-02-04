/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.DrawingModel;
import mvc.model.Shape;

/**
 * Class used for Command pattern. It removes shape from the canvas.
 * @author loshmi
 */
public class CmdRemoveShape implements Command
{
    private DrawingModel drawingModel;
    private Shape shape;

    /**
     * Constructor that takes DrawingModel and shape that will be removed
     * @param drawingModel
     * @param shape 
     */
    public CmdRemoveShape(DrawingModel drawingModel, Shape shape) {
        this.drawingModel = drawingModel;
        this.shape = shape;
    }

    /**
     * Method that is removing shape from the canvas (ArrayList inside DrawingModel)
     */
    @Override
    public void execute()
    {
        drawingModel.removeShape(shape);
    }

    /**
     * Undo method. It adds shape to the canvas, (ArrayList inside DrawingModel)
     */
    @Override
    public void unexecute()
    {
        drawingModel.add(shape);
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return ("Removed:" + shape);
    }    
}
