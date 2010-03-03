package com.coolisland.client.model.stack;

import com.coolisland.client.model.Value;

/**
 * Operand stack on which the operations execute
 * 
 * @author Silvio
 * 
 *         This design and code is based on design and code provided by Objects
 *         by Design, Inc.
 * 
 *         This program is free software; you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation. A copy of the license may be found at
 *         http://www.objectsbydesign.com/projects/gpl.txt
 * 
 */
public class OperandStack extends CalculatorStack<Value> {

	public OperandStack() {
		super(Value.class);
	}
}
