/**
 * 
 */
package com.coolisland.client.calculator;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Silvio
 *
 */
public class ControlButton extends Button implements ClickListener {
	private ControlAction action;
	private Calculator calculator;
	
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
