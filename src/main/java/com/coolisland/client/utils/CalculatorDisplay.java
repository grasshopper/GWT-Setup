package com.coolisland.client.utils;

import com.google.gwt.user.client.ui.TextBox;

public class CalculatorDisplay extends TextBox {

	public CalculatorDisplay(String displayString) {
		super.setText(displayString);
		super.setReadOnly(true);
		super.setTextAlignment(ALIGN_RIGHT);

		addStyleDependentName("CalculatorDisplay");
	}
}