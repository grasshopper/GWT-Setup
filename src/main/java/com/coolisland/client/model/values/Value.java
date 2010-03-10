package com.coolisland.client.model.values;

/**
 * Represents values that the calculator displays and operates on.
 * 
 * Value is a generic object so that the calculator can be implemented using
 * whatever specific type (e.g. Double, Long, Integer, etc.) is desired.
 * 
 * @author Silvio
 * 
 */
public interface Value {
	/*
	 * binary operators
	 */
	public Value add(Value val);

	public Value subtract(Value val);

	public Value multiply(Value val);

	public Value divide(Value val);

	/*
	 * unary operators
	 */
	// returns the negated version of this object
	public Value negate();

	// returns the square root of this object
	public Value squareRoot();

	// returns the inverse of this object (1/x)
	public Value inverse();

	// append a digit to the current number
	public String addDigit(String number, String digit);

	// returns String version of this object
	public String toString();

	/*
	 * returns the length of the value. Can be used to determine if any more
	 * characters can be appended to the display.
	 */
	public int length();

	public Value create(String string);
}
