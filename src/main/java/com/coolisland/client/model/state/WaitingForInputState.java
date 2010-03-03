package com.coolisland.client.model.state;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.operation.Operation;

public class WaitingForInputState extends State {

	public WaitingForInputState(Cpu cpu) {
		super(cpu);
	}

	/**
	 * User is entering a digit while in the Waiting for Input state.
	 * 
	 * Resets the Central Processing Unit, appends the digit passed in to the
	 * current display value, and moves to the next state -> Entering Number
	 * State
	 */
	@Override
	public State enterDigit(String digit) {
		/*
		 * resetting or clearing the display will set the value back to the
		 * initial value... so we shouldn't need to do that
		 */
		// reset the display register for new number
		// cpu.reset();

		// add the digit to the current display
		display.addDigit(digit);

		// move to the next state
		return cpu.EnteringNumberState;
	}

	@Override
	public State enterOperation(Operation op) {
		// if we do not have an operation yet, push the display register onto
		// the operand stack
		if (cpu.getOperandStack().empty()) {
			cpu.pushDisplayRegister();
		}

		// push the new operation onto the operation stack
		cpu.pushOperation(op);

		if (!op.getLookahead()) {
			// can't lookahead... move to the WaitingForInput state
			return cpu.WaitingForInputState;
		} else {
			// wait for a number
			return cpu.WaitingForNumberState;
		}
	}

	@Override
	public State enterValue(Operation op) {
		// reset the CPU
		cpu.reset();

		// execute operation for entering value (e.g. memory recall)
		cpu.executeOperation(op);

		// push the current display value onto the operand stack
		cpu.pushDisplayRegister();

		// we stay in the same state
		return this;
	}

}
