package tyufkdrycgdytuytuofcic;

import java.util.NoSuchElementException;

public class MyLinkedList {
	private class Node {
		private int value;
		private Node next;
		
		public Node(int value) {
			this.value = value;
		}
	}
	private Node first; //head
	private Node last;  //tail
	
	public void addLast(int item) {
		var node = new Node(item);
		node.value = item;
		
		if (isEmpty())
		{
			first = last = node;
		}
		else
		{
			last.next = node;
			last = node;
		}
	}
	public void addFirst(int item) {
		var node = new Node(item);
		node.value = item;
		
		if (isEmpty())
		{
			first = last = node;
		}
		else
		{
			node.next = first;
			first = node;
		}
	}
	
	public int indexOf(int item) {
		int index = 0;
		var current = first;
		while (current != null)
		{
			if (current.value == item) return index;
			current = current.next;
			index++;
		}
		return -1;
	}
 	private boolean isEmpty() {
		return first == null;
	}
 	
 	public boolean contains(int item)
 	{
 		return indexOf(item) != -1;
 	}
 	
 	public void removeFirst() {
 		if (isEmpty())
 	 	{
 	 		throw new NoSuchElementException();
 	 	}
 		
 		var second = first.next;
 		first.next = null;
 		first = second;
 	}
 	
 	public void printLinkedList()
 	{
 		var current = first;
 		while (current != null)
 		{
 			System.out.println(current.value);
 			current = current.next;
 		}
 	}
}

