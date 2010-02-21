package com.coolisland.client.utils;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Log {
	private static ArrayList<String> debugMessages = new ArrayList<String>();
	private static int textLineHeight = 42;

	/**
	 * display message in debug window
	 * 
	 * @param msg
	 */
	public static void debug(String msg) {
		Integer debugWindowWidth = 800;
		Integer debugWindowHeight = debugMessages.size() * textLineHeight
				+ textLineHeight;

		// display debug message
		// TextBox debugTextArea = new TextBox();
		// debugTextArea.setText(msg);
		// debugTextArea.setSize(debugWindowWidth.toString(), debugWindowHeight
		// .toString());
		// debugTextArea.setReadOnly(true);
		// VerticalPanel debugPanel = new VerticalPanel();
		// debugPanel.add(debugTextArea);
		//
		// // remove the old debug messages
		// RootPanel.get("debug").clear();
		// if (RootPanel.get("debug").getWidgetCount() > 1) {
		// RootPanel.get("debug").remove(0);
		// }
		//
		// RootPanel.get("debug").add(debugPanel);

		debugMessages.add(msg);
		String messages = new String();
		for (String message : debugMessages) {
			messages += message + "\n...";
		}

		//
		messages += "number of widget count in debug div: "
				+ RootPanel.get("debug").getWidgetCount();
		//

		TextBox debugTextArea = new TextBox();
		debugTextArea.setText(messages);
		debugTextArea.setSize(debugWindowWidth.toString(), debugWindowHeight
				.toString());
		debugTextArea.setReadOnly(true);
		debugTextArea.setTitle("debug");

		// remove the old debug messages
		if (RootPanel.get("debug").getWidgetCount() > 0) {
			RootPanel.get("debug").remove(0);
		}
		RootPanel.get("debug").clear();

		RootPanel.get("debug").add(debugTextArea);
	}
}
