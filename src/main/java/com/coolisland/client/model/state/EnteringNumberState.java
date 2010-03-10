package com.coolisland.client.model.state;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.operation.Operation;

public class EnteringNumberState extends State {

	public EnteringNumberState(Cpu cpu) {
		super(cpu);
	}

	@Override
	public State enterDigit(String digit) {
		// add the digit to the display
		display.addDigit(digit);

		// remain in the EnteringNumberState
		return this;
	}

	@Override
	public State enterOperation(Operation op) {
		// push the current display value onto the operand stack
		cpu.pushDisplayRegister();

		// push the new operation onto the operation stack
		cpu.pushOperation(op);

		// don't look ahead... go to WaitingForInput
		if (!op.getLookahead()) {
			// don't look ahead... move to the WaitingForInputState state
			return cpu.waitingForInputState;
		} else {
			// look ahead... move to the WaitingForNumber state
			return cpu.waitingForNumberState;
		}
	}

	@Override
	public State enterValue(Operation op) {
		// TODO Auto-generated method stub
		return null;
	}

}
