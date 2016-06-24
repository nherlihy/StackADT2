package edu.wit.comp2071.group3.stackapplication2;

import java.util.Scanner;

public class CalculatorOG {
	private VectorStack<Integer> Operators;
	private VectorStack<Double> Operands;
	private VectorStack<Integer> tempOperators;
	private VectorStack<Double> tempOperands;
	


	CalculatorOG(){
		Operators = new VectorStack<Integer>();
		Operands = new VectorStack<Double>();
		tempOperators = new VectorStack<Integer>();
		tempOperands = new VectorStack<Double>();
	}
	
	public void calculate(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Evaluation Of Arithmetic Expression Using Stacks Test\n");
	    System.out.println("Enter expression\n");
	    String input = scan.next();
	    scan.close();
	    input = "0" + input;
	    input = input.replaceAll("-","+-");
	    /* Store operands and operators in respective stacks */
	    String temp = "";
	    for (int i = 0;i < input.length();i++)
	    {
	        char ch = input.charAt(i);
	        if (ch == '-')
	            temp = "-" + temp;
	        else if (ch != '+' &&  ch != '*' && ch != '/')
	           temp = temp + ch;
	        else
	        {
	            Operands.push(Double.parseDouble(temp));
	            Operators.push((int)ch);
	            temp = "";
	        }
	    }
	    Operands.push(Double.parseDouble(temp));
	    /* Create char array of operators as per precedence */
	    /* - sign is already taken care of while storing */
	    char operators[] = {'/','*','+'};
	    /* Evaluation of expression */
	    for (int i = 0; i < 3; i++)
	    {
	        boolean it = false;
	        while (!Operators.isEmpty())
	        {
	            int optr = Operators.pop();
	            double v1 = Operands.pop();
	            double v2 = Operands.pop();
	            if (optr == operators[i])
	            {
	                /* if operator matches evaluate and store in temporary stack */
	                if (i == 0)
	                {
	                    tempOperands.push(v2 / v1);
	                    it = true;
	                    break;
	                }
	                else if (i == 1)
	                {
	                    tempOperands.push(v2 * v1);
	                    it = true;
	                    break;
	                }
	                else if (i == 2)
	                {
	                    tempOperands.push(v2 + v1);
	                    it = true;
	                    break;
	                }                                        
	            }
	            else
	            {
	                tempOperands.push(v1);
	                Operands.push(v2);
	                tempOperators.push(optr);
	            }                
	        }    
	        /* Push back all elements from temporary stacks to main stacks */            
	        while (!tempOperands.isEmpty())
	            Operands.push(tempOperands.pop());
	        while (!tempOperators.isEmpty())
	            Operators.push(tempOperators.pop());
	        /* Iterate again for same operator */
	        if (it)
	            i--;                            
	    }    
	    System.out.println("\nResult = "+Operands.pop());        
	}	

	public static void main(String[] args){
		CalculatorOG test = new CalculatorOG();
		test.calculate();
	}
}
