package com.coolisland.client.controls;

import com.coolisland.client.calculator.Calculator;
import com.coolisland.client.calculator.ControlAction;
import com.coolisland.client.utils.Log;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public class ControlPanel {
	Grid controlsPanel = new Grid(4, 2);

	public ControlPanel(Calculator calculator) {
		initControlPad(calculator);
	}

	/**
	 * Initialize the control buttons on the calculator
	 */
	private void initControlPad(Calculator calculator) {
		/*
		 * Initialize the control buttons
		 */
		// add the clear button to the list of controls
		addClearButton(calculator);

		// add the backspace button to the list of controls
		addBackSpaceButton(calculator);

		// add the multiply button
		addMultiplyButton(calculator);

		// add the divide button
		addDivisionButton(calculator);

		// add the + button to the list of controls
		addPlusButton(calculator);

		// add the minus button
		addMinusButton(calculator);

		// add the = button to the list of controls
		addEqualButton(calculator);
	}

	public Widget getPanel() {
		return controlsPanel;
	}

	/**
	 * add the * button to the list of controls
	 */
	private void addMultiplyButton(Calculator calculator) {
		ControlAction multiplyControlButtonAction = new ControlAction(
				calculator, "x") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous * current;
			}
		};

		Widget multiplyControlButtonWidget = new ControlButton(calculator,
				multiplyControlButtonAction);
		controlsPanel.setWidget(1, 0, multiplyControlButtonWidget);
	}

	/**
	 * add the / button to the list of controls
	 */
	private void addDivisionButton(Calculator calculator) {
		ControlAction divideControlButtonAction = new ControlAction(calculator,
				"/") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous / current;
			}
		};

		Widget divideControlButtonWidget = new ControlButton(calculator,
				divideControlButtonAction);
		controlsPanel.setWidget(1, 1, divideControlButtonWidget);
	}

	/**
	 * add the + button to the list of controls
	 */
	private void addPlusButton(Calculator calculator) {
		ControlAction plusControlButtonAction = new ControlAction(calculator,
				"+") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {

				Log.debug(this.getClass().getName()
						+ "addPlusButton() previous: " + previous
						+ " current: " + current);

				return previous + current;
			}
		};

		Widget plusControlButtonWidget = new ControlButton(calculator,
				plusControlButtonAction);
		controlsPanel.setWidget(2, 0, plusControlButtonWidget);
	}

	/**
	 * add the - button to the list of controls
	 */
	private void addMinusButton(Calculator calculator) {
		ControlAction minusControlButtonAction = new ControlAction(calculator,
				"-") {
			@Override
			public double performAction(ControlAction lastAction,
					double previous, double current) {
				return previous - current;
			}
		};

		Widget minusControlButtonWidget = new ControlButton(calculator,
				minusControlButtonAction);
		controlsPanel.setWidget(2, 1, minusControlButtonWidget);
	}

	/**
	 * add the = button to the list of controls
	 */
	private void addEqualButton(Calculator calculator) {
		ControlAction equalControlButtonAction = new ControlAction(calculator,
				"=") {
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

		Widget equalControlButtonWidget = new ControlButton(calculator,
				equalControlButtonAction);
		controlsPanel.setWidget(3, 0, equalControlButtonWidget);
	}

	/**
	 * add the backspace button to the list of controls
	 */
	private void addBackSpaceButton(Calculator calculator) {
		ControlAction backspaceControlButtonAction = new ControlAction(
				calculator, "bksp") {
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

		Widget backspaceControlButtonWidget = new ControlButton(calculator,
				backspaceControlButtonAction);
		controlsPanel.setWidget(0, 1, backspaceControlButtonWidget);
	}

	/**
	 * add the clear button to the list of controls
	 */
	private void addClearButton(Calculator calculator) {
		ControlAction clearControlButtonAction = new ControlAction(calculator,
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

		Widget clearControlButtonWidget = new ControlButton(calculator,
				clearControlButtonAction);
		controlsPanel.setWidget(0, 0, clearControlButtonWidget);
	}

}
