package edu.wit.comp2071.group3.stackapplication2;

import java.util.ArrayList;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class CalculatorOG {
	
	private VectorStack<String> Queue;
	private ArrayList<String> Equation;

	CalculatorOG(){
		Queue = new VectorStack<String>();
		Equation = new ArrayList<String>();
//		CalculatorGUI testCalc = new CalculatorGUI();
	}

	public void addEntry(String entry){
		Queue.push(entry);
	}

	public double calculate(){
		double answer = 0;
		ArrayList<String> equation = getEquation();

		for(int x = (equation.size() - 1); x >= 0 ;x--){
			System.out.println(equation.get(x));
		}

//	    ScriptEngineManager mgr = new ScriptEngineManager();
//	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
//	    try{
//	    	System.out.println(engine.eval(equation));
//	    	return (int) engine.eval(equation);
//	    }
//	    catch(ScriptException e){
//	    	System.out.println("error");
//	    }

	    return answer;
	}

	private ArrayList<String> getEquation(){
		if(!Queue.isEmpty()){
			String str =  Queue.pop();
			Equation.add(str);
			getEquation();
		}
		return Equation;

	}

	public static void main(String[] args){
		CalculatorOG test = new CalculatorOG();
		test.addEntry("2");
		test.addEntry("*");
		test.addEntry("3");
		test.calculate();
	}

}
