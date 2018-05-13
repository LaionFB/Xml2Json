package br.edu.cefsa.ec6.laion.datastructure;

public class Stack<T> {
	private StackNode<T> top;
	
	public Stack(){
		this.top = null;
	}
	
	public T see(){		
		return this.top.getValue();
	}
	public T pop(){
		if(this.top == null)
			throw new EmptyStackException("The stack is empty and cannot pop an entry.");

		StackNode<T> ret = this.top;
		this.top = this.top.getNext();
		return ret.getValue();
	}
	public void put(T value){
		this.top = new StackNode<T>(value, this.top);
	}
	public boolean isEmpty(){
		return this.top == null;
	}
}
