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

	public Value subtrack(Value val);

	public Value multiply(Value val);

	public Value divide(Value val);

	/*
	 * unary operators
	 */
	public Value negate(Value val);

	public Value squareRoot(Value val);

	public Value inverse(Value val);

	public Value percent(Value val);

	/*
	 * append a digit to the current number
	 */
	public Value addDigit(String number, String digit);

	/**
	 * @return number of characters in value
	 */
	public int length();

	/**
	 * @return String version of value
	 */
	public String toString();

}
