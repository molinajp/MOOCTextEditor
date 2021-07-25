package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if(element == null){
			throw new NullPointerException();
		}
		if(size == 0){
			LLNode<E> node = new LLNode<E>(element);
			node.next = tail;
			node.prev = head;
			head.next = node;
			tail.prev = node;
			size = 1;
			return true;
		}
		else{
			LLNode<E> node = new LLNode<E>(element);
			node.next = tail;
			node.prev = tail.prev;
			tail.prev.next = node;
			tail.prev = node;
			size++;
			return true;
		}
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index >= size || size == 0){
			throw new IndexOutOfBoundsException();
		}
		else{
			int counter = 0;
			LLNode<E> element = head;
			while(element != null){
				element = element.next;
				if(index == counter){
					return element.data;
				}
				else{
					counter++;
				}
			}
			return null;
		 }
	}
	
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException();
		}
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> node = new LLNode<E>(element);
		if(index == size){
			node.next = tail;
			node.prev = tail.prev;
			tail.prev.next = node;
			tail.prev = node;
			size += 1;
		}
		else if(index == 0){
			node.prev = head;
			node.next = head.next;
			head.next.prev = node;
			head.next = node;
			size += 1;
		}
		else{
			LLNode<E> before = getNode(index-1);
			LLNode<E> after = getNode(index);
			node.next = before.next;
			node.prev = after.prev;
			before.next = node;
			after.prev = node;
			size += 1;
		}
	}
	private LLNode<E> getNode(int index){
		LLNode<E> answer = head.next;
		for(int k=0;k<this.size();k++){
			if(index == k){
				return answer;
			}
			else{
				answer = answer.next;
			}
		}
		return null;
	}
	public static void main (String[] args){
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0,1);
		int a = list.remove(0);
		list.add(0,1);
		System.out.println(list.get(1));
		
	}

	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index>=size || index<0){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> deleting = getNode(index);
		LLNode<E> before = deleting.prev;
		LLNode<E> after = deleting.next;
		before.next = deleting.next;
		after.prev = deleting.prev;
		size = size - 1;
		return deleting.data;
				
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException();
		}
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> current = getNode(index);
		E replacedData = current.data;
		current.data = element;
		return replacedData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
