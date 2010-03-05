package com.coolisland.client.model.operation;

import com.coolisland.client.model.Cpu;
import com.coolisland.client.model.stack.OperandStack;

public abstract class Operation {

	/**
	 * The precedence for operations. For example, unary operations have a
	 * higher precedence than binary operations.
	 * 
	 * Unary operators have a High precedent.
	 * 
	 * Binary operators like +, -, *, / have a Low precedent.
	 * 
	 * Memory recall has the Highest precedent.
	 * 
	 * Memory clear, minus, and plus operations have the Lowest precedent.
	 * 
	 * @author Silvio
	 */
	public static enum Precedent {
		HIGHEST(5), HIGH(4), MEDIUM(3), LOW(2), LOWEST(1);

		private final int precedent;

		private Precedent(int precedent) {
			this.precedent = precedent;
		}

		public int getCode() {
			return precedent;
		}
	}

	private Precedent precedence;

	// do we need to look ahead?
	private boolean lookahead;

	// the symbol for the operand
	private String symbol;

	// Central Processor
	protected Cpu cpu;

	// Stack of Operands
	protected OperandStack stack;

	/**
	 * Returns the precedent of this operation.
	 * 
	 * @return the operation's precedent
	 */
	public Precedent getPrecedence() {
		return precedence;
	}

	protected void setPrecedence(Precedent newPrecedent) {
		precedence = newPrecedent;
	}

	/**
	 * Does the operation require looking ahead?
	 * 
	 * @return whether the operation needs to look ahead to the next operand
	 */
	public void setLookahead(boolean lookahead) {
		this.lookahead = lookahead;
	}

	/**
	 * Does the operation require two operands?
	 * 
	 * @return true if the operation needs two operands false otherwise
	 */
	public boolean getLookahead() {
		return lookahead;
	}

	/**
	 * returns the symbol for the operand
	 */
	@Override
	public String toString() {
		return symbol;
	}

	/**
	 * sets the symbol to use for the operand
	 * 
	 * @param s
	 *            - the symbol that represents the operand
	 */
	public void setString(String s) {
		symbol = s;
	}

	/**
	 * The execute method signature which must be implemented for each
	 * operation.
	 */
	public abstract void execute(Cpu cpu);

}
