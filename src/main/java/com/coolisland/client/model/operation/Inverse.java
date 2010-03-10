package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class Inverse extends UnaryOperation {

	public Inverse() {
		super.setString("1/x");
	}

	@Override
	public Value executeUnary(Value value) {
		return value.inverse();
	}

}
