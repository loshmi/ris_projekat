package app;

import mvc.controller.DrawingController;
import mvc.model.DrawingModel;
import mvc.view.DrawingView;

/**
 *Class that runs application
 * @author loshmi
 */
public class DrawingApp
{
    /**
     * Main method that runs application
     * @param args 
     */
    public static void main (String []args)
    {
        DrawingView drawingView = new DrawingView ();
        DrawingModel drawingModel = new DrawingModel ();
        DrawingController drawingController = new DrawingController (drawingModel, drawingView);
    }
}
