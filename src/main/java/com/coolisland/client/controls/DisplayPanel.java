package com.coolisland.client.controls;

import com.coolisland.client.java.utils.Observable;
import com.coolisland.client.java.utils.Observer;
import com.coolisland.client.utils.CalculatorDisplay;
import com.google.gwt.user.client.ui.Widget;

public class DisplayPanel extends Widget implements Observer {
	private final CalculatorDisplay displayField;
	private final MemoryStatusDisplay memoryStatus;

	public DisplayPanel() {
		displayField = new CalculatorDisplay("");
		memoryStatus = new MemoryStatusDisplay();

		// memoryStatus = new MemoryStatus();
		// add(memoryStatus);

		// add(displayField);
	}

	public MemoryStatusDisplay getMemoryStatus() {
		return memoryStatus;
	}

	public void setDisplay(String text) {
		displayField.setText(text);
	}

	public void update(Observable observable, Object object) {
		setDisplay((String) object);
	}

}
