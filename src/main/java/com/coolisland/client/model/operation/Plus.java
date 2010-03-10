package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class Plus extends BinaryOperation {

	public Plus() {
		super.setPrecedence(Precedent.LOW);
		super.setString("+");
	}

	@Override
	/**
	 * add two values
	 */
	public Value executeBinary(Value value1, Value value2) {
		return value1.add(value2);
	}

}
