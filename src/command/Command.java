/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 * Interface that is used for implementation of Command pattern.
 * @author loshmi
 */
public interface Command
{
    /**
     * Method that executes command
     */
    public void execute ();
    /**
     * Method used for undo
     */
    public void unexecute ();
}
