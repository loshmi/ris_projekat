/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.Collections;
import mvc.model.DrawingModel;

/**
 * Class used for Command pattern. It bring selected shape when step up to the z axis.
 * @author loshmi
 */
public class CmdFrontShape implements Command
{
    private DrawingModel drawingModel;
    private int index1;

    /**
     * Constructor that takes DrawingModel and index of a shape that will be bring one step up
     * @param drawingModel
     * @param index1 
     */
    public CmdFrontShape(DrawingModel drawingModel, int index1)
    {
        this.drawingModel = drawingModel;
        this.index1 = index1;
    }
    
    /**
     * Method that is bringing selected shape one step up to the z axis.
     */
    @Override
    public void execute()
    {
        int index2 = index1 + 1;
                
        if (index2 < drawingModel.getShapes().size())
        {
            Collections.swap(drawingModel.getShapes(), index1, index2);
        }
    }

    /**
     * Undo method. It moves selected shape one step back on z axis.
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
        return "Sent front: " + drawingModel.getShape(index1 + 1);
    }
}
