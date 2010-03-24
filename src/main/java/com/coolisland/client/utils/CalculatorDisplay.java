package com.coolisland.client.utils;

import com.google.gwt.user.client.ui.TextBox;

public class CalculatorDisplay extends TextBox {
	private static final int MAXDIGITS = 16;

	public CalculatorDisplay(String displayString) {
		Log.debug("Starting " + this.getClass().getName()
				+ " Contsructor. displayString: " + displayString);
		/*
		 * set control properties
		 */
		// setBackground(Color.lightGray);
		// Font font = new Font("Monospaced", Font.BOLD, 18);
		// Border bevelBorder = BorderFactory.createLoweredBevelBorder();
		// setFont(font);
		// setEditable(false);
		// setBackground(Color.white);
		// setHorizontalAlignment(JTextField.RIGHT);
		// setBorder(bevelBorder);

		setText(displayString);
		setReadOnly(true);
		setTextAlignment(ALIGN_RIGHT);

		addStyleDependentName("CalculatorDisplay");
	}
}