package com.coolisland.client.model.values;

public class IntegerValue implements Value {
	protected Integer value = null;

	/**
	 * Creates a DecimalValue from the string value passed in
	 * 
	 * @param value
	 *            - the value to create the DecimalValue from
	 */
	public IntegerValue(String value) {
		this.value = new Integer(value);
	}

	public IntegerValue(int i) {
		this.value = new Integer(value);
	}

	public Value add(Value val) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addDigit(String number, String digit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Value create(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Value divide(Value val) {
		// TODO Auto-generated method stub
		return null;
	}

	public Value inverse() {
		// TODO Auto-generated method stub
		return null;
	}

	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Value multiply(Value val) {
		// TODO Auto-generated method stub
		return null;
	}

	public Value negate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Value squareRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	public Value subtract(Value val) {
		// TODO Auto-generated method stub
		return null;
	}

}
