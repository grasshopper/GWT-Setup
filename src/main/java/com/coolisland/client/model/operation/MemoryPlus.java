package com.coolisland.client.model.operation;

import com.coolisland.client.model.Value;

public class MemoryPlus extends MemoryOperation {

	public MemoryPlus() {
		super.setPrecedence(Precedent.LOWEST);

		super.setString("M+");
	}

	@Override
	public void executeMemory() {
		// get the top value from the memory stack
		Value value = stack.peek();

		// memory = memory + value
		super.memory.setValue(super.memory.getValue().add(value));
	}

}
