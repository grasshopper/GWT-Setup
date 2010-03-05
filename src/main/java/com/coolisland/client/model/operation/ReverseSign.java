package com.coolisland.client.model.operation;

import com.coolisland.client.model.Value;

public class ReverseSign extends UnaryOperation {

	public ReverseSign() {
		this.setString("-/+");
	}

	@Override
	public Value executeUnary(Value value) {
		return value.negate();
	}
}
