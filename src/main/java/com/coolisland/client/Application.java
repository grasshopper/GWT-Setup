package com.coolisland.client;

import com.coolisland.client.calculator.Calculator;
import com.coolisland.client.utils.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initialize();
	}

	private void initialize() {
		try {
			// display debug message
			Log
					.debug("We made it to com.coolisland.client.Application.onModuleLoad()");

			// display the calculator
			Calculator calc = new Calculator();
			RootPanel.get("calculator").add(calc);

			// hide the loading message
			RootPanel.get("loading").setVisible(false);
		} catch (Exception e) {
			String msg = "in the catch block.....";
			Label label = new Label(msg);
			RootPanel.get().add(label);

			// dump the stack trace on the sever
			e.printStackTrace();

			// dump the stack trace on the HTML client page
			VerticalPanel panel = new VerticalPanel();
			panel.add(new Label("Error"));
			panel.add(new Label(e.getMessage()));
			RootPanel.get().add(panel);
		}

		Log.debug("Outside of the try/catch block.....");

	}
}
