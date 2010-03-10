package com.coolisland.client.model.state;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.operation.Operation;

public class WaitingForNumberState extends State {

	public WaitingForNumberState(Cpu cpu) {
		super(cpu);
	}

	@Override
	public State enterDigit(String digit) {
		/*
		 * resetting or clearing the display will set the value back to the
		 * initial value... so we shouldn't need to do that
		 */
		// reset the display register for new number
		// display.reset();

		// add the new digit
		display.addDigit(digit);

		return cpu.enteringNumberState;
	}

	@Override
	public State enterOperation(Operation op) {
		// enter a state of a new operation
		cpu.replaceOperation(op);

		// FIXME: why would we stay in the current state?
		// stay in this state
		return this;
	}

	@Override
	public State enterValue(Operation op) {
		// execute operation for entering value (e.g. memory recall)
		cpu.executeOperation(op);

		// next state
		return cpu.waitingForOperationState;
	}
}
