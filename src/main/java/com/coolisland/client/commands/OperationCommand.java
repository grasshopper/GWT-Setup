package com.coolisland.client.commands;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.operation.Operation;

public class OperationCommand implements Command {
	Operation op;

	public OperationCommand(Operation op) {
		this.op = op;
	}

	public void execute(Cpu cpu) {
		cpu.pushOperation(op);
	}

	public void display() {
		System.out.println(op.toString());
	}

}
