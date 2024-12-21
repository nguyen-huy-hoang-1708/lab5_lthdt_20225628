package tyufkdrycgdytuytuofcic;

import java.util.*;

public class CheckParenthesis {

    public static int isValidExpression(String s) {
      
        int check1 = 0, check2 = 0, check3 = 0;
        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' ) {
                check1++;
            }
            else if (ch == '{')
            {
            	check2++;
            }
            else if (ch == '[')
            {
            	check3++;
            }
            // If it's a closing bracket, check if it matches the last opening bracket
            else if (ch == ')' ) 
            	{
            		if (check1!=0)
            		{
            			check1--;
            		}
            		else
            		{
            			return 0;
            		}
            	}
            else if (ch == '}')
            	{
            		if (check2!=0)
            		{
        			check2--;
            		}
            		else
            		{
        			return 0;
            		}
            	}
            else if (ch == ']') 
        		{
        		if (check3!=0)
        			{
        			check3--;
        			}
        		else
        			{
        			return 0;
        			}
        		}
        }
        
        // If the stack is empty, all brackets are matched correctly
        if (check1 != 0 || check2 != 0 || check3 != 0)
        {
        	return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: a string of brackets
        String input = scanner.nextLine();
        
        // Output: 1 if the sequence is correct, 0 otherwise
        System.out.println(isValidExpression(input));
        
        scanner.close();
    }
}