package com.coolisland.client.commands;

import com.coolisland.client.model.Cpu;

public interface Command {

	public void execute(Cpu cpu);

	public void display();
}
