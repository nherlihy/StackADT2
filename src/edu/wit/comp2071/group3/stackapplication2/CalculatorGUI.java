package edu.wit.comp2071.group3.stackapplication2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class CalculatorGUI extends JFrame implements ActionListener {
	
	CalculatorOG og = new CalculatorOG();
	private VectorStack<String> queue;
    
    JPanel[] row = new JPanel[7]; // Initialize rows
    JButton[] button = new JButton[20]; // Initialize buttons
    String[] buttonString = {"C", "<", "Q", "/",
                             "7", "8", "9", "*",
                             "4", "5", "6", "-",
                             "1", "2", "3", "+",
                             "0", "(", ")", "="};
    
    Dimension regularDimension = new Dimension(150, 25); // Set dimensions for inputField and displayField
    Dimension buttonDimension = new Dimension(40, 40); // Set dimensions for button
    
    String sInput = ""; // Initialize sInput 
    String sDisplay = ""; // Initialize sDisplay
    char operation = ' '; // Initialize operation
    
    JTextField inputField = new JTextField(); // Create new textfield for input
    JTextField displayField = new JTextField(); // Create new textfield for display
    
    Font font = new Font("Calibri", Font.BOLD, 14); // Font type, weight, size for input, buttons, output
    
    CalculatorGUI() {
        super("Calculator OG"); // Title
        queue = new VectorStack<String>();
        setDesign(); // Set Nimbus LookAndFeel
        setSize(200, 300); // Set Size of Calculator
        setResizable(false); // No Resizing 
        GridLayout grid = new GridLayout(7,4); // Initialize grid layout 7 rows, 4 columns
        setLayout(grid); // Set layout
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, -2, -2);
        
        // Loop to set each row
        for(int i = 0; i < 7; i++)
        {
            row[i] = new JPanel();
        }
        
        // Set Layout for inputField, and displayField
        row[0].setLayout(f1);
        row[1].setLayout(f1);
        
        // Set Layout(f2) to rows 2 - 7
        for(int i = 2; i < 7; i++)
        {
            row[i].setLayout(f2);
        }
        
        // Set Text, Font, Size, and add ActionListener for all buttons
        for(int i = 0; i < 20; i++) 
        {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);
            button[i].setPreferredSize(buttonDimension);
        }
        
        // Set Parameters for inputField
        inputField.setFont(font);
        inputField.setText("0");
        inputField.setEditable(false);
        inputField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setPreferredSize(regularDimension);
        
        // Set Parameter for displayField
        displayField.setFont(font);
        displayField.setEditable(false);
        displayField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setPreferredSize(regularDimension);
        
        // Add inputField row
        row[0].add(inputField);
        add(row[0]);
        
        // Add displayField row
        row[1].add(displayField);
        add(row[1]);
        
        // Add Row 1 of buttons C through /
        for(int i = 0; i < 4; i++)
        {
            row[2].add(button[i]);
            add(row[2]);
        }
        
        // Add Row 2 of buttons 7 through *
        for(int i = 4; i < 8; i++)
        {
            row[3].add(button[i]);
            add(row[3]);
        }
        
        // Add Row 3 of buttons 4 through -
        for(int i = 8; i < 12; i++)
        {
            row[4].add(button[i]);
            add(row[4]);
        }
        
        // Add Row 4 of buttons 1 through +
        for(int i = 12; i < 16; i++)
        {
            row[5].add(button[i]);
            add(row[5]);
        }
        
        // Add Row 5 of buttons 0 through =
        for(int i = 16; i < 20; i++)
        {
        	row[6].add(button[i]);
        	add(row[6]);
        }
        
        // Set Background and Foreground Colors for buttons
        for(int i = 0; i < 20; i++)
        	button[i].setBackground(Color.black);
        
        for(int i = 0; i < 4; i++)
        	button[i].setForeground(Color.white);
        
        for(int i = 4; i < 7; i++)
        	button[i].setForeground(Color.red);
        
        for(int i = 8; i < 11; i++)
        	button[i].setForeground(Color.red);
        
        for(int i = 12; i < 15; i++)
        	button[i].setForeground(Color.red);
        
        for(int i = 16; i < 19; i++)
        	button[i].setForeground(Color.red);
        
        button[7].setForeground(Color.white);
        button[11].setForeground(Color.white);
        button[15].setForeground(Color.white);
        button[19].setForeground(Color.white);
        
        
        setVisible(true); 
    }
    
    // Design for LookAndFeel
    public final void setDesign() 
    {
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch(Exception e) {}
    }
    
    // Action Events for All Buttons 
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getActionCommand().equals("C"))
        {
			queue.clear();
			inputField.setText("");
        }
        else if(ae.getActionCommand().equals("<"))
        {
            //backspace function
        	if(operation == '=')
			{
				sDisplay = "<";
				sInput = "<";
				inputField.setText(sInput);
				og.addEntry("<");
			}
			else 
			{
				sDisplay = sDisplay + "<";
				sInput += "<";
				inputField.setText(sInput);
				og.addEntry("<");
			}
        }
        else if(ae.getActionCommand().equals("Q"))
        {
        	System.exit(0);
        }
        if(ae.getActionCommand().equals("/")) 
        {
        	if(operation == '=')
			{
				sDisplay = "/";
				sInput = "/";
				inputField.setText(sInput);
				og.addEntry("/");
			}
			else 
			{
				sDisplay = sDisplay + "/";
				sInput += "/";
				inputField.setText(sInput);
				og.addEntry("/");
			}
        }
        else if(ae.getActionCommand().equals("7"))
        {
        	// 7
        	if(operation == '=')
			{
				sDisplay = "7";
				sInput = "7";
				inputField.setText(sInput);
				og.addEntry("7");
			}
			else 
			{
				sDisplay = sDisplay + "7";
				sInput += "7";
				inputField.setText(sInput);
				og.addEntry("7");
			}
        }
        else if(ae.getActionCommand().equals("8"))
        {
        	// 8
        	if(operation == '=')
			{
				sDisplay = "8";
				sInput = "8";
				inputField.setText(sInput);
				og.addEntry("8");
			}
			else 
			{
				sDisplay = sDisplay + "8";
				sInput += "8";
				inputField.setText(sInput);
				og.addEntry("8");
			}
        }
        else if(ae.getActionCommand().equals("9"))
        {
        	// 9
        	if(operation == '=')
			{
				sDisplay = "9";
				sInput = "9";
				inputField.setText(sInput);
				og.addEntry("9");
			}
			else 
			{
				sDisplay = sDisplay + "9";
				sInput += "9";
				inputField.setText(sInput);
				og.addEntry("9");
			}
        }
        else if(ae.getActionCommand().equals("*"))
        {
        	//multiply function
        	if(operation == '=')
			{
				sDisplay = "*";
				sInput = "*";
				inputField.setText(sInput);
				og.addEntry("*");
			}
			else 
			{
				sDisplay = sDisplay + "*";
				sInput += "*";
				inputField.setText(sInput);
				og.addEntry("*");
			}
        }
        else if(ae.getActionCommand().equals("4"))
        {
        	// 4
        	if(operation == '=')
			{
				sDisplay = "4";
				sInput = "4";
				inputField.setText(sInput);
				og.addEntry("4");
			}
			else 
			{
				sDisplay = sDisplay + "4";
				sInput += "4";
				inputField.setText(sInput);
				og.addEntry("4");
			}
        }
        else if(ae.getActionCommand().equals("5"))
        {
        	// 5
        	if(operation == '=')
			{
				sDisplay = "5";
				sInput = "5";
				inputField.setText(sInput);
				og.addEntry("5");
			}
			else 
			{
				sDisplay = sDisplay + "5";
				sInput += "5";
				inputField.setText(sInput);
				og.addEntry("5");
			}
        }
        else if(ae.getActionCommand().equals("6"))
        {
        	// 6
        	if(operation == '=')
			{
				sDisplay = "6";
				sInput = "6";
				inputField.setText(sInput);
				og.addEntry("6");
			}
			else 
			{
				sDisplay = sDisplay + "6";
				sInput += "6";
				inputField.setText(sInput);
				og.addEntry("6");
			}
        }
        else if(ae.getActionCommand().equals("-"))
        {
        	//subtract function
        	if(operation == '=')
			{
				sDisplay = "-";
				sInput = "-";
				inputField.setText(sInput);
				og.addEntry("-");
			}
			else 
			{
				sDisplay = sDisplay + "-";
				sInput += "-";
				inputField.setText(sInput);
				og.addEntry("-");
			}
        }
        else if(ae.getActionCommand().equals("1"))
        {
        	// 1
        	if(operation == '=')
			{
				sDisplay = "1";
				sInput = "1";
				inputField.setText(sInput);
				og.addEntry("1");
			}
			else 
			{
				sDisplay = sDisplay + "1";
				sInput += "1";
				inputField.setText(sInput);
				og.addEntry("1");
			}
        }
        else if(ae.getActionCommand().equals("2"))
        {
        	// 2
        	if(operation == '=')
			{
				sDisplay = "2";
				sInput = "2";
				inputField.setText(sInput);
				og.addEntry("2");
			}
			else 
			{
				sDisplay = sDisplay + "2";
				sInput += "2";
				inputField.setText(sInput);
				og.addEntry("2");
			}
        }
        else if(ae.getActionCommand().equals("3"))
        {
        	// 3
        	if(operation == '=')
			{
				sDisplay = "3";
				sInput = "3";
				inputField.setText(sInput);
				og.addEntry("3");
			}
			else 
			{
				sDisplay = sDisplay + "3";
				sInput += "3";
				inputField.setText(sInput);
				og.addEntry("3");
			}
        }
        else if(ae.getActionCommand().equals("+"))
        {
        	//add function
        	if(operation == '=')
			{
				sDisplay = "+";
				sInput = "+";
				inputField.setText(sInput);
				og.addEntry("+");
			}
			else 
			{
				sDisplay = sDisplay + "+";
				sInput += "+";
				inputField.setText(sInput);
				og.addEntry("+");
			}
        }
        else if(ae.getActionCommand().equals("0"))
        {
        	// 0
        	if(operation == '=')
			{
				sDisplay = "0";
				sInput = "0";
				inputField.setText(sInput);
				og.addEntry("0");
			}
			else 
			{
				sDisplay = sDisplay + "0";
				sInput += "0";
				inputField.setText(sInput);
				og.addEntry("0");
			}
        }
        else if(ae.getActionCommand().equals("("))
        {
        	// (
        	if(operation == '=')
			{
				sDisplay = "(";
				sInput = "(";
				inputField.setText(sInput);
				og.addEntry("(");
			}
			else 
			{
				sDisplay = sDisplay + "(";
				sInput += "(";
				inputField.setText(sInput);
				og.addEntry("(");
			}
        }
        else if(ae.getActionCommand().equals(")"))
        {
        	// )
        	if(operation == '=')
			{
				sDisplay = ")";
				sInput = ")";
				inputField.setText(sInput);
				og.addEntry(")");
			}
			else 
			{
				sDisplay = sDisplay + ")";
				sInput += ")";
				inputField.setText(sInput);
				og.addEntry(")");
			}
        }
        else if(ae.getActionCommand().equals("="))
        {
        	//results function
				sDisplay = "=";
				sInput = "=";
				//inputField.setText(sInput);
				og.calculate();
				//displayField.setText();
        }
    }
    
    public static void main(String[] arguments) 
    {
        @SuppressWarnings("unused")
		CalculatorGUI testCalc = new CalculatorGUI();
    }
    
}
