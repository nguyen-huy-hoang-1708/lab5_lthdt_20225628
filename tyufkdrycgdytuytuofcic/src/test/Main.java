package test;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		var list = new MyLinkedList();
		int n;
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		for (int i = 0; i < n; i++)
		{
			int index = input.nextInt();
			list.addLast(index);
		}
	
		input.nextLine();
		
		while(true)
		{
			String command = input.nextLine();
			if (command.equals("#")) {
				break;
			}
			
			 String[] parts = command.split(" ");
	         String operation = parts[0];

	            switch (operation) {
	                case "addlast":
	                    int valueToAddLast = Integer.parseInt(parts[1]);
	                    if (!list.contains(valueToAddLast)) {
	                        list.addLast(valueToAddLast);
	                    }
	                    break;

	                case "addfirst":
	                    int valueToAddFirst = Integer.parseInt(parts[1]);
	                    if (!list.contains(valueToAddFirst)) {
	                        list.addFirst(valueToAddFirst);
	                    }
	                    break;

	                case "addafter":
	                    int valueToAddAfter = Integer.parseInt(parts[1]);
	                    int afterValue = Integer.parseInt(parts[2]);
	                    if (list.contains(afterValue) && !list.contains(valueToAddAfter)) {
	                        list.addAfter(valueToAddAfter, afterValue);
	                    }
	                    break;

	                case "addbefore":
	                    int valueToAddBefore = Integer.parseInt(parts[1]);
	                    int beforeValue = Integer.parseInt(parts[2]);
	                    if (list.contains(beforeValue) && !list.contains(valueToAddBefore)) {
	                        list.addBefore(valueToAddBefore, beforeValue);
	                    }
	                    break;

	                case "remove":
	                    int valueToRemove = Integer.parseInt(parts[1]);
	                    if (list.contains(valueToRemove)) {
	                        list.remove(valueToRemove);
	                    }
	                    break;

	                case "reverse":
	                    list.reverse();
	                    break;

	                default:
	                    System.out.println("Invalid command");
	                    break;
		}
		
	}
		list.printLinkedList();
	}
	
		
	public static class MyLinkedList {
		private class Node{
			private int value;
			private Node next;
			
			public Node(int value) {
				this.value = value;
			}
		}
		private Node first;
		private Node last;
		
		public void addLast(int item){
			var node = new Node(item);
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
			if (isEmpty()) {
				first = last = node;
			}
			else {
				node.next = first;
				first = node;
			}
		}
		
		public void addAfter(int item1, int item2) {
			var current = first;
			var node = new Node(item1);
			while (current.value != item2)
			{
				current = current.next;
			}

			node.next = current.next;
			current.next = node;
		}
		public void addBefore(int item1, int item2) {
			var current = first;
			var node = new Node(item1);
			while (current.value != item2)
			{
				if (current.next.value == item2)
				{
					break;
				}
				current = current.next;
			}
			
			node.next = current.next;
			current.next = node;
		}
		
		public void remove(int item) {
			if (first.value == item)
			{
				var second = first.next;
		 		first.next = null;
		 		first = second;
		 		return;
			}
			var current = first;
			while(current.next!= null && current.next.value !=item)
			{
				current = current.next;
			}
			if (current.next == last)
			{
				last = current;
				current.next = null;
				return;
			}
			// 4 - 3 - 2
			// 4 - 3 2
			// 4 - 2
			var second = current.next;
			current.next = second.next;
			second.next = null;
		}
		
		public void reverse() {
			Node previous = null;
			var current = first;
			Node next = null;
			while (current != null)
			{
				next = current.next;
				current.next = previous;
				previous = current;
				current = next;
			}
			first = previous;
		}
		
		public boolean contains(int value) {
            var current = first;
            while (current != null) {
                if (current.value == value) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
		
		private boolean isEmpty() {
			return first == null;
		}
		public void printLinkedList()
	 	{
	 		var current = first;
	 		while (current != null)
	 		{
	 			System.out.print(current.value + " ");
	 			current = current.next;
	 		}
	 		System.out.println();
	 	}
	
	}

}
