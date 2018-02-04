/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import mvc.model.Circle;
import mvc.model.Hexagon;
import mvc.model.Line;
import mvc.model.Point;
import mvc.model.Rectangle;
import mvc.model.Shape;
import mvc.model.Square;
import mvc.view.CircleDrawer;
import mvc.view.HexagonDrawer;
import mvc.view.LineDrawer;
import mvc.view.PointDrawer;
import mvc.view.RectangleDrawer;
import mvc.view.ShapeDrawer;
import mvc.view.SquareDrawer;

/**
 * Class that creates appropriate Drawer object based on type of Shape
 * @author loshmi
 */
public class ShapeDrawerFactory
{
    /**
     * Method that return ShapeDrawer object
     * @param shape
     * @return 
     */
    public static ShapeDrawer getShapeDrawer (Shape shape)
    {
        if (shape instanceof Point)
        {
            return new PointDrawer ();
        }
        else if (shape instanceof Line)
        {
            return new LineDrawer ();
        }
        else if (shape instanceof Circle)
        {
            return new CircleDrawer ();
        }
        else if (shape instanceof Square)
        {
            return new SquareDrawer ();
        }
        else if (shape instanceof Rectangle)
        {
            return new RectangleDrawer ();
        }
        else if (shape instanceof Hexagon)
        {
            return new HexagonDrawer ();
        }
        
        return null;
    }
}
