package com.coolisland.client.model.stack;

import com.coolisland.client.model.operation.Operation;

/**
 * Operation stack which operates on the operands
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
public class OperationStack extends CalculatorStack<Operation> {

	public OperationStack() {
		super(Operation.class);
	}
}
