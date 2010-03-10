package com.coolisland.client.model.operation;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.values.Value;

public abstract class UnaryOperation extends Operation {

	public UnaryOperation() {
		// unary operations do not require a second operand
		super.setLookahead(false);

		// set the precedent for unary operators
		super.setPrecedence(Precedent.HIGH);
	}

	@Override
	public void execute(Cpu cpu) {
		// save off the the cpu
		super.cpu = cpu;

		// save off the stack
		super.stack = cpu.getOperandStack();

		// pop top operand, perform the unary operation, and push the result
		// back onto the stack
		Value value1 = super.stack.pop();
		super.stack.push(executeUnary(value1));

		// update the display
		super.cpu.setUpdateDisplay();
	}

	public abstract Value executeUnary(Value value1);
}
