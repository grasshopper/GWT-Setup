/**
 * 
 */
package com.coolisland.client.calculator;

import com.coolisland.client.controls.CalculatorButton;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Silvio
 * 
 */
public class ControlButton extends CalculatorButton implements ClickListener {
	private final ControlAction action;
	private final Calculator calculator;

	public ControlButton(Calculator calculator, ControlAction action) {
		// set the button label to the action's display string
		super(action.getDisplayString());

		this.action = action;
		this.calculator = calculator;

		addClickListener(this);
	}

	public void onClick(Widget sender) {
		calculator.actionClick(action);
	}

}
