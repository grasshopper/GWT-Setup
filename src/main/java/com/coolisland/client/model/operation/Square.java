package com.coolisland.client.model.operation;

import com.coolisland.client.model.values.Value;

public class Square extends UnaryOperation {

	public Square() {
		this.setString("SQR");
	}

	@Override
	public Value executeUnary(Value value) {
		return value.multiply(value);
	}

}
