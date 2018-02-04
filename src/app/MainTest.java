/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import command.*;
import java.awt.Color;
import mvc.model.DrawingModel;
import mvc.model.Point;

/**
 *Class used for testing purposes
 * @author loshmi
 */
public class MainTest
{
    /**
     * main method that runs testing
     * @param args 
     */
    public static void main (String [] args)
    {
        DrawingModel model = new DrawingModel();
        Point point = new Point(10, 10, Color.BLACK);
        CmdAddShape c1 = new CmdAddShape(model, point);
        
        c1.execute();
        System.out.println("Posle dodavanja: " + model.getShape(0));

        c1.unexecute();
        System.out.println("Posle undo: " + model.getShapes());
        c1.execute();
        System.out.println("Posle dodavanja: " + model.getShape(0));
        System.out.println("Svi oblici u listi: " + model.getShapes());
        CmdRemoveShape c2 = new CmdRemoveShape(model, point);
        c2.execute();
        System.out.println("Posle uklanjanja: " + model.getShapes());
        c2.unexecute();
        System.out.println("Posle undo: " + model.getShapes());

        Point point2 = new Point(20, 30, Color.BLUE);
        System.out.println("Svi oblici u listi: " + model.getShapes());
        CmdUpdateShape c3 = new CmdUpdateShape(point, point2);
        c3.execute();
        System.out.println("Posle update: " + model.getShape(0));
        c3.unexecute();
        System.out.println("Posle undo: " + model.getShape(0));
    }
}
