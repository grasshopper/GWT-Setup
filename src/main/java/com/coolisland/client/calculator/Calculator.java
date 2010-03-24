package com.coolisland.client.calculator;

import com.coolisland.client.controls.CalculatorFrame;
import com.coolisland.client.controls.NumberPanel;
import com.coolisland.client.controls.OperationPanel;
import com.coolisland.client.java.utils.Observer;
import com.coolisland.client.model.Cpu;
import com.coolisland.client.utils.Log;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Calculator extends Composite {
	private final Cpu cpu = null;

	private final DockPanel dockPanel;
	private OperationPanel controlsPanel;
	private NumberPanel numbersPanel;
	private CalculatorFrame calculatorDisplay;

	private static final NumberFormat nf = NumberFormat.getDecimalFormat()
			.getFormat("###0.#####;-###0.#####");

	private double lastNum = 0;
	private ControlAction lastAction = null;
	boolean clearDisplayOnNextDigit = false;

	/**
	 * 
	 */
	public Calculator() {
		Log.debug("Starting " + this.getClass().getName() + "Constructor");

		/*
		 * initialize the view objects
		 */
		dockPanel = new DockPanel();

		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.add(dockPanel);
		initWidget(mainPanel);
	}

	public void initialize() {
		/*
		 * initialize the model objects
		 */
		// cpu = new Cpu();

		/*
		 * initialize the view objects
		 */
		calculatorDisplay = new CalculatorFrame(this);
		dockPanel.add(calculatorDisplay, DockPanel.NORTH);

		numbersPanel = new NumberPanel(this);
		dockPanel.add(numbersPanel.getPanel(), DockPanel.CENTER);

		controlsPanel = new OperationPanel(this);
		dockPanel.add(controlsPanel.getPanel(), DockPanel.EAST);

		setResult(0);
	}

	/**
	 * 
	 * @param action
	 */
	public void actionClick(ControlAction action) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".actionClick(). this: " + this.toString() + " action: "
				+ action.toString());

		if (action.isMultiArg()) {
			/*
			 * A multi-argument ControlAction, e.g. + or -
			 * 
			 * Store the current value, and start collecting the second number.
			 * Store the value of the action so we remember it when they press
			 * =.
			 */
			lastNum = getCurrentNum();
			setDoClearOnNextDigit(true);
			this.lastAction = action;
		} else {
			/*
			 * An execution action such as sqrt or =
			 * 
			 * Process the last ControlAction
			 */
			setResult(action.performAction(lastAction, getLastNum(),
					getCurrentNum()));
		}
	}

	private void setResult(double result) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".setResult(). result: " + result);

		String displayResult;

		if (result > Long.MAX_VALUE || result < Long.MIN_VALUE) {
			displayResult = "ERROR";
		} else {
			displayResult = nf.format(result);
		}

		calculatorDisplay.setText(displayResult);
	}

	/**
	 * returns the last number entered
	 * 
	 * @return
	 */
	private double getLastNum() {
		Log.debug("Starting " + this.getClass().getName()
				+ ".getLastNum(). this: " + this.toString() + " lastNum: "
				+ lastNum);

		return lastNum;
	}

	/**
	 * returns the current number displayed on the calculator as a double.
	 * 
	 * @return
	 */
	private double getCurrentNum() {
		Log.debug("Starting " + this.getClass().getName()
				+ ".getCurrentNum(). this: " + this.toString());

		String current = calculatorDisplay.getText();
		double currentNum = 0;
		try {
			currentNum = Double.valueOf(current).doubleValue();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

		Log.debug("Finished " + this.getClass().getName()
				+ ".getCurrentNum(). this: " + this.toString()
				+ " currentNum: " + currentNum);

		return currentNum;
	}

	/**
	 * Set a flag which indicates whether the display should be cleared when the
	 * user enters the next digit. If the user is entering the + control button,
	 * then we would clear the current display when the next digit is entered.
	 * 
	 * If the user is entering digits/number buttons, then we would not clear
	 * the current display when the next digit is entered.
	 * 
	 * @param clearOnNextDigit
	 */
	private void setDoClearOnNextDigit(boolean clearOnNextDigit) {
		Log.debug("Finished " + this.getClass().getName()
				+ ".setDoClearOnNextDigit(). this: " + this.toString()
				+ " clearOnNextDigit: " + clearOnNextDigit);

		clearDisplayOnNextDigit = clearOnNextDigit;
	}

	private boolean isDoClearOnNextDigit() {
		Log.debug("Finished " + this.getClass().getName()
				+ ".isDoClearOnNextDigit(). this: " + this.toString()
				+ " clearDisplayOnNextDigit: " + clearDisplayOnNextDigit);

		return clearDisplayOnNextDigit;
	}

	/**
	 * 
	 * @param value
	 */
	public void enterDigit(String value) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".enterDigit(). this: " + this.toString() + " value: "
				+ value);

		if (isDoClearOnNextDigit()) {
			calculatorDisplay.setText("");
			setDoClearOnNextDigit(false);
		}

		calculatorDisplay.setText(calculatorDisplay.getText() + value);
	}

	/**
	 * 
	 * @param observer
	 */
	public void addDisplayObserver(Observer observer) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".addDisplayObserver(). this: " + this.toString()
				+ " observer: " + observer.toString());

		cpu.addDisplayObserver(observer);
	}

	/**
	 * 
	 * @param observer
	 */
	public void addMemoryObserver(Observer observer) {
		Log.debug("Starting " + this.getClass().getName()
				+ ".addMemoryObserver(). this: " + this.toString()
				+ " observer: " + observer.toString());

		cpu.addMemoryObserver(observer);
	}
}
