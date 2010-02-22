package com.coolisland.client.calculator;

import com.coolisland.client.utils.CalculatorDisplay;
import com.coolisland.client.utils.Log;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Calculator extends Composite {

	DockPanel dockPanel = new DockPanel();
	Grid controlsPanel = new Grid(4, 2);
	Grid numbersPanel = new Grid(4, 3);
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

		initNumberPad();
		initControlPad();

		calculatorDisplay = new CalculatorDisplay("");
		dockPanel.add(calculatorDisplay, DockPanel.NORTH);

		dockPanel.add(numbersPanel, DockPanel.CENTER);
		dockPanel.add(controlsPanel, DockPanel.EAST);

		HorizontalPanel mainP = new HorizontalPanel();
		mainP.add(dockPanel);
		initWidget(mainP);

		setResult(0);
	}

	/**
	 * Initialize the number key pad panel
	 */
	private void initNumberPad() {
		/*
		 * Initialze the 1 through 9 buttons
		 */
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				numbersPanel.setWidget(row, col, new NumberButton(this, row * 3
						+ col + 1));
			}
		}

		/*
		 * add the 0 button
		 */
		numbersPanel.setWidget(3, 0, new NumberButton(this, 0));
	}

	/**
	 * add the * button to the list of controls
	 */
	private void addMultiplyButton() {
		ControlAction multiplyControlButtonAction = new ControlAction(this, "x") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous * current;
			}
		};
		Widget multiplyControlButtonWidget = new ControlButton(this,
				multiplyControlButtonAction);
		controlsPanel.setWidget(1, 0, multiplyControlButtonWidget);
	}

	/**
	 * add the / button to the list of controls
	 */
	private void addDivisionButton() {
		ControlAction divideControlButtonAction = new ControlAction(this, "/") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous / current;
			}
		};
		Widget divideControlButtonWidget = new ControlButton(this,
				divideControlButtonAction);
		controlsPanel.setWidget(1, 1, divideControlButtonWidget);
	}

	/**
	 * add the + button to the list of controls
	 */
	private void addPlusButton() {
		ControlAction plusControlButtonAction = new ControlAction(this, "+") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {

				Log.debug(this.getClass().getName()
						+ "addPlusButton() previous: " + previous
						+ " current: " + current);

				return previous + current;
			}
		};
		Widget plusControlButtonWidget = new ControlButton(this,
				plusControlButtonAction);
		controlsPanel.setWidget(2, 0, plusControlButtonWidget);
	}

	/**
	 * add the - button to the list of controls
	 */
	private void addMinusButton() {
		ControlAction minusControlButtonAction = new ControlAction(this, "-") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous - current;
			}
		};
		Widget minusControlButtonWidget = new ControlButton(this,
				minusControlButtonAction);
		controlsPanel.setWidget(2, 1, minusControlButtonWidget);
	}

	/**
	 * add the = button to the list of controls
	 */
	private void addEqualButton() {
		ControlAction equalControlButtonAction = new ControlAction(this, "=") {
			@Override
			public boolean isMultiArg() {
				return false;
			}

			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				if (lastAction == null) {
					return current;
				} else {
					return lastAction.performAction(null, previous, current);
				}
			}
		};
		Widget equalControlButtonWidget = new ControlButton(this,
				equalControlButtonAction);
		controlsPanel.setWidget(3, 0, equalControlButtonWidget);
	}

	/**
	 * add the backspace button to the list of controls
	 */
	private void addBackSpaceButton() {
		ControlAction backspaceControlButtonAction = new ControlAction(this,
				"bksp") {
			@Override
			public boolean isMultiArg() {
				return false;
			}

			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				String currentValue = current + "";

				/*
				 * if the value ends with .0 (e.g. 11.0) then we truncate the .0
				 * and the digit before the decimal (e.g. return 1)
				 */
				if (currentValue.endsWith(".0")) {
					currentValue = currentValue.substring(0, currentValue
							.length() - 3);
				} else {
					currentValue = currentValue.substring(0, currentValue
							.length() - 1);
				}

				// if the value is empty, then return a 0
				if (currentValue.equals("")) {
					currentValue = "0";
				}

				return Double.parseDouble(currentValue);
			}
		};
		Widget backspaceControlButtonWidget = new ControlButton(this,
				backspaceControlButtonAction);
		controlsPanel.setWidget(0, 1, backspaceControlButtonWidget);
	}

	/**
	 * add the clear button to the list of controls
	 */
	private void addClearButton() {
		ControlAction clearControlButtonAction = new ControlAction(this,
				"clear") {
			@Override
			public boolean isMultiArg() {
				return false;
			}

			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				String currentValue = "0";
				return Double.parseDouble(currentValue);
			}
		};
		Widget clearControlButtonWidget = new ControlButton(this,
				clearControlButtonAction);
		controlsPanel.setWidget(0, 0, clearControlButtonWidget);
	}

	/**
	 * Initialize the control buttons on the calculator
	 */
	private void initControlPad() {
		/*
		 * Initialize the control buttons
		 */
		// add the clear button to the list of controls
		addClearButton();

		// add the backspace button to the list of controls
		addBackSpaceButton();

		// add the multiply button
		addMultiplyButton();

		// add the divide button
		addDivisionButton();

		// add the + button to the list of controls
		addPlusButton();

		// add the minus button
		addMinusButton();

		// add the = button to the list of controls
		addEqualButton();
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
	public void digitAction(String value) {
		if (isDoClearOnNextDigit()) {
			calculatorDisplay.setText("");
			setDoClearOnNextDigit(false);
		}

		calculatorDisplay.setText(calculatorDisplay.getText() + value);
	}
}
