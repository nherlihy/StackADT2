package edu.wit.comp2071.group3.stackapplication2;

public class CalculatorOG {
	
	private VectorStack<String> Queue;
	private VectorStack<String> Operators;
	private VectorStack<Integer> Operands;

	CalculatorOG(){
		Queue = new VectorStack<String>();
		Operators = new VectorStack<String>();
		Operands = new VectorStack<Integer>();
//		CalculatorGUI testCalc = new CalculatorGUI();
	}

	public void addEntry(String entry){
		Queue.push(entry);
	}

	public double calculate(){
		return 0;
	}


	public static void main(String[] args){
		CalculatorOG test = new CalculatorOG();
		test.addEntry("2");
		test.addEntry("*");
		test.addEntry("3");
		test.calculate();
	}

}
