package com.coolisland.client.model.state;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.Display;
import com.coolisland.client.model.operation.Operation;

public abstract class State {
	/*
	 * The calculator's display. The state will need access to the display
	 */
	protected final Display display;

	/**
	 * The central processing unit
	 */
	protected final Cpu cpu;

	public State(Cpu cpu) {
		this.cpu = cpu;

		display = this.cpu.getDisplayRegister();
	}

	/**
	 * user has entered a digit
	 * 
	 * @param digit
	 *            - digit entered by the user
	 * @return
	 */
	public abstract State enterDigit(String digit);

	/**
	 * user has entering an operation
	 * 
	 * @param op
	 *            - operation entered by the user
	 * @return
	 */
	public abstract State enterOperation(Operation op);

	/**
	 * not sure how this method gets used
	 * 
	 * @param op
	 * @return
	 */
	public abstract State enterValue(Operation op);
}
