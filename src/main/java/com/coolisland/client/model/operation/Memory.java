package com.coolisland.client.model.operation;

import com.coolisland.client.model.Register;
import com.coolisland.client.model.values.Value;

public class Memory extends Register {
	/**
	 * The memory status. Either the memory has been used to store a value or
	 * not. The enum value should be used as the display value on the
	 * calculator.
	 * 
	 * @author Silvio
	 */
	public static enum MemoryStatus {
		// the symbol to display when we have a value stored in memory
		MEM_SET("M"),
		// the symbol to display when we do not have a value stored in memory
		MEM_CLEAR(" ");

		private final String memoryStatus;

		private MemoryStatus(String status) {
			this.memoryStatus = status;
		}

		public String getCode() {
			return memoryStatus;
		}
	}

	// the status for the memory... that is,
	private MemoryStatus status;

	public Memory(Value initValue) {
		super(initValue);

		// start with blank/empty memory
		status = MemoryStatus.MEM_SET;
	}

	/**
	 * Update memory with the value supplied.
	 * 
	 * @param value
	 *            - the new value to set the memory to
	 */
	@Override
	public void setValue(Value value) {
		// set the memory status
		status = MemoryStatus.MEM_SET;

		// set the memory value
		super.setValue(value);
	}

	/**
	 * Clears the memory register
	 */
	@Override
	public void clear() {
		// clear the memory status
		status = MemoryStatus.MEM_CLEAR;

		// clear the memory register
		super.clear();
	}

	/**
	 * Notifies appropriate observers that the memory value has changed.
	 */
	protected void doNotify() {
		super.setChanged();
		super.notifyObservers(status);
	}

}
