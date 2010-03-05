package com.coolisland.client.model.operation;

public class MemoryRecall extends MemoryOperation {

	public MemoryRecall() {
		super.setPrecedence(Precedent.HIGHEST);

		super.setString("MR");
	}

	@Override
	public void executeMemory() {
		// reset the display... this will reset the display to its initial value
		super.display.reset();

		// updates the display with the value from memory
		super.display.setValue(super.memory.getValue());
	}

}
