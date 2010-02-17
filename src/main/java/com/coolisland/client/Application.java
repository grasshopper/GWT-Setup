package com.coolisland.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application
    implements EntryPoint
{

  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
     final Label label = new Label ( "We made it to com.coolisland.client.Application.onModuleLoad()" );
     RootPanel.get().add( label );
  }
}
