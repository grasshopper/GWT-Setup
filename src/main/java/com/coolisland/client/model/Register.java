package com.coolisland.client.model;

import java.util.Observable;

import com.coolisland.client.model.values.Value;

public class Register extends Observable {
	// the current value of the register
	private Value value;

	// the initial value of the register
	private final Value initialValue;

	/**
	 * initialize the register
	 * 
	 * @param initValue
	 *            - the value to initialize the register with
	 */
	public Register(Value initValue) {
		value = initValue;
		initialValue = initValue;
	}

	/**
	 * return the register's current value
	 * 
	 * @return
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * set the register value to a new value
	 * 
	 * @param newValue
	 *            - new value to set the register to
	 */
	public void setValue(Value newValue) {
		value = newValue;
		doNotify();
	}

	public void clear() {
		reset();
		doNotify();
	}

	/**
	 * reset the register to the initial value
	 */
	public void reset() {
		value = initialValue;
	}

	/**
	 * Notify observers of this register that its value has changed.
	 */
	private void doNotify() {
		this.setChanged();
		notifyObservers(value.toString());
	}
}
