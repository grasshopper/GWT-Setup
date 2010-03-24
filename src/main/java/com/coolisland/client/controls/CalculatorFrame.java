package com.coolisland.client.controls;

import com.coolisland.client.calculator.Calculator;
import com.smartgwt.client.widgets.Window;

public class CalculatorFrame extends Window {
	final static String title = new String("Calculator");
	final static String width = "375";
	final static String height = "300";

	// Panel that contains a text field for display
	DisplayPanel display;

	// Panel that contains the memory and function keys
	// FunctionPanel function;

	// Panel that containts the + - / * = keys
	OperationPanel operation;

	// Panel that contains the number keys
	NumberPanel key;

	// a reference to the calculator
	Calculator calculator;

	public CalculatorFrame(Calculator calculator) {
		setTitle(title);
		setSize(width, height);
		this.centerInPage();
		setBackgroundColor("lightGray");

		this.calculator = calculator;

		/*
		 * add the calculator display
		 */
		display = new DisplayPanel();
		super.addChild(display);

		calculator.addDisplayObserver(display);
		calculator.addMemoryObserver(display.getMemoryStatus());

		// function = new FunctionPanel(calculator);
		// buildConstraints(constraints, 0, 2, 3, 2);
		// layout.setConstraints(function, constraints);
		// getContentPane().add(function);

		/*
		 * add the operations panel buttons
		 */
		operation = new OperationPanel(calculator);
		super.addChild(operation);

		/*
		 * add the numbers panel
		 */
		key = new NumberPanel(calculator);
		super.addChild(key);

		/*
		 * handle Window Closing
		 */
		// enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		// addKeyListener(new KeyHandler());
		show();
		// requestFocus();
	}

	public void setText(String displayResult) {
		display.setDisplay(displayResult);
	}

	public String getText() {
		return display.getDisplay();
	}

}
