package test;

import java.util.Stack;
import java.util.Scanner;

public class MyStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
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
	                    stack.push(value);
	                    break;

	                case "POP":
	                	if (stack.isEmpty())
	                	{
	                		System.out.println("NULL");
	                	}
	                	else System.out.println(stack.pop());
	                    break;

	                default:
	                    System.out.println("Invalid command");
	                    break;
		}
		
	}
	

}
}

