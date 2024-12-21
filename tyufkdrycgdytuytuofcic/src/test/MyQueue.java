package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Scanner input = new Scanner(System.in);
		while(true)
		{
			String command = input.nextLine();
			if (command.equals("#")) {
				break;
			}
			
			 String[] parts = command.split(" ");
	         String operation = parts[0];

	            switch (operation) {
	                case "PUSH":
	                	int value = Integer.parseInt(parts[1]);
	                    queue.offer(value);
	                    break;

	                case "POP":
	                	if (queue.isEmpty())
	                	{
	                		System.out.println("NULL");
	                	}
	                	else System.out.println(queue.poll());
	                    break;

	                default:
	                    System.out.println("Invalid command");
	                    break;
		}
		
	}
	

}
}

