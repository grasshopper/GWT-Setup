package com.coolisland.client.commands;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.values.Value;

public class OperandCommand implements Command {

	Value value;

	public OperandCommand(Value value) {
		this.value = value;
	}

	public void execute(Cpu cpu) {
		cpu.loadOperand(value);
	}

	public void display() {
		System.out.println(value.toString());
	}
}
