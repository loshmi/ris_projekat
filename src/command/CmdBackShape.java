/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.Collections;
import mvc.model.DrawingModel;

/**
 * Class used for Command pattern. It sends selected shape when step back (-1 on z axis).
 * @author loshmi
 */
public class CmdBackShape implements Command
{
    private DrawingModel drawingModel;
    private int index1;

    /**
     * Constructor that takes DrawingModel and index of a shape that will be sent to back
     * @param drawingModel
     * @param index1 
     */
    public CmdBackShape(DrawingModel drawingModel, int index1)
    {
        this.drawingModel = drawingModel;
        this.index1 = index1;
    }
    
    /**
     * Method that is sending shape one step back on z axis
     */
    @Override
    public void execute()
    {
        int index2 = index1 - 1;
                
        if (index2 >= 0)
        {
            Collections.swap(drawingModel.getShapes(), index1, index2);
        }
    }

    /**
     * Undo method. It brings shape one step up on z axis.
     */
    @Override
    public void unexecute()
    {
        execute();
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return "Sent back: " + drawingModel.getShape(index1 - 1);
    }
}
