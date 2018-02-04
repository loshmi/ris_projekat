/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * View in MVC pattern
 * @author loshmi
 */
public class DrawingView extends JFrame
{

    private final DrawingPanel drawingPanel;
    
    private final JPanel commandPanel;
    
    private final JPanel line1Panel;
    private final JPanel line2Panel;
    private final JPanel line3Panel;
    private final JPanel line4Panel;
    
    private final JPanel jListPanel;
    
    private JButton btnOpenLog;
    private JButton btnSaveLog;
    private JButton btnUndo;
    private JButton btnRedo;
    private JButton btnOpenModel;
    private JButton btnSaveModel;
    
    private JButton btnPoint;
    private JButton btnLine;
    private JButton btnCircle;
    private JButton btnSquare;
    private JButton btnRectangle;
    private JButton btnHexagon;
    
    private JLabel lblX;
    private JLabel lblY;
    private JLabel lblARX1;
    private JLabel lblBY1;
    private JLabel lblBorder;
    private JLabel lblFill;
    
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtARX1;
    private JTextField txtBY1;
    
    private JButton btnBorder;
    private JButton btnFill;
    
    private JButton btnCancel;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnDone;
    private JButton btnSelect;
    private JButton btnFront;
    private JButton btnBack;
    private JButton btnFirst;
    private JButton btnLast;
        
    JList list;
    
    public static final Color DEFAULT_BORDER = Color.BLACK;
    public static final Color DEFAULT_FILL = Color.RED;
    
    /**
     * Constructor
     */
    public DrawingView()
    {
        drawingPanel = new DrawingPanel ();
        
        commandPanel = new JPanel ();
        
        line1Panel = new JPanel();
        line2Panel = new JPanel();
        line3Panel = new JPanel();
        line4Panel = new JPanel();
        jListPanel = new JPanel ();
    }

    public JList getList() {
        return list;
    }
    
    /**
     * Method that initialize drawingPanel
     */
    private void initDrawingPanel ()
    {
        this.add (drawingPanel, BorderLayout.CENTER);
    }
    
    /**
     * Method that initialize panel with command buttons
     */
    private void initCommandPanel ()
    {
        commandPanel.setLayout(new GridLayout (4, 1, 0, 10));
        initLine1();
        initLine2();
        initLine3();
        initLine4();
        this.add (commandPanel, BorderLayout.NORTH);
    }
    
    /**
     * Method that initialize first line of command palette
     */
    private void initLine1 ()
    {
        line1Panel.setLayout(new GridLayout (1, 6, 10, 0));
        
        btnOpenModel = new JButton ("Open model");
        btnSaveModel = new JButton ("Save model");
        btnUndo = new JButton ("Undo");
        btnUndo.setEnabled(false);
        btnRedo = new JButton ("Redo");
        btnRedo.setEnabled(false);
        btnOpenLog = new JButton ("Open Log");
        btnSaveLog = new JButton ("Save Log");
        
        line1Panel.add(btnOpenModel);
        line1Panel.add(btnSaveModel);
        line1Panel.add(btnUndo);
        line1Panel.add(btnRedo);
        line1Panel.add(btnOpenLog);
        line1Panel.add(btnSaveLog);         
        
        commandPanel.add (line1Panel);
    }
    
    /**
     * Method that initialize second line of command palette
     */
    private void initLine2 ()
    {
        line2Panel.setLayout(new GridLayout (1, 6, 10, 0));
        btnPoint = new JButton ("Point");
        btnLine = new JButton ("Line");
        btnCircle = new JButton ("Circle");
        btnSquare = new JButton ("Square");
        btnRectangle = new JButton ("Rectangle");
        btnHexagon = new JButton ("Hexagon");
        
        line2Panel.add(btnPoint);
        line2Panel.add(btnLine);
        line2Panel.add(btnCircle);
        line2Panel.add(btnSquare);
        line2Panel.add(btnRectangle);
        line2Panel.add(btnHexagon);
        
        commandPanel.add (line2Panel);
    }
    
    /**
     * Method that initialize third line of command palette
     */
    private void initLine3 ()
    {
        line3Panel.setLayout(new GridLayout (1, 12, 10, 0));
        lblX = new JLabel ("X", SwingConstants.RIGHT);
        lblY = new JLabel ("Y", SwingConstants.RIGHT);
        lblARX1 = new JLabel ("a/r/X1", SwingConstants.RIGHT);
        lblBY1 = new JLabel ("b/Y1", SwingConstants.RIGHT);
        lblBorder = new JLabel("Border", SwingConstants.RIGHT);
        lblFill = new JLabel("Fill", SwingConstants.RIGHT);
        
        txtX = new JTextField ();
        txtY = new JTextField ();
        txtARX1 = new JTextField ();
        txtBY1 = new JTextField ();
        
        btnBorder = new JButton();
        btnBorder.setBackground(DEFAULT_BORDER);
        btnFill = new JButton();
        btnFill.setBackground(DEFAULT_FILL);
        
        line3Panel.add(lblX);
        line3Panel.add(txtX);
        line3Panel.add(lblY);
        line3Panel.add(txtY);
        line3Panel.add(lblARX1);
        line3Panel.add(txtARX1);
        line3Panel.add(lblBY1);
        line3Panel.add(txtBY1);
        line3Panel.add(lblBorder);
        line3Panel.add(btnBorder);
        line3Panel.add(lblFill);
        line3Panel.add(btnFill);
        
        commandPanel.add (line3Panel);
    }
    
    /**
     * Method that initialize fourth line of command palette
     */
    private void initLine4 ()
    {
        line4Panel.setLayout(new GridLayout (1, 9, 10, 0));
        btnSelect = new JButton("Select");
        btnDone = new JButton("Done");
        btnCancel = new JButton("Cancel");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnFront = new JButton ("Front");
        btnBack = new JButton ("Back");
        btnFirst = new JButton ("First");
        btnLast = new JButton ("Last");
        
        line4Panel.add(btnSelect);
        line4Panel.add(btnDone);
        line4Panel.add(btnCancel);
        line4Panel.add(btnDelete);
        line4Panel.add(btnClear);
        line4Panel.add(btnFront);
        line4Panel.add(btnBack);
        line4Panel.add(btnFirst);
        line4Panel.add(btnLast);
        
        commandPanel.add (line4Panel);
    }
    
    /**
     * Method that initialize JList
     */
    private void initJlist ()
    {
        list = new JList();
        JScrollPane scrollPane = new JScrollPane (list);
        jListPanel.add(scrollPane);
        this.add (jListPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Method that initialize view
     */
    public void initView ()
    {
        initCommandPanel();
        initDrawingPanel();
        initJlist();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(950, 800);
        this.setTitle("Crtanje");
        setVisible(true);
    }

    public DrawingPanel getDrawingPanel()
    {
        return drawingPanel;
    }

    public JButton getBtnBorder() {
        return btnBorder;
    }

    public JButton getBtnFill() {
        return btnFill;
    }

    public JTextField getTxtX() {
        return txtX;
    }

    public JTextField getTxtY() {
        return txtY;
    }

    public JTextField getTxtARX1() {
        return txtARX1;
    }

    public JTextField getTxtBY1() {
        return txtBY1;
    }

    public JLabel getLblARX1() {
        return lblARX1;
    }

    public JLabel getLblBY1() {
        return lblBY1;
    }
    
    public void setLabels ()
    {
        lblARX1.setText("a/r/X1");
        lblBY1.setText("b/Y1");
    }
    
    /**
     * Method that disables command that could not be used in given context
     */
    public void disableCommands ()
    {
        disableTxtFields();
        btnBorder.setBackground(DEFAULT_BORDER);
        btnFill.setBackground(DEFAULT_FILL);
        btnBorder.setEnabled(false);
        btnFill.setEnabled(false);
    }
    
    /**
     * Method that enables disabled commands
     */
    public void enableCommands ()
    {
        enableTxtFields();
        btnBorder.setBackground(DEFAULT_BORDER);
        btnFill.setBackground(DEFAULT_FILL);
        btnBorder.setEnabled(true);
        btnFill.setEnabled(true);
    }
    
    public void disableTxtFields ()
    {
        txtX.setText("");
        txtY.setText("");
        txtARX1.setText("");
        txtBY1.setText("");
        txtX.setEnabled(false);
        txtY.setEnabled(false);
        txtARX1.setEnabled(false);
        txtBY1.setEnabled(false);
    }
    
    public void enableTxtFields ()
    {
        txtX.setText("");
        txtY.setText("");
        txtARX1.setText("");
        txtBY1.setText("");
        txtX.setEnabled(true);
        txtY.setEnabled(true);
        txtARX1.setEnabled(true);
        txtBY1.setEnabled(true);
    }

    public void addDrawingListener (MouseListener listenForMouseClick)
    {
        drawingPanel.addMouseListener(listenForMouseClick);
    }
    
    public void addActionListenerOpenModel (ActionListener al)
    {
        btnOpenModel.addActionListener(al);
    }
    
    public void addActionListenerSaveModel (ActionListener al)
    {
        btnSaveModel.addActionListener(al);
    }
    
    public void addActionListenerUndo (ActionListener al)
    {
        btnUndo.addActionListener(al);
    }
    
    public void addActionListenerRedo (ActionListener al)
    {
        btnRedo.addActionListener(al);
    }
    
    public void addActionListenerOpenLog (ActionListener al)
    {
        btnOpenLog.addActionListener(al);
    }
    
    public void addActionListenerSaveLog (ActionListener al)
    {
        btnSaveLog.addActionListener(al);
    }
    
    public void addActionListenerPoint (ActionListener al)
    {
        btnPoint.addActionListener(al);
    }
    
    public void addActionListenerLine (ActionListener al)
    {
        btnLine.addActionListener(al);
    }
    
    public void addActionListenerCircle (ActionListener al)
    {
        btnCircle.addActionListener(al);
    }
    
    public void addActionListenerSquare (ActionListener al)
    {
        btnSquare.addActionListener(al);
    }
    
    public void addActionListenerRectangle (ActionListener al)
    {
        btnRectangle.addActionListener(al);
    }
    
    public void addActionListenerHexagon (ActionListener al)
    {
        btnHexagon.addActionListener(al);
    }
    
    public void addActionListenerBorder (ActionListener al)
    {
        btnBorder.addActionListener(al);
    }
    
    public void addActionListenerFill (ActionListener al)
    {
        btnFill.addActionListener(al);
    }
    
    public void addActionListenerSelect (ActionListener al)
    {
        btnSelect.addActionListener(al);
    }
    
    public void addActionListenerDone (ActionListener al)
    {
        btnDone.addActionListener(al);
    }
    
    public void addActionListenerCancel (ActionListener al)
    {
        btnCancel.addActionListener(al);
    }
    
    public void addActionListenerDelete (ActionListener al)
    {
        btnDelete.addActionListener(al);
    }
    
    public void addActionListenerClear (ActionListener al)
    {
        btnClear.addActionListener(al);
    }
    
    public void addActionListenerFront (ActionListener al)
    {
        btnFront.addActionListener(al);
    }
    
    public void addActionListenerBack (ActionListener al)
    {
        btnBack.addActionListener(al);
    }
    
    public void addActionListenerFirst (ActionListener al)
    {
        btnFirst.addActionListener(al);
    }
    
    public void addActionListenerLast (ActionListener al)
    {
        btnLast.addActionListener(al);
    }
    
    public void setListData (Object [] data)
    {
        list.setListData(data);
    }
    
    public void setEnbBtnUndo (boolean state)
    {
        btnUndo.setEnabled(state);
    }
    
    public void setEnbBtnRedo (boolean state)
    {
        btnRedo.setEnabled(state);
    }
}
