package com.coolisland.client.model;

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

	public Value inverse(Value val);

	public Value percent(Value val);

	// returns the inverse of this object (1/x)
	public Value inverse();

	// append a digit to the current number
	public Value addDigit(String number, String digit);

	// returns number of characters in value
	public int length();

	// returns String version of this object
	public String toString();

}
