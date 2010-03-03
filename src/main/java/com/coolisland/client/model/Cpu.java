package com.coolisland.client.model;

import java.util.HashMap;

import com.coolisland.client.model.operation.Operation;
import com.coolisland.client.model.stack.OperandStack;
import com.coolisland.client.model.stack.OperationStack;
import com.coolisland.client.model.state.State;
import com.google.gwt.dev.util.Memory;

public class Cpu {
	private Memory memory;
	private Display display;
	private State state;
	private HashMap operationMap;

	boolean displayUpdate;

	// the waiting for a number state
	public State WaitingForNumberState = null;

	Display calculatorDisplay = null;

	// the waiting for an operation state
	public State WaitingForOperationState = null;

	// the waiting for an input state
	public State WaitingForInputState = null;

	// the entering number state object
	public State EnteringNumberState;

	// a stack of operands
	private final OperandStack operandStack = null;

	// a stack of operations
	private OperationStack operationStack;

	/**
	 * returns the display register
	 * 
	 * @return the display register
	 */
	public Display getDisplayRegister() {
		return calculatorDisplay;
	}

	/**
	 * Entering the state of a new operation
	 * 
	 * @param op
	 *            - the new operation
	 */
	public void replaceOperation(Operation op) {
		// TODO Auto-generated method stub

	}

	public void executeOperation(Operation op) {
		// TODO Auto-generated method stub

	}

	/**
	 * push the current display value on to an operand stack
	 */
	public void pushDisplayRegister() {
		// TODO Auto-generated method stub

	}

	/**
	 * push the operation onto the operation stack
	 * 
	 * @param op
	 */
	public void pushOperation(Operation op) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public OperandStack getOperandStack() {
		return operandStack;
	}

	/**
	 * updates the display
	 */
	public void setUpdateDisplay() {
		// TODO Auto-generated method stub

	}

}
