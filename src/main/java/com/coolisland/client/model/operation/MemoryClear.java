package com.coolisland.client.model.operation;

public class MemoryClear extends MemoryOperation {

	public MemoryClear() {
		super.setPrecedence(Precedent.LOWEST);

		super.setString("MC");
	}

	/**
	 * clear the memory
	 */
	@Override
	public void executeMemory() {
		super.memory.clear();
	}

}
