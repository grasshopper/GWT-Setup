package com.coolisland.client.model;

public class Display extends Register {
	// max number of digits we can handle
	private final int MAX_LENGTH = 14;

	// the display value... start with a value of 0
	String displayNumber = "0";

	/**
	 * Constructs the calculator display control with the specified initial
	 * value
	 * 
	 * @param initValue
	 *            - initial value to display
	 */
	public Display(Value initValue) {
		super(initValue);
	}

	/**
	 * append a digit to current display
	 * 
	 * FIXME: need to handle decimal point.
	 * 
	 * @param digit
	 *            - digit to append to the current value in the display
	 * @return true if digit was appended, false otherwise
	 */
	public boolean addDigit(String digit) {
		/*
		 * don't allow another digit to be appended if we have reached the
		 * maximum digits
		 */
		if (super.getValue().length() > MAX_LENGTH) {
			// can't add any more digits to the number
			// TODO: should we beep or something?
			return false;
		} else {
			// append the digit to the current value
			displayNumber = super.getValue().addDigit(displayNumber, digit)
					.toString();

			// let the observer know that we have had our value changed
			setChanged();
			notifyObservers(displayNumber);

			return true;
		}
	}

	@Override
	public void setValue(Value value) {
		super.setValue(value);
		displayNumber = value.toString();
	}

	// we should not need to over-ride clear() since we aren't doing anything different.
//	@Override
//	public void clear() {
//		super.clear();
//	}
}
