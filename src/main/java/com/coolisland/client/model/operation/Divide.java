package com.coolisland.client.model.operation;

import com.coolisland.client.model.Value;

public class Divide extends BinaryOperation {

	public Divide() {
		setPrecedence(Precedent.LOW);
		setString("/");
	}

	/**
	 * perform the division on the two values
	 */
	@Override
	public Value executeBinary(Value value1, Value value2) {
		return value1.divide(value2);
	}

}
