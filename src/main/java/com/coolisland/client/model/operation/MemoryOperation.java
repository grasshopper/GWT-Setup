/**
 * 
 */
package com.coolisland.client.model.operation;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.Display;

/**
 * @author Silvio
 * 
 */
public abstract class MemoryOperation extends Operation {
	protected Memory memory = null;
	protected Display display = null;

	public MemoryOperation() {
		// memory operations do not need another operand
		this.setLookahead(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.coolisland.client.model.operation.Operation#execute(com.coolisland
	 * .client.model.Cpu)
	 */
	@Override
	public void execute(Cpu cpu) {
		// backup the cpu
		this.cpu = cpu;

		// backup the memory
		memory = cpu.getMemoryRegister();

		// backup the display
		display = cpu.getDisplayRegister();

		// backup the operand stack
		this.stack = cpu.getOperandStack();

		// do the operation
		executeMemory();
	}

	/**
	 * the inheriting classes must implement
	 */
	public abstract void executeMemory();
}
