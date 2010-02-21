package com.coolisland.client.utils;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Log {
	private static ArrayList<String> debugMessages = new ArrayList<String>();

	// private static int textLineHeight = 42;

	/**
	 * display message in debug window
	 * 
	 * @param msg
	 */
	public static void debug(String msg) {
		// Integer debugWindowWidth = 800;
		// Integer debugWindowHeight = debugMessages.size() * textLineHeight
		// + textLineHeight;

		debugMessages.add(msg);
		String messages = new String();
		for (String message : debugMessages) {
			messages += message + "</br>";
		}

		displayDebugMessages(messages);
	}

	private static void displayDebugMessages(String debugHtml) {
		HTMLPanel debugPanel = new HTMLPanel(debugHtml);

		// remove the old debug messages
		if (RootPanel.get("debug").getWidgetCount() > 0) {
			RootPanel.get("debug").remove(0);
		}
		RootPanel.get("debug").clear();

		RootPanel.get("debug").add(debugPanel);
	}
}
