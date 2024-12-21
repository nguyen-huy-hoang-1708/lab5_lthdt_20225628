package test;

import java.util.*;

public class SumPair {

	public static void main(String[] args) {
		int n, M;
		Stack<Integer> stack = new Stack<>();
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		M = input.nextInt();
		
		input.nextLine();
		for (int i = 0 ; i < n; i++)
		{
			int cmd = input.nextInt();
			stack.push(cmd);
		}
		 int res = 0;
	     Set<Integer> visited = new HashSet<>(); 
	        
	     while (!stack.isEmpty()) {
	            int current = stack.pop();  // Lấy phần tử từ stack
	            int complement = M - current;  // Tìm phần tử cần thiết để tạo ra tổng M
	            
	            // Kiểm tra nếu phần tử cần thiết đã có trong set visited
	            if (visited.contains(complement)) {
	                res++;  // Tìm thấy cặp hợp lệ
	                visited.remove(complement);  // Loại bỏ phần tử đã tìm được cặp
	            } else {
	                visited.add(current);  // Thêm phần tử hiện tại vào tập visited
	            }
	        }
		
		System.out.println(res);
		input.close();
	}

}
