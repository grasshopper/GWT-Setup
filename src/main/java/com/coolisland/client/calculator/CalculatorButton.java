package com.coolisland.client.calculator;

import com.google.gwt.user.client.ui.Button;

public class CalculatorButton extends Button {

	public CalculatorButton(String displayString) {
		super(displayString);
		addStyleDependentName("CalculatorButton");
	}
}
