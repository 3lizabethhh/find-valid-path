/**Dongzheng (Elizabeth)Xu
 * This class creates a stack.
 * 
 * Last edited: February 28 2019
 **/
public class ArrayStack<T> implements ArrayStackADT<T> {

	private T[] stack;
	private int top;
	private int DEFAULT_CAPACITY=20;

	/**
	 * Constructor that makes an array stack
	 * 
	 **/
	public ArrayStack() {
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
		top=-1; //sets stack to empty
	}
	/**
	 * Constructor that makes an array stack with given initial capacity 
	 * @param initial capacity
	 **/
	public ArrayStack (int initialCapacity) {

		stack = (T[]) (new Object[initialCapacity]);
		top=-1;		//sets stack to empty
	}

	/**
	 * Accessor method that gets length/size of entire stack
	 * @return size of stack
	 **/
	public int length() {
		return stack.length;
	}

	/**
	 * Method that adds item to stack
	 * @param data item to be added
	 * @return size of stack
	 **/
	@Override
	public void push(T dataItem) {		
		if(size() == stack.length) {//if stack is full expand capacity
			expandCapacity();
		}
		top++;
		stack[top]=dataItem;

	}

	/**
	 * Method that increases size of entire stack
	 **/
	public void expandCapacity() {
		if(stack.length<100) { //increase size by factor 2 if less than 100 size
			T[]larger =(T[])new Object[stack.length*2];

			//copy values from original stack to new stack
			for (int i=0;i<size();i++) {
				larger[i]=stack[i];
			}
			stack=larger;//set stack variable to reference the new larger stack
		}
		else {
			T[]larger =(T[])(new Object[stack.length+50]);//increase size by 50 if more than 100

			//copy values from original stack to new stack
			for (int i=0;i<size();i++) {
				larger[i]=stack[i];
			}
			stack=larger;//set stack variable to reference the new larger stack
		}
	}

	/**
	 * Method that pops item from stack
	 * @return popped item
	 **/
	@Override
	public T pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Stack");

		T result= stack[top]; // set temporary to hold top variable
		top--; //decrease index counter

		if(top<(stack.length/3)) {// if there are less than 1/3 items in stack 
			shrinkCapacity();
		}

		return result;
	}

	/**
	 * Method that shrinks size of entire stack
	 * 
	 **/
	public void shrinkCapacity() {
		T[]smaller =(T[])(new Object[(stack.length/2)]);//creates a smaller sized stack by factor 2

		for (int i=0;i<size();i++) {//copy the values of original stack to new smaller stack
			smaller[i]=stack[i];
		}
		stack=smaller;
	}

	/**
	 * Method that looks at top value
	 * @return item on top
	 **/
	@Override
	public T peek() throws EmptyStackException {
		return stack[top];
	}

	/**
	 * Method that checks if stack is empty
	 * @return true if empty
	 * @return false if not empty
	 **/
	@Override
	public boolean isEmpty() {
		if (top==-1) 
			return true;
		return false;
	}
	
	/**
	 * Method finds number of items in stack
	 * @return number of items in stack
	 **/
	@Override
	public int size() {
		return top+1;
	}

	String result = "Stack:";
	
	/**
	 * Method prints out stack
	 * @return String that is to be printed
	 **/
	public String toString() {
		for(int i = 0 ; i <=top; i++) {
			if(i == top)
				result = result +" "+stack[i].toString();
			else 
				result = result + " "+stack[i].toString()+",";

		}
		return result;
	}

}