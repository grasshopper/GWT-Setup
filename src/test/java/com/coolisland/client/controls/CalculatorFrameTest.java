package com.coolisland.client.controls;

import junit.framework.Assert;

import com.coolisland.client.calculator.Calculator;
import com.google.gwt.junit.client.GWTTestCase;

public class CalculatorFrameTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.coolisland.Application";
	}

	public void testSomething() {
		Calculator calculator = new Calculator();
		Assert.assertNotNull(calculator);

		CalculatorFrame calculatorFrame = new CalculatorFrame(calculator);
		Assert.assertNotNull(calculatorFrame);
	}
}
