package edu.wit.comp2071.group3.stackapplication2;

import java.io.*;

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
		
	String line = "";
	
	/** Reads through file and calculates each equation.
    @param filePath of file to check spelling */
	public void readFile(String filePath){
	    try{
            FileReader fileReader = new FileReader(filePath); // Open File
            BufferedReader bufferReader = new BufferedReader(fileReader); // Read File
           
            while((line = bufferReader.readLine()) != null){
            	calculate();
            }
            bufferReader.close();
        } // end try
        catch(Exception ex){
            System.out.println("Unable to open file");
        }
    } // end readFile

	/** Calculates the equation using stacks */
	public void calculate(){
	    String filenput = line;
	    String heldOperands = "";
	    filenput = filenput.replaceAll("-","+-");

	    /* Store operands and operators in respective stacks */
	    for (int i = 0;i < filenput.length();i++)
	    {
	        char heldOperators = filenput.charAt(i);
	        if (heldOperators == '-')
	        	heldOperands = "-" + heldOperands;
	        else if (heldOperators != '+' &&  heldOperators != '*' && heldOperators != '/')
	           heldOperands = heldOperands + heldOperators;
	        else
	        {
	            Operands.push(Double.parseDouble(heldOperands));
	            Operators.push((int)heldOperators);
	            heldOperands = "";
	        }
	    }

	    Operands.push(Double.parseDouble(heldOperands));

	    /* Create char array of operators as per precedence */
	    /* - sign is already taken care of while storing */
	    char operators[] = {'/','*','+'};

	    /* Evaluation of expression */
	    for (int i = 0; i < 3; i++){
	        boolean iterate = false;
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
	                	if(operators[i] == '/' && v1 == 0){
	                		System.out.println("Error: can't divide by '0'");
	                		System.out.println("");
	                	}
	                	else {
	                		tempOperands.push(v2 / v1);
	                		iterate = true;
	                		break;
	                	}
	                }
	                else if (i == 1)
	                {
	                    tempOperands.push(v2 * v1);
	                    iterate = true;
	                    break;
	                }
	                else if (i == 2)
	                {
	                    tempOperands.push(v2 + v1);
	                    iterate = true;
	                    break;
	                }                                        
	            } // end if
	            else
	            {
	                tempOperands.push(v1);
	                Operands.push(v2);
	                tempOperators.push(optr);
	            }                
	        } // end while

	        /* Push back all elements from temporary stacks to main stacks */            
	        while (!tempOperands.isEmpty()){
	            Operands.push(tempOperands.pop());
	        }
	        while (!tempOperators.isEmpty()){
	            Operators.push(tempOperators.pop());
	        }
	        /* Iterate again for same operator */
	        if (iterate){
	            i--;
	        }
	    } // end for

	    /* Print out the solution */
	    try{
	    	System.out.print(line);
	    	System.out.println(" = "+ Operands.pop() + "\n");
	    }
	    catch(java.util.EmptyStackException e){
	    	//If division by 0 then the stack is left with zero entries and no result
	    	//Will catch and just continue
	    }
	} // end calculate

	
	public static void main(String[] args){
		CalculatorOG test = new CalculatorOG(); 
		test.readFile("data/math.txt");
	}
}
