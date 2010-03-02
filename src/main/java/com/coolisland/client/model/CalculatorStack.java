package com.coolisland.client.model;

import java.util.Stack;

public class CalculatorStack<E> {
	// temp storage of our actual type
	private final Class<E> clazz;

	/*
	 * a stack of operands or operations
	 */
	protected Stack<E> stack;

	public CalculatorStack(Class<E> clazz) {
		this.clazz = clazz;
		stack = new Stack<E>();
	}

	public boolean empty() {
		return stack.empty();
	}

	public E pop() {
		return stack.pop();
	}

	public void push(E value) {
		stack.push(value);
	}

	public E peek() {
		return stack.peek();
	}

	public void clear() {
		stack = new Stack<E>();
	}

}
