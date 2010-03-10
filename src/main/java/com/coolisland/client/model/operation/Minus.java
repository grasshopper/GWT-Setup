package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class Minus extends BinaryOperation {

	public Minus() {
		setPrecedence(Precedent.LOW);
		setString("-");
	}

	@Override
	public Value executeBinary(Value value1, Value value2) {
		return value1.subtract(value2);
	}

}
