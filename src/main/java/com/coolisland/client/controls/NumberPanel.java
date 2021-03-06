package com.coolisland.client.controls;

import com.coolisland.client.calculator.Calculator;
import com.coolisland.client.utils.Log;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public class NumberPanel extends Widget {
	Grid numbersPanel = new Grid(4, 3);

	public NumberPanel(Calculator calculator) {
		Log.debug("Starting " + this.getClass().getName()
				+ " Contsructor. calculator: " + calculator);

		initNumberPad(calculator);
	}

	/**
	 * Initialize the number key pad panel
	 */
	private void initNumberPad(Calculator calculator) {
		Log.debug("Starting " + this.getClass().getName() + ".initNumberPad()");

		/*
		 * Initialze the 1 through 9 buttons
		 */
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				numbersPanel.setWidget(row, col, new NumberButton(calculator,
						row * 3 + col + 1));
			}
		}

		/*
		 * add the 0 button
		 */
		numbersPanel.setWidget(3, 0, new NumberButton(calculator, 0));
	}

	public Widget getPanel() {
		Log.debug("Starting " + this.getClass().getName() + ".getPanel()");

		return numbersPanel;
	}
}
