package com.coolisland.client.controls;

import com.coolisland.client.java.utils.Observable;
import com.coolisland.client.java.utils.Observer;
import com.google.gwt.user.client.ui.TextBox;

public class MemoryStatusDisplay extends TextBox implements Observer {

	public MemoryStatusDisplay() {
		/*
		 * set the control properties
		 */
		// Font font = new Font("Helvetica", Font.BOLD | Font.ITALIC, 14);
		// Border emptyBorder = BorderFactory.createEmptyBorder();
		//
		// setFont(font);
		// setEditable(false);
		// setBackground(Color.lightGray);
		// setBorder(emptyBorder);

		setText("");

	}

	/**
	 * 
	 */
	public void update(Observable observable, Object object) {
		setText((String) object);
	}

}
