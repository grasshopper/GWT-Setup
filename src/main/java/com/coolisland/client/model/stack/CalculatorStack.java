package com.coolisland.client.model.stack;

import java.util.Stack;

public class CalculatorStack<T> {
	// temp storage of our actual type
	private final Class<T> clazz;

	/*
	 * a stack of operands or operations
	 */
	protected Stack<T> stack;

	public CalculatorStack(Class<T> clazz) {
		this.clazz = clazz;
		stack = new Stack<T>();
	}

	public boolean empty() {
		return stack.empty();
	}

	public T pop() {
		return stack.pop();
	}

	public void push(T value) {
		stack.push(value);
	}

	public T peek() {
		return stack.peek();
	}

	public void clear() {
		stack = new Stack<T>();
	}

}
