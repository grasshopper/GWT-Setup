package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class SquareRoot extends UnaryOperation {

	public SquareRoot() {
		this.setString("SQRT");
	}

	@Override
	public Value executeUnary(Value value) {
		return value.squareRoot();
	}
}
