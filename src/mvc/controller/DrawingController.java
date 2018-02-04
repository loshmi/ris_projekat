/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.logging.Logger;
import command.CmdAddShape;
import command.CmdBackShape;
import command.CmdFirstShape;
import command.CmdFrontShape;
import command.CmdLastShape;
import command.CmdRemoveShape;
import command.CmdRemoveShapes;
import command.CmdUpdateShape;
import command.Command;
import helper.Util;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import mvc.model.*;
import mvc.view.DrawingView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Controller class in MVC
 * @author loshmi
 */
public class DrawingController
{
    private static final int MODE_SELECT = 0;
    private static final int MODE_DRAW = 1;
    
    private static final int BTN_NONE = 0;
    private static final int BTN_POINT = 1;
    private static final int BTN_LINE = 2;
    private static final int BTN_CIRCLE = 3;
    private static final int BTN_SQUARE = 4;
    private static final int BTN_RECTANGLE = 5;
    private static final int BTN_HEXAGON = 6;
    
    private static final int SELECT_NONE = 0;
    private static final int SELECT_FIRST = 1;
    
    private int mode;
    private int btn_code;
    private int select_step;
    
    private int x;
    private int y;
    private int x1;
    private int y1;
    
    private int r;
    private int a;
    private int b;
     
    private Point firstPoint;
    
    private DrawingModel drawingModel;
    private final DrawingView drawingView;
    public final static Logger LOGGER = Logger.getLogger("logger");
    private ArrayList<String> listEntries;
    private File file;
    private LinkedList<Command> commands;
    private int indexCmd;

    /**
     * Constructor that takes model and view
     * @param drawingModel
     * @param drawingView 
     */
    public DrawingController(DrawingModel drawingModel, DrawingView drawingView)
    {
        this.listEntries = new ArrayList<> ();
        this.drawingModel = drawingModel;
        this.drawingView = drawingView;
        this.drawingView.initView();
        this.commands= new LinkedList<Command> ();
        this.indexCmd = 0;
     
        this.drawingView.addDrawingListener (new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        
                        if (mode == MODE_DRAW)
                        {
                            if (btn_code == BTN_POINT)
                            {
                                x = e.getX();
                                y = e.getY();
                                CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createPoint());
                                doCmd(cmdAddShape);
                            }
                            else if (btn_code == BTN_LINE)
                            {
                                if (select_step == SELECT_NONE)
                                {
                                    x = e.getX();
                                    y = e.getY();
                                    firstPoint = createPoint();
                                    drawingModel.add(firstPoint);
                                    select_step = SELECT_FIRST;
                                }
                                else if (select_step == SELECT_FIRST)
                                {
                                    x1 = e.getX();
                                    y1 = e.getY();
                                    CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createLine());
                                    drawingModel.removeShape(firstPoint);
                                    firstPoint = null;
                                    doCmd(cmdAddShape);
                                    select_step = SELECT_NONE;
                                }
                            }
                            else if (btn_code == BTN_CIRCLE)
                            {
                                if (select_step == SELECT_NONE)
                                {
                                    x = e.getX();
                                    y = e.getY();
                                    firstPoint = createPoint();
                                    drawingModel.add(firstPoint);
                                    select_step = SELECT_FIRST;
                                }
                                else if (select_step == SELECT_FIRST)
                                {
                                    x1 = e.getX();
                                    y1 = e.getY();
                                    r = calculateDistance();
                                    CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createCircle());
                                    drawingModel.removeShape(firstPoint);
                                    firstPoint = null;
                                    doCmd(cmdAddShape);
                                    select_step = SELECT_NONE;
                                }
                            }
                            else if (btn_code == BTN_SQUARE)
                            {
                                if (select_step == SELECT_NONE)
                                {
                                    x = e.getX();
                                    y = e.getY();
                                    firstPoint = createPoint();
                                    drawingModel.add(firstPoint);
                                    select_step = SELECT_FIRST;
                                }
                                else if (select_step == SELECT_FIRST)
                                {
                                    x1 = e.getX();
                                    y1 = e.getY();

                                    if (x1 < x) swapX();
                                    if (y1 < y) swapY();

                                    a = x1 - x;
                                    b = y1 - y;
                                    a = a > b ? a : b;
                                    CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createSquare());
                                    drawingModel.removeShape(firstPoint);
                                    firstPoint = null;
                                    doCmd(cmdAddShape);
                                    select_step = SELECT_NONE;
                                }
                            }
                            else if (btn_code == BTN_RECTANGLE)
                            {
                                if (select_step == SELECT_NONE)
                                {
                                    x = e.getX();
                                    y = e.getY();
                                    firstPoint = createPoint();
                                    drawingModel.add(firstPoint);
                                    select_step = SELECT_FIRST;
                                }
                                else if (select_step == SELECT_FIRST)
                                {
                                    x1 = e.getX();
                                    y1 = e.getY();

                                    if (x1 < x) swapX();
                                    if (y1 < y) swapY();

                                    a = x1 - x;
                                    b = y1 - y;
                                    CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createRectangle());
                                    drawingModel.removeShape(firstPoint);
                                    firstPoint = null;
                                    doCmd(cmdAddShape);
                                    select_step = SELECT_NONE;
                                }
                            }
                            else if (btn_code == BTN_HEXAGON)
                            {
                                if (select_step == SELECT_NONE)
                                {
                                    x = e.getX();
                                    y = e.getY();
                                    firstPoint = createPoint();
                                    drawingModel.add(firstPoint);
                                    select_step = SELECT_FIRST;
                                }
                                else if (select_step == SELECT_FIRST)
                                {
                                    x1 = e.getX();
                                    y1 = e.getY();

                                    a = calculateDistance();
                                    
                                    CmdAddShape cmdAddShape = new CmdAddShape(drawingModel, createHexagon());
                                    drawingModel.removeShape(firstPoint);
                                    firstPoint = null;
                                    doCmd(cmdAddShape);
                                    select_step = SELECT_NONE;
                                }
                            }
                        }
                        else
                        {
                            drawingView.enableCommands();
                            drawingView.setLabels();
                            x = e.getX();
                            y = e.getY();
                            Point selectedPoint = createPoint();
                            
                            selectShapes (selectedPoint);

                            if (drawingModel.getSelectedShapes().size() == 1)
                            {
                                drawingView.getTxtX().setText("" + drawingModel.getSelectedShapes().get(0).getX());
                                drawingView.getTxtY().setText("" + drawingModel.getSelectedShapes().get(0).getY());
                                drawingView.getBtnBorder().setBackground(drawingModel.getSelectedShapes().get(0).getBorder());

                                drawingView.getTxtARX1().setText("");
                                drawingView.getTxtBY1().setText("");
                                drawingView.getBtnFill().setBackground(DrawingView.DEFAULT_FILL);
                                if (drawingModel.getSelectedShapes().get(0) instanceof Point)
                                {
                                    drawingView.getTxtARX1().setEnabled(false);
                                    drawingView.getTxtBY1().setEnabled(false);
                                }      
                                else if (drawingModel.getSelectedShapes().get(0) instanceof Line)
                                {
                                    Line line = (Line) drawingModel.getSelectedShapes().get(0);
                                    drawingView.getLblARX1().setText("X1");
                                    drawingView.getLblBY1().setText ("Y1");
                                    drawingView.getTxtARX1().setText("" + line.getX1());
                                    drawingView.getTxtBY1().setText("" + line.getY1());
                                }
                                else if (drawingModel.getSelectedShapes().get(0) instanceof Circle)
                                {
                                    Circle circle = (Circle) drawingModel.getSelectedShapes().get(0);
                                    drawingView.getLblARX1().setText("r");
                                    drawingView.getTxtARX1().setText("" + circle.getR());
                                    drawingView.getBtnFill().setBackground(circle.getFill());
                                    drawingView.getTxtBY1().setEnabled(false);
                                }
                                else if (drawingModel.getSelectedShapes().get(0) instanceof Square)
                                {
                                    Square square = (Square) drawingModel.getSelectedShapes().get(0);
                                    drawingView.getLblARX1().setText("a");
                                    drawingView.getTxtARX1().setText("" + square.getA());
                                    drawingView.getBtnFill().setBackground(square.getFill());
                                    drawingView.getTxtBY1().setEnabled(false);
                                }
                                else if (drawingModel.getSelectedShapes().get(0) instanceof Rectangle)
                                {
                                    Rectangle rectanglee = (Rectangle) drawingModel.getSelectedShapes().get(0);
                                    drawingView.getLblARX1().setText("a");
                                    drawingView.getLblBY1().setText ("b");
                                    drawingView.getTxtARX1().setText("" + rectanglee.getA());
                                    drawingView.getTxtBY1().setText("" + rectanglee.getB());
                                    drawingView.getBtnFill().setBackground(rectanglee.getFill());
                                }
                                else if (drawingModel.getSelectedShapes().get(0) instanceof Hexagon)
                                {
                                    Hexagon hexagon = (Hexagon) drawingModel.getSelectedShapes().get(0);
                                    drawingView.getLblARX1().setText("a");
                                    drawingView.getTxtARX1().setText("" + hexagon.getA());
                                    drawingView.getBtnFill().setBackground(hexagon.getFill());
                                    drawingView.getTxtBY1().setEnabled(false);
                                }
                            }
                            else if (drawingModel.getSelectedShapes().size() > 1)
                            {                                
                                drawingView.setLabels();
                                drawingView.disableCommands();
                            }
                            else
                            {
                                drawingView.enableCommands();
                            }
                        }

                        drawingView.getDrawingPanel().repaint();
                        drawingView.repaint();                                            
                                        
                        
                    }
                });
        this.drawingView.getDrawingPanel().setShapes(this.drawingModel.getShapes());
        this.drawingView.getDrawingPanel().setSelectedShapes(this.drawingModel.getSelectedShapes());
        
        try
        {
            File dir = new File("log");
            boolean kreiran = dir.mkdir();
            String fileName = dir.getAbsolutePath() + "/log" + System.currentTimeMillis();
            file = new File (fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            FileHandler fileHandler = new FileHandler (fileName);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            
            ActionListenerOpen actLstOpen = new ActionListenerOpen ();
            this.drawingView.addActionListenerOpenLog(actLstOpen);
            
            ActionListenerSave actLstSave = new ActionListenerSave ();
            this.drawingView.addActionListenerSaveLog(actLstSave);
            
            ActionListenerUndo actLstUndo = new ActionListenerUndo ();
            this.drawingView.addActionListenerUndo(actLstUndo);
            
            ActionListenerRedo actLstRedo = new ActionListenerRedo ();
            this.drawingView.addActionListenerRedo(actLstRedo);
            
            ActionListenerOpenModel actLstOpenModel = new ActionListenerOpenModel ();
            this.drawingView.addActionListenerOpenModel(actLstOpenModel);
            
            ActionListenerSaveModel actLstSaveModel = new ActionListenerSaveModel ();
            this.drawingView.addActionListenerSaveModel(actLstSaveModel);
            
            ActionListenerPoint actLstPoint = new ActionListenerPoint();
            this.drawingView.addActionListenerPoint(actLstPoint);
            
            ActionListenerLine actLstLine = new ActionListenerLine();
            this.drawingView.addActionListenerLine(actLstLine);
            
            ActionListenerCircle actLstCircle = new ActionListenerCircle();
            this.drawingView.addActionListenerCircle(actLstCircle);
            
            ActionListenerSquare actLstSquare = new ActionListenerSquare();
            this.drawingView.addActionListenerSquare(actLstSquare);
            
            ActionListenerRectangle actLstRectangle = new ActionListenerRectangle();
            this.drawingView.addActionListenerRectangle(actLstRectangle);
            
            ActionListenerHexagon actLstHexagon = new ActionListenerHexagon();
            this.drawingView.addActionListenerHexagon(actLstHexagon);
            
            ActionListenerBorder actLstBorder = new ActionListenerBorder();
            this.drawingView.addActionListenerBorder(actLstBorder);
            
            ActionListenerFill actLstFill = new ActionListenerFill();
            this.drawingView.addActionListenerFill(actLstFill);
            
            ActionListenerSelectCancel actLstSelectCancel = new ActionListenerSelectCancel();
            this.drawingView.addActionListenerSelect(actLstSelectCancel);
            this.drawingView.addActionListenerCancel(actLstSelectCancel);
            
            ActionListenerDone actLstDone = new ActionListenerDone();
            this.drawingView.addActionListenerDone(actLstDone);
            
            ActionListenerDelete actLstDelete = new ActionListenerDelete();
            this.drawingView.addActionListenerDelete(actLstDelete);
            
            ActionListenerClear actLstClear = new ActionListenerClear();
            this.drawingView.addActionListenerClear(actLstClear);
            
            ActionListenerFront actLstFront = new ActionListenerFront();
            this.drawingView.addActionListenerFront(actLstFront);
            
            ActionListenerBack actLstBack = new ActionListenerBack();
            this.drawingView.addActionListenerBack(actLstBack);
            
            ActionListenerFirst actLstFirst = new ActionListenerFirst();
            this.drawingView.addActionListenerFirst(actLstFirst);
            
            ActionListenerLast actLstLast = new ActionListenerLast();
            this.drawingView.addActionListenerLast(actLstLast);
        }
        catch(Exception ex)
        {
            
        }
    }
    
    /**
     * Method that executes command following Command pattern
     * @param cmd 
     */
    private void doCmd (Command cmd)
    {
        cmd.execute();
        if (commands.size() > indexCmd)
        {
            for (int i = commands.size() - 1; i >= indexCmd; i--)
            {
                commands.remove(i);
            }
        }
        LOGGER.log(Level.INFO, cmd.toString());
        commands.add(cmd);
        updateJList();
        indexCmd++;
        setUndoRedo();
    }

    /**
     * Method that determines if Redo or/and Undo button should be enabled
     */
    private void setUndoRedo ()
    {
        if (indexCmd < commands.size())
        {
            drawingView.setEnbBtnRedo(true);
        }
        else
        {
            drawingView.setEnbBtnRedo(false);
        }
        
        if (indexCmd > 0)
        {
            drawingView.setEnbBtnUndo(true);
        }
        else
        {
            drawingView.setEnbBtnUndo(false);
        }
    }
    
    /**
     * Method that updates JList based on shapes 
     */
    private void updateJList ()
    {
        ArrayList<String> tempList = Util.convertShapesToStrings(drawingModel.getShapes());
        Collections.reverse(tempList);
        drawingView.setListData(tempList.toArray());
    }
    
    /**
     * Helper method used for calculating distance between two points
     * @return 
     */
    private int calculateDistance ()
    {
        double temp = Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y1 - y), 2));
        return (int) temp;
    }
    
    /**
     * Method that creates Point object
     * @return 
     */
    private Point createPoint ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        
        return new Point(x, y, border);
    }
    
    /**
     * Method that creates Line object
     * @return 
     */
    private Line createLine ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        
        return new Line (x, y, x1, y1, border);
    }
    
    /**
     * Method that creates Circle object
     * @return 
     */
    private Circle createCircle ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        Color fill = drawingView.getBtnFill ().getBackground();
        
        return new Circle (x, y, r, border, fill);
    }
    
    /**
     * Method that creates Square object
     * @return 
     */
    private Square createSquare ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        Color fill = drawingView.getBtnFill ().getBackground();
        
        return new Square(x, y, a, border, fill);
    }
    
    /**
     * Method that creates Rectangle object
     * @return 
     */
    private Rectangle createRectangle ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        Color fill = drawingView.getBtnFill ().getBackground();
        
        return new Rectangle(x, y, a, b, border, fill);
    }
    
    /**
     * Method that creates Hexagon object
     * @return 
     */
    private Hexagon createHexagon ()
    {
        Color border = drawingView.getBtnBorder().getBackground();
        Color fill = drawingView.getBtnFill ().getBackground();
        
        return new Hexagon (x, y, a, border, fill);
    }
    
    /**
     * Method that changes values of x and x1 variables
     */
    private void swapX ()
    {
        int temp = x;
        x = x1;
        x1 = temp;
    }
    
    /**
     * Method that changes values of y and y1 variables
     */
    private void swapY ()
    {
        int temp = y;
        y = y1;
        y1 = temp;
    }
    
    /**
     * Method that selects shapes based on click of a mouse
     * @param point 
     */
    private void selectShapes (Point point)
    {
        int selectedIndex = -1;
        
        for (int i = 0; i < drawingModel.getShapes().size(); i++)
        {
            if (drawingModel.getShapes().get(i).containsPoint (point))
            {
                selectedIndex = i;
            }
        }
        
        if (selectedIndex != -1)
        {
            drawingModel.addSelected(drawingModel.getShape(selectedIndex));
        }
    }
    
    /**
     * ActionListener that is used for opening log
     */
    private class ActionListenerOpen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            int rVal = fileChooser.showOpenDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try 
                {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
                    dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();                
                    Document doc = dBuilder.parse(file);


                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("record");

                    for (int temp = 0; temp < nList.getLength(); temp++)
                    {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {

                            Element eElement = (Element) nNode;

                            String message = eElement.getElementsByTagName("message").item(0).getTextContent();
                            
                            Shape tempShape = null;
                            
                            if (message.contains ("Point"))
                            {
                                tempShape = Util.parsePoint(message);
                            }
                            else if (message.contains ("Line"))
                            {
                                tempShape = Util.parseLine(message);
                            }
                            else if (message.contains("Circle"))
                            {
                                tempShape = Util.parseCircle(message);
                            }
                            else if (message.contains("Square"))
                            {
                                tempShape = Util.parseSquare(message);
                            }
                            else if (message.contains("Rectangle"))
                            {
                                tempShape = Util.parseRectangle(message);
                            }
                            else if (message.contains("Hexagon"))
                            {
                                tempShape = Util.parseHexagon(message);
                            }
                            

                            CmdAddShape cmd = new CmdAddShape(drawingModel, tempShape);
                            cmd.execute();
                            DrawingController.LOGGER.log(Level.INFO, cmd.toString());
                        }                    
                    }

                    ArrayList<String> tempList = Util.convertShapesToStrings(drawingModel.getShapes());
                    Collections.reverse(tempList);

                    drawingView.setListData(tempList.toArray());
                }
                catch (Exception ex)
                {
                  ex.printStackTrace();
                }
            }
            else
            {
                System.out.println("File access cancelled by user.");
            }
        }
    }
    
    /**
     * Listener used for saving log
     */
    private class ActionListenerSave implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String newFileName;
            String newDirName;
            JFileChooser fileChooser = new JFileChooser();
            int rVal = fileChooser.showSaveDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                newFileName = fileChooser.getSelectedFile().getName();
                newDirName = fileChooser.getCurrentDirectory().toString();

                String newFileNameFull = newDirName + "/" + newFileName;

                File newFile = new File(newFileNameFull);

                copyFile (newFile);
                closeTag(newFile);
            }
        }

        private void copyFile (File dest)
        {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try
            {
                try {
                    sourceChannel = new FileInputStream(file).getChannel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    destChannel = new FileOutputStream(dest).getChannel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                } catch (IOException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            finally
            {
                try {
                    sourceChannel.close();
                    destChannel.close();
                } catch (IOException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void closeTag (File file)
        {
            try {
                Writer output;
                output = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
                output.append("</log>");
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Listener used for Undo option
     */
    private class ActionListenerUndo implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (indexCmd > 0)
            {
                indexCmd--;
                Command cmd = commands.get(indexCmd);
                cmd.unexecute();
                LOGGER.log(Level.INFO, "UNDO: " + cmd.toString());
                updateJList();
                drawingView.repaint();
                setUndoRedo();
                drawingModel.getSelectedShapes().clear();
            }
        }
    }
    
    /**
     * Listener used for Redo option
     */
    private class ActionListenerRedo implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (commands.size() != indexCmd)
            {
                Command cmd = commands.get(indexCmd);
                cmd.execute();
                LOGGER.log(Level.INFO, "REDO: " + cmd.toString());
                updateJList();
                indexCmd++;
                drawingView.repaint();
                setUndoRedo();
                drawingModel.getSelectedShapes().clear();
            }
        }
    }
    
    /**
     * Listener used for opening model
     */
    private class ActionListenerOpenModel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String newFileName;
            String newDirName;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileFilter()
                                                {
                                                    public String getDescription()
                                                    {
                                                        return "Seriazable files (*.ser)";
                                                    }
 
                                                    public boolean accept(File f)
                                                    {
                                                        if (f.isDirectory())
                                                        {
                                                            return true;
                                                        }
                                                        else
                                                        {
                                                            return f.getName().toLowerCase().endsWith(".ser");
                                                        }
                                                    }
                                                });
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                newFileName = fileChooser.getSelectedFile().getName();
                newDirName = fileChooser.getCurrentDirectory().toString();

                String newFileNameFull = newDirName + "/" + newFileName;

                File newFile = new File(newFileNameFull);

                try
                {
                    FileInputStream fileInputStream = new FileInputStream(newFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    DrawingModel model = (DrawingModel) objectInputStream.readObject();
                    drawingModel.copyShapes(model.getShapes());
                    drawingView.getDrawingPanel().setShapes(drawingModel.getShapes());
                    drawingView.getDrawingPanel().setSelectedShapes(drawingModel.getSelectedShapes());                    
                    ArrayList<String> tempList = Util.convertShapesToStrings(drawingModel.getShapes());
                    Collections.reverse(tempList);
                    drawingView.setListData(tempList.toArray());
                    drawingView.repaint();
                    
                    objectInputStream.close();
                    fileInputStream.close();
                }
                catch (FileNotFoundException fnfException)
                {
                    fnfException.printStackTrace();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch (ClassNotFoundException cnfException)
                {
                    cnfException.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Listener used for saving model
     */
    private class ActionListenerSaveModel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileFilter()
                                                {
                                                    public String getDescription()
                                                    {
                                                        return "Seriazable files (*.ser)";
                                                    }
 
                                                    public boolean accept(File f)
                                                    {
                                                        if (f.isDirectory())
                                                        {
                                                            return true;
                                                        }
                                                        else
                                                        {
                                                            return f.getName().toLowerCase().endsWith(".ser");
                                                        }
                                                    }
                                                });
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showSaveDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();

                try
                {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream outObject = new ObjectOutputStream(fileOut);
                    outObject.writeObject(drawingModel);
                    outObject.close();
                    fileOut.close();
                    String fileName = file.getAbsolutePath();
                    if (!fileName.toLowerCase().endsWith(".ser"))
                    {
                        fileName += ".ser";
                        File newFileName = new File (fileName);
                        file.renameTo(newFileName);
                    }
                }
                catch (FileNotFoundException fnfException)
                {
                    fnfException.printStackTrace();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Listener used for drawing new Point
     */
    private class ActionListenerPoint implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_POINT;
            drawingModel.getSelectedShapes().clear();
            if (firstPoint != null)
            {
                drawingModel.removeShape(firstPoint);
                drawingView.getDrawingPanel().repaint();
                drawingView.repaint();
            }
        }        
    }
    
    /**
     * Listener used for drawing new Line
     */
    private class ActionListenerLine implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_LINE;
            drawingModel.getSelectedShapes().clear();
        }        
    }
    
    /**
     * Listener used for drawing new Circle
     */
    private class ActionListenerCircle implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_CIRCLE;
            drawingModel.getSelectedShapes().clear();
        }        
    }
    
    /**
     * Listener used for drawing new Square
     */
    private class ActionListenerSquare implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_SQUARE;
            drawingModel.getSelectedShapes().clear();
        }        
    }
    
    /**
     * Listener used for drawing new Rectangle
     */
    private class ActionListenerRectangle implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_RECTANGLE;
            drawingModel.getSelectedShapes().clear();
        }        
    }
    
    /**
     * Listener used for drawing new Hexagon
     */
    private class ActionListenerHexagon implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.disableTxtFields();
            drawingView.setLabels();
            mode = MODE_DRAW;
            btn_code = BTN_HEXAGON;
            drawingModel.getSelectedShapes().clear();
        }        
    }
    
    /**
     * Listener used for changing border color
     */
    private class ActionListenerBorder implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Color initialcolor = drawingView.getBtnBorder().getBackground();
            Color color = JColorChooser.showDialog(drawingView, "Izaberite boju", initialcolor);    
            drawingView.getBtnBorder().setBackground(color);   
        }        
    }
    
    /**
     * Listener used for changing fill color
     */
    private class ActionListenerFill implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Color initialcolor = drawingView.getBtnFill().getBackground();
            Color color = JColorChooser.showDialog(drawingView, "Izaberite boju", initialcolor);    
            drawingView.getBtnFill().setBackground(color);   
        }        
    }
    
    /**
     * Listener used for selecting object
     */
    private class ActionListenerSelectCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            drawingView.enableCommands();
            drawingView.setLabels();
            mode = MODE_SELECT;
            btn_code = BTN_NONE;
            select_step = SELECT_NONE;
            if (firstPoint != null)
            {
                drawingModel.removeShape(firstPoint);
                drawingView.getDrawingPanel().repaint();
                drawingView.repaint();
            }
            
            drawingModel.getSelectedShapes().clear();
        }
    }
    
    /**
     * Listener used for accepting changes on Shape
     */
    private class ActionListenerDone implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() == 1)
            {
                String stringX = drawingView.getTxtX().getText();
                if (!stringX.matches("[0-9]+"))
                {
                    stringX = "1";
                }
                
                String stringY = drawingView.getTxtY().getText();
                if (!stringY.matches("[0-9]+"))
                {
                    stringY = "1";
                }
                
                if (drawingModel.getSelectedShape(0) instanceof Point)
                {
                    Point newPoint = new Point (Integer.parseInt(stringX), Integer.parseInt(stringY), drawingView.getBtnBorder().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newPoint);
                    doCmd(command);
                }
                else if (drawingModel.getSelectedShape(0) instanceof Line)
                {
                    String stringX1 = drawingView.getTxtARX1().getText();
                    if (!stringX1.matches("[0-9]+"))
                    {
                        stringX1 = "1";
                    }
                    String stringY1 = drawingView.getTxtBY1().getText();
                    if (!stringY1.matches("[0-9]+"))
                    {
                        stringY1 = "1";
                    }
                    
                    Line newLine = new Line (Integer.parseInt(stringX), Integer.parseInt(stringY), Integer.parseInt(stringX1), Integer.parseInt(stringY1), drawingView.getBtnBorder().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newLine);
                    doCmd(command);
                }
                else if (drawingModel.getSelectedShape(0) instanceof Circle)
                {
                    String stringR = drawingView.getTxtARX1().getText();
                    if (!stringR.matches("[0-9]+"))
                    {
                        stringR = "1";
                    }
                    
                    Circle newCircle = new Circle (Integer.parseInt(stringX), Integer.parseInt(stringY), Integer.parseInt(stringR), drawingView.getBtnBorder().getBackground(), drawingView.getBtnFill().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newCircle);
                    doCmd(command);
                }
                else if (drawingModel.getSelectedShape(0) instanceof Square)
                {
                    String stringA = drawingView.getTxtARX1().getText();
                    if (!stringA.matches("[0-9]+"))
                    {
                        stringA = "1";
                    }
                    
                    Square newSquare = new Square (Integer.parseInt(stringX), Integer.parseInt(stringY), Integer.parseInt(stringA), drawingView.getBtnBorder().getBackground(), drawingView.getBtnFill().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newSquare);
                    doCmd(command);
                }
                else if (drawingModel.getSelectedShape(0) instanceof Rectangle)
                {
                    String stringA = drawingView.getTxtARX1().getText();
                    if (!stringA.matches("[0-9]+"))
                    {
                        stringA = "1";
                    }
                    String stringB = drawingView.getTxtBY1().getText();
                    if (!stringB.matches("[0-9]+"))
                    {
                        stringB = "1";
                    }
                    
                    Rectangle newRectangle = new Rectangle (Integer.parseInt(stringX), Integer.parseInt(stringY), Integer.parseInt(stringA), Integer.parseInt(stringB), drawingView.getBtnBorder().getBackground(), drawingView.getBtnFill().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newRectangle);
                    doCmd(command);
                }
                else if (drawingModel.getSelectedShape(0) instanceof Hexagon)
                {
                    String stringA = drawingView.getTxtARX1().getText();
                    if (!stringA.matches("[0-9]+"))
                    {
                        stringA = "1";
                    }
                    
                    Hexagon newHexagon = new Hexagon (Integer.parseInt(stringX), Integer.parseInt(stringY), Integer.parseInt(stringA), drawingView.getBtnBorder().getBackground(), drawingView.getBtnFill().getBackground());
                    Command command = new CmdUpdateShape(drawingModel.getSelectedShape(0), newHexagon);
                    doCmd(command);
                }
            }
            
            drawingView.getDrawingPanel().repaint();
            drawingView.repaint();
            drawingView.setEnbBtnUndo(true);
        }
    }
    
    /**
     * Listener used for deleting shapes (single or multiple)
     */
    private class ActionListenerDelete implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() > 0)
            {
                int result = JOptionPane.showConfirmDialog(drawingView, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);  
                if (result == JOptionPane.YES_OPTION)
                {
                    if (drawingModel.getSelectedShapes().size () == 1)
                    {
                        Command command = new CmdRemoveShape(drawingModel, drawingModel.getSelectedShape(0));
                        doCmd(command);
                    }
                    else
                    {
                        Command command = new CmdRemoveShapes (drawingModel, drawingModel.getSelectedShapes());
                        doCmd(command);
                    }
                    
                    
                    drawingModel.getSelectedShapes().clear();
                    drawingView.setLabels();
                    drawingView.enableCommands();
                    drawingView.setEnbBtnUndo(true);
                    drawingView.getDrawingPanel().repaint();
                    drawingView.repaint();
                }  
            }
        }
    }
    
    /**
     * Listener used for deleting all shapes
     */
    private class ActionListenerClear implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int result = JOptionPane.showConfirmDialog(drawingView, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION); 
            if(result == JOptionPane.YES_OPTION)
            {
                
                drawingModel.selectAll();
                Command command = new CmdRemoveShapes (drawingModel, drawingModel.getSelectedShapes());
                doCmd(command);
                drawingView.setLabels();
                drawingView.enableCommands();
                drawingModel.getSelectedShapes().clear();
                drawingView.getDrawingPanel().repaint();
                drawingView.repaint();
            }
        }
    }
    
    /**
     * Listener used for bringing selected shape one step to the front
     */
    private class ActionListenerFront implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() == 1)
            {
                int index1 = drawingModel.getShapes().indexOf(drawingModel.getSelectedShape(0));
                
                Command command = new CmdFrontShape(drawingModel, index1);
                doCmd(command);
                
                drawingView.getDrawingPanel().repaint();
                drawingView.repaint();
            }
        }
    }
    
    /**
     * Listener used for sending selected shape one step to the back
     */
    private class ActionListenerBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() == 1)
            {
                int index1 = drawingModel.getShapes().indexOf(drawingModel.getSelectedShape(0));
                
                Command command = new CmdBackShape(drawingModel, index1);
                doCmd(command);
                
                drawingView.getDrawingPanel().repaint();
                drawingView.repaint();
            }
        }
    }
    
    /**
     * Listener used for bringing selected shape to the top of z axis
     */
    private class ActionListenerFirst implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() == 1)
            {
                if (drawingModel.getShapes().size() > 1)
                {
                    int index1 = drawingModel.getShapes().indexOf(drawingModel.getSelectedShape(0));
                    
                    Command command = new CmdFirstShape(drawingModel, index1);
                    doCmd(command);

                    drawingView.getDrawingPanel().repaint();
                    drawingView.repaint();
                }
            }
        }
    }
    
    /**
     * Listener used for bringing selected shape to the bottom of z axis
     */
    private class ActionListenerLast implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (drawingModel.getSelectedShapes().size() == 1)
            {
                if (drawingModel.getShapes().size() > 1)
                {
                    int index1 = drawingModel.getShapes().indexOf(drawingModel.getSelectedShape(0));
                    Command command = new CmdLastShape (drawingModel, index1);
                    doCmd(command);
                    
                    drawingView.getDrawingPanel().repaint();
                    drawingView.repaint();
                }
            }
        }
    }
}
