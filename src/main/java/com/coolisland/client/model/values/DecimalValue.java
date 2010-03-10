package com.coolisland.client.model.values;

import java.math.BigDecimal;

public class DecimalValue implements Value {
	protected BigDecimal value;
	private static BigDecimal one;
	private static BigDecimal hundred;
	private static BigDecimal zero;

	private static final int SCALE = 10;

	static {
		one = new BigDecimal(1.0);
		hundred = new BigDecimal(100.0);
		zero = new BigDecimal(0.0);
	}

	/**
	 * Creates a DecimalValue from the string value passed in
	 * 
	 * @param value
	 *            - the value to create the DecimalValue from
	 */
	public DecimalValue(String value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * Creates a DecimalValue from the BigDecimal value passed in
	 * 
	 * @param value
	 *            - the value to create the DecimalValue from
	 */
	public DecimalValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * Creates a DecimalValue from the double value passed in
	 * 
	 * @param value
	 *            - the value to create the DecimalValue from
	 */
	public DecimalValue(double value) {
		this.value = new BigDecimal(value);
		this.value = this.value.setScale(SCALE, BigDecimal.ROUND_UP);

	}

	/**
	 * Creates and returns a DecimalValue from the string value passed in. The
	 * new DecimalValue object is returned as a generic Value.
	 * 
	 * @param string
	 *            - the value to create the DecimalValue from
	 * @return a DecimalValue representation of string as a generic Value.
	 */
	public Value create(String string) {
		return new DecimalValue(string);
	}

	/**
	 * Adds the value passed in (i.e. val) to this DecimalValue object and
	 * returns the sum. This DecimalValue object is immutable by this operation.
	 * 
	 * @param val
	 *            - the value to add to this object
	 * 
	 * @return the sum of val and this object
	 */
	public Value add(Value val) {
		if (val instanceof DecimalValue) {
			DecimalValue decimalVal = (DecimalValue) val;

			BigDecimal result = value.add(decimalVal.value);
			return new DecimalValue(result);
		} else {
			System.err
					.println("Arithmetic Error - divisor is not of type DecimalValue");
			return this;
		}
	}

	/**
	 * Subtracts the value passed in (i.e. val) from this DecimalValue object
	 * and returns the result. This DecimalValue object is immutable by this
	 * operation.
	 * 
	 * @param val
	 *            - the value to substract from this object
	 * 
	 * @return this minus val
	 */
	public Value subtract(Value val) {
		if (val instanceof DecimalValue) {
			DecimalValue decimalVal = (DecimalValue) val;

			BigDecimal result = value.subtract(decimalVal.value);
			return new DecimalValue(result);
		} else {
			System.err
					.println("Arithmetic Error - divisor is not of type DecimalValue");
			return this;
		}
	}

	/**
	 * Multiplies the value passed in (i.e. val) with this DecimalValue object
	 * and returns the result. This DecimalValue object is immutable by this
	 * operation.
	 * 
	 * @param val
	 *            - the value to multiply with this object
	 * 
	 * @return this * val
	 */
	public Value multiply(Value val) {
		if (val instanceof DecimalValue) {
			DecimalValue decimalVal = (DecimalValue) val;

			BigDecimal result = value.multiply(decimalVal.value);
			return new DecimalValue(result);
		} else {
			System.err
					.println("Arithmetic Error - divisor is not of type DecimalValue");
			return this;
		}
	}

	/**
	 * Divides this object by the value passed in (i.e. val) and returns the
	 * result. This DecimalValue object is immutable by this operation. If the
	 * divisor passed in is zero, then this object's value is returned.
	 * 
	 * @param val
	 *            - the divisor
	 * 
	 * @return this divided by val
	 */
	public Value divide(Value val) {
		BigDecimal result = null;

		if (val instanceof DecimalValue) {
			DecimalValue decimalVal = (DecimalValue) val;

			// protect from divide by zero
			if (decimalVal.equals(zero)) {
				// in case of divide by zero, report the error and return
				// the current value
				System.err.println("Arithmetic Error - divide by zero");
				return this;
			} else {
				try {
					result = value.divide(decimalVal.value, SCALE,
							BigDecimal.ROUND_UP);
				} catch (ArithmeticException e) {
					// in case of divide by zero, report the error and return
					// the current value
					System.err.println("Arithmetic Error - divide by zero");
					return this;
				}
				return new DecimalValue(result);
			}
		} else {
			System.err
					.println("Arithmetic Error - divisor is not of type DecimalValue");
			return this;
		}
	}

	/**
	 * Returns the square root of this object
	 * 
	 * @return the square root of this object
	 */
	public Value squareRoot() {
		double result = value.doubleValue();
		result = Math.sqrt(result);
		return new DecimalValue(result);
	}

	/**
	 * Returns the inverse of this object. That is, 1 / this DecimalValue
	 * 
	 * @return inverse of this object
	 */
	public Value inverse() {
		BigDecimal result = null;

		// protect from divide by zero
		if (value.equals(zero)) {
			// in case of divide by zero, report the error and return
			// the current value
			System.err.println("Arithmetic Error - divide by zero");
			return this;
		} else {
			try {
				result = one.divide(value, SCALE, BigDecimal.ROUND_UP);
			} catch (ArithmeticException e) {
				// in case of divide by zero, report the error and return
				// the current value
				System.err.println("Arithmetic Error - divide by zero");
				return this;
			}
			return new DecimalValue(result);
		}
	}

	/**
	 * Returns the negative value of this object
	 * 
	 * @return the negative value of this object
	 */
	public Value negate() {
		return new DecimalValue(value.negate());
	}

	/**
	 * Adds a digit to a number.
	 * 
	 * If the number already has a decimal point and the digit to add to the
	 * number is a decimal point, then the request is ignored and the number is
	 * returned.
	 * 
	 * @param number
	 *            - the number to add the digit to
	 * @param digit
	 *            - the digit to add to the number
	 * @return the new value with the added digit
	 */
	public String addDigit(String number, String digit) {
		// is the decimal point is already set?
		boolean decimalSet = (number.indexOf(".") == -1) ? false : true;

		// if the number already has a decimal point, don't add a second decimal
		// point
		if (digit.equals(".") && decimalSet) {
			return number;
		}

		// check for leading zeros
		if (!decimalSet && number.equals("0")) {
			// only one leading zero allowed
			if (digit.equals("0")) {
				return number;
			} else {
				// trim the leading zero
				number = "";
			}
		}

		// append digit to number
		return number + digit;
	}

	/**
	 * Convert the value of this object to a percentage.
	 * 
	 * @return value of this object divided by 100
	 */
	public Value percent() {
		BigDecimal result = value.divide(hundred, SCALE, BigDecimal.ROUND_UP);
		return new DecimalValue(result);
	}

	@Override
	public String toString() {
		String returnStr = value.toString();

		int i;

		/*
		 * If the number does not have a decimal point, then append a decimal
		 * point to the end of the number. If the number does have a decimal
		 * point, then trim the zeros after the decimal point.
		 */
		if (returnStr.indexOf('.') != -1) {
			for (i = returnStr.length(); i > 0; i--) {
				if (returnStr.charAt(i - 1) != '0') {
					break;
				}
			}
			returnStr = returnStr.substring(0, i);
		} else {
			returnStr += ".";
		}

		return returnStr;
	}

	/**
	 * @return the number of digits in the value
	 */
	public int length() {
		return (toString().length());
	}

}
