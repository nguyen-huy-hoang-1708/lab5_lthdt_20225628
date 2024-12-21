package test;

import java.util.Stack;
import java.util.Scanner;

public class SSSTring {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		Scanner input = new Scanner(System.in);
		while(true)
		{
			String command = input.nextLine();
			if (command.equals("*")) {
				break;
			}
			
			stack.push(command);
		}
		while(true)
		{
			String command = input.nextLine();
			if (command.equals("***")) {
				break;
			}
			
			 String[] parts = command.split(" ");
	         String operation = parts[0];

	            switch (operation) {
	                case "insert":
	                	String tempInsert = parts[1];
	                	if (!stack.contains(tempInsert)) 
	                	{
	                		System.out.println(1);
	                		stack.push(tempInsert);
	                	}
	                	else System.out.println(0);
	                    break;

	                case "find":
	                	String tempFind = parts[1];
	                	if (!stack.contains(tempFind)) 
	                	{
	                		System.out.println(0);
	                	}
	                	else System.out.println(1);
	                    break;

	                default:
	                    System.out.println("Invalid command");
	                    break;
		}
		
	}
	

}
}

