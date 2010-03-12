package com.coolisland.client.model;

import java.util.HashMap;

import com.coolisland.client.commands.OperandCommand;
import com.coolisland.client.commands.OperationCommand;
import com.coolisland.client.java.utils.Observable;
import com.coolisland.client.java.utils.Observer;
import com.coolisland.client.model.operation.Memory;
import com.coolisland.client.model.operation.Operation;
import com.coolisland.client.model.operation.Operation.Precedent;
import com.coolisland.client.model.stack.OperandStack;
import com.coolisland.client.model.stack.OperationStack;
import com.coolisland.client.model.state.EnteringNumberState;
import com.coolisland.client.model.state.State;
import com.coolisland.client.model.state.WaitingForInputState;
import com.coolisland.client.model.state.WaitingForNumberState;
import com.coolisland.client.model.state.WaitingForOperationState;
import com.coolisland.client.model.values.IntegerValue;
import com.coolisland.client.model.values.Value;

public class Cpu extends Observable {
	// a reference to the calculator memory
	private final Memory memory;

	// a reference to the current state of the calculator - e.g. user is
	// entering digits
	private State state;

	// the waiting for a number state
	public State waitingForNumberState = null;

	// the waiting for an operation state
	public State waitingForOperationState = null;

	// the waiting for an input state
	public State waitingForInputState = null;

	// the entering number state object
	public State enteringNumberState;

	// a reference to the calculator display
	private final Display display;

	//
	boolean displayUpdate;

	Display calculatorDisplay = null;

	// a stack of operands
	private OperandStack operandStack = null;

	// a stack of operations
	private final OperationStack operationStack;

	// map of operations
	private final HashMap<String, Operation> operationMap;

	/**
	 * 
	 */
	public Cpu() {
		Value initialValue = new IntegerValue(0);
		operandStack = new OperandStack();
		operationStack = new OperationStack();
		memory = new Memory(initialValue);
		display = new Display(initialValue);
		displayUpdate = false;
		operationMap = new HashMap<String, Operation>();
		initializeStates();

		// set the initial state
		loadOperand(initialValue);
		setState(waitingForInputState);
	}

	/**
	 * initialize all the state class members
	 */
	private void initializeStates() {
		waitingForNumberState = new WaitingForInputState(this);
		waitingForNumberState = new WaitingForNumberState(this);
		waitingForOperationState = new WaitingForOperationState(this);
		enteringNumberState = new EnteringNumberState(this);
	}

	/**
	 * updates the state of the CPU to the new state.
	 * 
	 * @param state
	 *            - the new state for the cpu
	 */
	private void setState(State state) {
		boolean viewStateChange = false;

		if (!this.state.equals(state)) {
			viewStateChange = true;
		}

		this.state = state;

		if (viewStateChange) {
			String name = state.getClass().getName();
			System.out.println("State: " + name);
		}
	}

	/**
	 * 
	 * @param opcode
	 */
	public void enterOperation(String opcode) {
		// create a class for the operation
		Operation op = findOperation(opcode);

		// depending on the current state, do operation, set new state
		if (opcode.compareTo("MemoryRecall") == 0) {
			// memory recall is an operation but behaves like a number
			// so handle it as entering a value
			setState(state.enterValue(op));
		} else {
			setState(state.enterOperation(op));
		}
	}

	/**
	 * Enter/append digits. The CPU uses its current state to determine what to
	 * do with the number.
	 */
	public void enterDigit(String digit) {
		// depending on the current state, handle new digit, set new state.
		setState(state.enterDigit(digit));
	}

	/**
	 * 
	 * @param operation
	 * @return
	 */
	private Operation findOperation(String operation) {
		String model = "com.objectsbydesign.calc.model";
		Operation op = null;
		// FIXME: java.util.reflect.Constructor does not appear to be supported
		// by GWT
		// Constructor<?> constructor = null;
		//
		// try {
		// // check the operation cache first
		// op = operationMap.get(operation);
		// if (op == null) {
		// // create an instance of the operation
		// Class<?> klass = Class.forName(model + "." + operation);
		// constructor = klass.getDeclaredConstructor(null);
		// op = (Operation) constructor.newInstance(null);
		// operationMap.put(operation, op);
		// }
		// } catch (Exception ex) {
		// System.out.println(ex.toString());
		// }

		return op;
	}

	/**
	 * returns the display register
	 * 
	 * @return the display register
	 */
	public Display getDisplayRegister() {
		return calculatorDisplay;
	}

	/**
	 * Replace the top operation on the operation stack.
	 * 
	 * @param op
	 *            - the new operation
	 */
	public void replaceOperation(Operation op) {
		if (!operationStack.empty()) {
			operationStack.pop();
			pushOperation(op);
		}
	}

	/**
	 * returns the stack of operands
	 * 
	 * @return stack of operands
	 */
	public OperandStack getOperandStack() {
		return operandStack;
	}

	public void setUpdateDisplay() {
		displayUpdate = true;
	}

	/*
	 * returns the Memory Register
	 */
	public Memory getMemoryRegister() {
		return memory;
	}

	/**
	 * Push the display input onto the operand stack, and broadcasts the input
	 * to all observers
	 */
	public void pushDisplayRegister() {
		Value value = display.createValue();
		operandStack.push(value);
		doNotify(new OperandCommand(value));
	}

	/**
	 * Push the operand value onto the operand stack.
	 */
	public void loadOperand(Value value) {
		operandStack.push(value);
	}

	/**
	 * Push the operation onto the operation stack or execute immediately, and
	 * broadcast the input to all observers
	 */
	public void pushOperation(Operation op) {

		/*
		 * Ccompare precedence of new operation to the top of the stack (if not
		 * empty). If the new operation has equal or higher precedence, execute
		 * the operation from the stack
		 */
		if (!operationStack.empty()) {
			Precedent topOperandStackPrecedent = operationStack.peek()
					.getPrecedence();
			Precedent opPrecedent = op.getPrecedence();

			if (topOperandStackPrecedent.compareTo(opPrecedent) >= 0) {
				operationStack.pop().execute(this);
			}
		}

		// if the operation has no lookahead
		if (!op.getLookahead()) {
			// execute immediately
			op.execute(this);
		} else {
			// if the operation has a lookahead
			// push operation for later execution
			operationStack.push(op);
		}

		doNotify(new OperationCommand(op));
		updateDisplay();
	}

	/**
	 * Execute the operation and broadcast the input to all observers
	 */
	public void executeOperation(Operation op) {
		op.execute(this);
		doNotify(new OperationCommand(op));
	}

	/**
	 * Execute the equals function.
	 */
	public void equals() {
		while (!operationStack.empty()) {
			operationStack.pop().execute(this);
		}
	}

	/**
	 * Set the display register with any calculated results
	 */
	public void updateDisplay() {
		if (displayUpdate && !operandStack.empty()) {
			Value value = operandStack.peek();
			display.setValue(value);
			doNotify(new OperandCommand(value));
			displayUpdate = false;
		}
	}

	/**
	 * Executes the clear function. Empties the stacks and clears the display
	 * register.
	 */
	public void clear() {
		operandStack.clear();
		operationStack.clear();
		display.clear();
	}

	/**
	 * Resets the CPU. Empties the stacks and resets the display register.
	 */
	public void reset() {
		operandStack.clear();
		operationStack.clear();
		display.reset();
	}

	public void addDisplayObserver(Observer observer) {
		display.addObserver(observer);

	}

	public void addMemoryObserver(Observer observer) {
		memory.addObserver(observer);
	}

	/**
	 * Notify all observers of an input to the CPU
	 */
	private void doNotify(Object object) {
		setChanged();
		notifyObservers(object);
	}

	public OperationStack getOperationStack() {
		return operationStack;
	}

}
