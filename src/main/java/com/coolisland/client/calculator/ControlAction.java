package com.coolisland.client.calculator;

/**
 * Represents an action that the calculator can perform.
 * Actions will be given the current and previous values as doubles
 * and return a result to display. 
 * 
 * @author Silvio
 *
 */
public abstract class ControlAction {
	private String displayString;
	private Calculator calculator;
	
	public ControlAction(Calculator calculator, String displayString) {
		this.displayString = displayString;
		this.calculator = calculator;
	}
	
	public String getDisplayString() {
		return displayString;
	}
	
	public abstract double performAction(ControlAction lastAction, double previous, double current);

	/**
	 * Should this action change the result right away?
	 * Or does it wait for another argument and then an equal sign?
	 * (e.g. +, -, /, *)
	 * 
	 *  If true, we'll wait for the = sign.
	 *  
	 *  Override this if your action updates the result immediately
	 *  (e.g. =, sqrt)
	 *  
	 * @return
	 */
	public boolean isMultiArg(){
		return true;
	}

}
