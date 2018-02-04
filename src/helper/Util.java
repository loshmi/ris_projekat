/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import mvc.model.*;

/**
 * Class used for various tasks that don't belong to any specific class
 * @author loshmi
 */
public class Util
{
    /**
     * Creates ArrayList of String object based on ArrayList of Shape objects
     * @param shapes
     * @return 
     */
    public static ArrayList<String> convertShapesToStrings (ArrayList<Shape> shapes)
    {
        ArrayList<String> stringList = new ArrayList<> ();
        
        for (Shape shape : shapes)
        {
            stringList.add (shape.toString());
        }
        
        return stringList;
    }
    
    /**
     * Point parser method
     * @param message
     * @return 
     */
    public static Point parsePoint (String message)
    {
        return new Point (getX(message), getY(message), Shape.DEFAULT_BORDER);
    }
    
    public static Line parseLine (String message)
    {
        return new Line (getX(message), getY (message), getX2 (message), getY2 (message), Shape.DEFAULT_BORDER);
    }
    
    public static Circle parseCircle (String message)
    {
        return new Circle (getX(message), getY(message), getRA(message), Shape.DEFAULT_BORDER, Shape.DEFAULT_FILL);
    }
    
    public static Square parseSquare (String message)
    {
        return new Square (getX(message), getY(message), getRA(message), Shape.DEFAULT_BORDER, Shape.DEFAULT_FILL);
    }
    
    public static Rectangle parseRectangle (String message)
    {
        return new Rectangle (getX(message), getY(message), getRecA(message), getRecB (message), Shape.DEFAULT_BORDER, Shape.DEFAULT_FILL);
    }
    
    public static Hexagon parseHexagon (String message)
    {
        return new Hexagon (getX(message), getY(message), getRA(message), Shape.DEFAULT_BORDER, Shape.DEFAULT_FILL);
    }
    
    /**
     * Method that extracts x coordinate from message
     * @param message
     * @return 
     */
    private static int getX (String message)
    {
        int startIndex = message.indexOf("(");
        int endIndex = message.indexOf(",");
        
        String x = message.substring(startIndex + 1, endIndex);
        
        return Integer.parseInt(x);
    }
    
    /**
     * Method that extracts y coordinate from message
     * @param message
     * @return 
     */
    private static int getY (String message)
    {
        int startIndex = message.indexOf(",");
        int endIndex = message.indexOf(")");
        
        String y = message.substring(startIndex + 2, endIndex);
        
        return Integer.parseInt(y);
    }
    
    private static int getX2 (String message)
    {
        int skipIndex = message.indexOf("),");
        
        skipIndex += 2;
        
        int startIndex = message.indexOf("(", skipIndex);
        int endIndex = message.indexOf(",", skipIndex);
        
        String x2 = message.substring(startIndex + 1, endIndex);
        
        return Integer.parseInt(x2);
    }
    
    private static int getY2 (String message)
    {
        int skipIndex = message.indexOf("),");
        
        skipIndex += 2;
        
        int startIndex = message.indexOf(",", skipIndex);
        int endIndex = message.indexOf(")", skipIndex);
        
        String y2 = message.substring(startIndex + 2, endIndex);
        
        return Integer.parseInt(y2);
    }
    
    private static int getRA (String message)
    {
        int startIndex = message.indexOf(")");
        int endIndex = message.indexOf("]");
        
        
        String ra = message.substring(startIndex + 2, endIndex);
        
        return Integer.parseInt(ra);
    }
    
    private static int getRecA (String message)
    {
        int startIndex = message.indexOf("[");
        int endIndex = message.indexOf(",", startIndex);
        
        
        String a = message.substring(startIndex + 1, endIndex);
        
        return Integer.parseInt(a);
    }
    
    private static int getRecB (String message)
    {
        int skipIndex = message.indexOf("[");
        
        int startIndex = message.indexOf(",", skipIndex);
        int endIndex = message.indexOf("]");
        
        String b = message.substring(startIndex + 2, endIndex);
        
        return Integer.parseInt(b);
    }
}
