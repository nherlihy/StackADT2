package edu.wit.comp2071.group3.stackapplication2;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class CalculatorOG {
	
	private VectorStack<String> Queue;
	private String Equation;

	CalculatorOG(){
		Queue = new VectorStack<String>();
		Equation = "";
	}

	public void addEntry(String entry){
		Queue.push(entry);
	}

	public void calculate(){
		String equation = getEquation();

	    ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    try{
	    	System.out.println(engine.eval(equation));
	    }
	    catch(ScriptException e){
	    	System.out.println("error");
	    }

	}

	private String getEquation(){
		if(!Queue.isEmpty()){
			String str =  Queue.pop();
			Equation += str;
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
