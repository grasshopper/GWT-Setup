package com.coolisland.client.model.operation;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.values.Value;

/**
 * Binary Operations include operations like +, -, *, and /.
 * 
 * Binary operations require a second operand.
 * 
 * @author Silvio
 * 
 */
public abstract class BinaryOperation extends Operation {

	public BinaryOperation() {
		super.setLookahead(true);
	}

	@Override
	public void execute(Cpu cpu) {
		super.cpu = cpu;
		super.stack = cpu.getOperandStack();

		// the top of the stack contains the second operand
		Value value2 = stack.pop();

		// get the first operand
		Value value1 = stack.pop();

		// push the result of the operation back on to the stack
		stack.push(executeBinary(value1, value2));

		// update the display with the new value
		cpu.setUpdateDisplay();
	}

	public abstract Value executeBinary(Value value1, Value value2);
}
