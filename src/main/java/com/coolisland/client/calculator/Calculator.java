package com.coolisland.client.calculator;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Calculator extends Composite {

	DockPanel dockPanel = new DockPanel();
	Grid controls = new Grid(5, 2);
	Grid numbersP = new Grid(4, 3);

	private static final NumberFormat nf = NumberFormat.getDecimalFormat()
			.getFormat("###0.#####;-###0.#####");

	private double lastNum = 0;
	private ControlAction lastAction = null;
	boolean clearDisplayOnNextDigit = false;
	private final TextBox inputBox;
	private final TextArea ticker;

	/**
	 * 
	 */
	public Calculator() {

		initNumberPad();
		initControlPad();

		dockPanel.add(numbersP, DockPanel.CENTER);
		dockPanel.add(controls, DockPanel.EAST);

		inputBox = new TextBox();
		inputBox.addStyleName("Resultbox");
		dockPanel.add(inputBox, DockPanel.NORTH);

		ticker = new TextArea();
		ticker.setSize("7em", "140px");

		HorizontalPanel mainP = new HorizontalPanel();
		mainP.add(dockPanel);
		mainP.add(ticker);
		initWidget(mainP);

		setResult(0);
	}

	private void initNumberPad() {
		/*
		 * Initialze the 1 through 9 buttons
		 */
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				numbersP.setWidget(row, col, new NumberButton(this, row * 3
						+ col + 1));
			}
		}
	}

	private void initControlPad() {
		/*
		 * Initialize the control buttons
		 */
		// add the + button to the list of controls
		ControlAction plusControlButtonAction = new ControlAction(this, "+") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous + current;
			}
		};
		Widget plusControlButtonWidget = new ControlButton(this,
				plusControlButtonAction);
		controls.setWidget(3, 0, plusControlButtonWidget);

		// add the = button to the list of controls
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
		controls.setWidget(3, 1, equalControlButtonWidget);

		// add the backspace button to the list of controls
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
		controls.setWidget(3, 1, backspaceControlButtonWidget);
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
		this.inputBox.setText(nf.format(result));
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
		String current = inputBox.getText();
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
			inputBox.setText("");
			setDoClearOnNextDigit(false);
		}

		inputBox.setText(inputBox.getText() + value);
	}
}
