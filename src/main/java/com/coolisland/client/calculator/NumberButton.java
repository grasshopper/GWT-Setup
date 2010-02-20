package com.coolisland.client.calculator;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class NumberButton extends Button implements ClickListener {
	private String value;
	private Calculator calculator;
	
	public NumberButton(Calculator calculator, String value) {
		// set the button's label
		super(value);
		
		this.value = value;
		this.calculator = calculator;
		
		addClickListener(this);
	}
	
	public NumberButton(Calculator calculator, int number) {
		// convert the int to a string
		this(calculator, Integer.toString(number, 10));
	}
	
	public void onClick(Widget sender) {
		calculator.digitAction(value);
	}

}
