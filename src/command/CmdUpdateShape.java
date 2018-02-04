/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.DrawingModel;
import mvc.model.Shape;

/**
 * Class used for Command pattern. It updates shape and preserves original state
 * for undo.
 * @author loshmi
 */
public class CmdUpdateShape implements Command
{
    private Shape shapeOriginal;
    private Shape shapeNewState;
    private Shape shapeOldState;

    /**
     * Constructor that takes shape that is going to be modified and a shape 
     * with new state.
     * @param shapeOriginal
     * @param shapeNewState 
     */
    public CmdUpdateShape(Shape shapeOriginal, Shape shapeNewState)
    {
        this.shapeOriginal = shapeOriginal;
        this.shapeNewState = shapeNewState;
    }   
    
    /**
     * Method that preserves shape and updates it to a new state
     */
    @Override
    public void execute()
    {
        shapeOldState = shapeOriginal.cloneShape();
        shapeOriginal.setShape(shapeNewState);
    }

    /**
     * Undo method. It return shape to its original state
     */
    @Override
    public void unexecute()
    {
        shapeOriginal.setShape(shapeOldState);
    }
    
    /**
     * ToString method used for logger
     * @return 
     */
    @Override
    public String toString ()
    {
        return ("Updated:" + shapeOriginal);
    }
}
