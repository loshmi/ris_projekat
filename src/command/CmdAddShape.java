/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.*;

/**
 * Class used for Command pattern. It adds shape to the canvas.
 * @author loshmi
 */
public class CmdAddShape implements Command
{
    
    private DrawingModel drawingModel;
    private Shape shape;

    /**
     * Constructor that takes DrawingModel and shape that will be added
     * @param drawingModel
     * @param shape 
     */
    public CmdAddShape(DrawingModel drawingModel, Shape shape)
    {
        this.drawingModel = drawingModel;
        this.shape = shape;
    }

    /**
     * Method that is adding shape to the canvas (ArrayList inside DrawingModel)
     */
    @Override
    public void execute()
    {
        drawingModel.add(shape);
    }

    /**
     * Undo method. It removes shape from canvas, (ArrayList inside DrawingModel)
     */
    @Override
    public void unexecute()
    {
        drawingModel.removeShape(shape);
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return ("Added:" + shape);
    }
}
