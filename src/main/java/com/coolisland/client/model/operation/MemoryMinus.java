package com.coolisland.client.model.operation;

import com.coolisland.client.model.Value;

public class MemoryMinus extends MemoryOperation {

	public MemoryMinus() {
		this.setPrecedence(Precedent.LOWEST);
		this.setString("M-");
	}

	@Override
	public void executeMemory() {
		// get the value from the top of the stack
		Value value = super.stack.peek();

		// memory = memory - value
		super.memory.setValue(super.memory.getValue().subtract(value));
	}
}
