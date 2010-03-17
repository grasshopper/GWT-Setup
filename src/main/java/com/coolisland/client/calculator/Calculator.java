package com.coolisland.client.calculator;

import com.coolisland.client.controls.NumberPanel;
import com.coolisland.client.controls.OperationPanel;
import com.coolisland.client.java.utils.Observer;
import com.coolisland.client.model.Cpu;
import com.coolisland.client.utils.CalculatorDisplay;
import com.coolisland.client.utils.Log;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Calculator extends Composite {
	private final Cpu cpu = null;

	private final DockPanel dockPanel;
	private final OperationPanel controlsPanel;
	private final NumberPanel numbersPanel;
	private final CalculatorDisplay calculatorDisplay;

	private static final NumberFormat nf = NumberFormat.getDecimalFormat()
			.getFormat("###0.#####;-###0.#####");

	private double lastNum = 0;
	private ControlAction lastAction = null;
	boolean clearDisplayOnNextDigit = false;

	/**
	 * 
	 */
	public Calculator() {
		/*
		 * initialize the model objects
		 */
		// cpu = new Cpu();

		/*
		 * initialize the view objects
		 */
		dockPanel = new DockPanel();

		calculatorDisplay = new CalculatorDisplay("");
		dockPanel.add(calculatorDisplay, DockPanel.NORTH);

		numbersPanel = new NumberPanel(this);
		dockPanel.add(numbersPanel.getPanel(), DockPanel.CENTER);

		controlsPanel = new OperationPanel(this);
		dockPanel.add(controlsPanel.getPanel(), DockPanel.EAST);

		HorizontalPanel mainP = new HorizontalPanel();
		mainP.add(dockPanel);
		initWidget(mainP);

		setResult(0);
	}

	/**
	 * 
	 * @param action
	 */
	public void actionClick(ControlAction action) {
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
		String displayResult;

		Log.debug(this.getClass().getName() + ".setResult() result: " + result);

		if (result > Long.MAX_VALUE || result < Long.MIN_VALUE) {
			displayResult = "ERROR";
		} else {
			displayResult = nf.format(result);
		}

		this.calculatorDisplay.setText(displayResult);
	}

	/**
	 * returns the last number entered
	 * 
	 * @return
	 */
	private double getLastNum() {
		return lastNum;
	}

	/**
	 * returns the current number displayed on the calculator as a double.
	 * 
	 * @return
	 */
	private double getCurrentNum() {
		String current = calculatorDisplay.getText();
		double currentNum = 0;
		try {
			currentNum = Double.valueOf(current).doubleValue();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

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
		clearDisplayOnNextDigit = clearOnNextDigit;
	}

	private boolean isDoClearOnNextDigit() {
		return clearDisplayOnNextDigit;
	}

	/**
	 * 
	 * @param value
	 */
	public void enterDigit(String value) {
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
		cpu.addDisplayObserver(observer);
	}

	/**
	 * 
	 * @param observer
	 */
	public void addMemoryObserver(Observer observer) {
		cpu.addMemoryObserver(observer);
	}
}
