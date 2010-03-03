package com.coolisland.client.model.state;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.operation.Operation;

public class WaitingForOperationState extends State {

	public WaitingForOperationState(Cpu cpu) {
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

		// append the new digit
		display.addDigit(digit);

		// allow the user to continue entering the number
		return cpu.EnteringNumberState;
	}

	@Override
	public State enterOperation(Operation op) {
		// behavior is same as in EnteringNumberState
		return cpu.EnteringNumberState.enterOperation(op);
	}

	@Override
	public State enterValue(Operation op) {
		// execute operation for entering value (e.g. memory recall)
		cpu.executeOperation(op);

		// next state
		return this;
	}

}
