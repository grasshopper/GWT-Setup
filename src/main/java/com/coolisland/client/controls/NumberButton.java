package com.coolisland.client.controls;

import com.coolisland.client.calculator.Calculator;
import com.coolisland.client.utils.Log;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class NumberButton extends CalculatorButton implements ClickListener {
	private final String value;
	private final Calculator calculator;

	public NumberButton(Calculator calculator, String value) {
		// set the button's label
		super(value);

		Log.debug("Starting " + this.getClass().getName()
				+ "Constructor. Calculator: " + calculator.toString()
				+ " value: " + value);

		this.value = value;
		this.calculator = calculator;

		addClickListener(this);
	}

	public NumberButton(Calculator calculator, int number) {
		// convert the int to a string
		this(calculator, Integer.toString(number, 10));
	}

	public void onClick(Widget sender) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".onClick(). calculator: " + calculator.toString()
				+ " value: " + value + " sender: " + sender.toString());

		calculator.enterDigit(value);
	}

}
