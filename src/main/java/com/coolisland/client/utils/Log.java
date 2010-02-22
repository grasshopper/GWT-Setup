package com.coolisland.client.utils;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Log {
	private static ArrayList<String> debugMessages = new ArrayList<String>();
	private static boolean debugPanelInitialized = false;

	/**
	 * display message in debug window
	 * 
	 * @param msg
	 */
	public static void debug(String msg) {
		debugMessages.add(msg);

		displayDebugMessages();
	}

	/**
	 * displays the debug messages in reverse chronological order
	 */
	private static void displayDebugMessages() {
		if (!debugPanelInitialized) {
			debugPanelInitialized = true;
			RootPanel.get("debug").setStylePrimaryName("LogDebugPanel");
		}

		// build the list of messages to display
		String messages = new String();
		for (int ndx = debugMessages.size() - 1; ndx >= 0; ndx--) {
			messages += debugMessages.get(ndx) + "</br>";
		}
		HTMLPanel debugPanel = new HTMLPanel(messages);

		// remove the old debug messages
		if (RootPanel.get("debug").getWidgetCount() > 0) {
			RootPanel.get("debug").remove(0);
			RootPanel.get("debug").clear();
		}

		// display the debug messages
		RootPanel.get("debug").add(debugPanel);
	}
}
