package br.edu.cefsa.ec6.laion.datastructure;

class StackNode<T> {
	private StackNode<T> next;
	private T value;
	
	public StackNode(T value, StackNode<T> next){
		this.value = value;
		this.next = next;
	}

	public StackNode<T> getNext(){
		return next;
	}
	public T getValue(){
		return value;
	}
}
