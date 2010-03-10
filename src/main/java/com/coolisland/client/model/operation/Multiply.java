package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class Multiply extends BinaryOperation {
	public Multiply() {
		super.setString("*");
		super.setPrecedence(Precedent.LOW);
	}

	@Override
	public Value executeBinary(Value value1, Value value2) {
		return value1.multiply(value2);
	}
}
