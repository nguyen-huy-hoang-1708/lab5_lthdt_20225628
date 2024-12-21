package test;

import java.util.*;
import java.io.*;

public class BankTransaction {

	static class Transaction {
		String fromAccount;
		String toAccount;
		int money;
		String timePoint;
		String atm;
		
		public Transaction(String fromAccount, String toAccount, int money, String timePoint, String atm) {
			this.fromAccount = fromAccount;
			this.toAccount = toAccount;
			this.money = money;
			this.timePoint = timePoint;
			this.atm = atm;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Transaction> transactions = new ArrayList<>();
		Set<String> uniqueAccounts = new HashSet<>();
		Map<String, Integer> accountOutflows = new HashMap<>();
		
		Map<String, List<String>> accountGraph = new HashMap<>();
		
		int totalMoney = 0;
		
		String line;
		while(!(line = reader.readLine()).equals("#")) {
			String[] parts = line.split(" ");
			String fromAccount = parts[0];
			String toAccount = parts[1];
			int money = Integer.parseInt(parts[2]);
			String timePoint = parts[3];
			String atm = parts[4];
			
			Transaction transaction = new Transaction(fromAccount, toAccount, money, timePoint, atm);
			transactions.add(transaction);
			
			totalMoney += money;
			
			uniqueAccounts.add(fromAccount);
	        uniqueAccounts.add(toAccount);
	        
	        accountOutflows.put(fromAccount, accountOutflows.getOrDefault(fromAccount, 0) + money);
	        
	        accountGraph.putIfAbsent(fromAccount, new ArrayList<>());
            accountGraph.get(fromAccount).add(toAccount);
			
		}
		
		List<String> sortedAccounts = new ArrayList<>(uniqueAccounts);
        Collections.sort(sortedAccounts);
		
		while(!(line = reader.readLine()).equals("#")) {
			String[] queryParts = line.split(" ");
			String queryType = queryParts[0];
			
			switch (queryType) {
				case "?number_transactions":
					System.out.println(transactions.size());
					break;
				case "?total_money_transaction":
					System.out.println(totalMoney);
					break;
				case "?list_sorted_accounts":
                    System.out.println(String.join(" ", sortedAccounts));
                    break;
				case "?total_money_transaction_from":
                    String account = queryParts[1];
                    System.out.println(accountOutflows.getOrDefault(account, 0));
                    break;
				case "?inspect_cycle":
	                String startAccount = queryParts[1];
	                int cycleLength = Integer.parseInt(queryParts[2]);
	                System.out.println(detectCycle(accountGraph, startAccount, cycleLength) ? 1 : 0);
	                    break;
				default: 
					System.out.println("Unknown query: " + queryType);
			}
		}
	}
	
	private static boolean detectCycle(Map<String, List<String>> graph, String start, int length) {
	        return dfs(graph, start, start, length, new HashSet<>());
	    }

	    private static boolean dfs(Map<String, List<String>> graph, String start, String current, int remaining, Set<String> visited) {
	        if (remaining == 0) {
	            return current.equals(start);
	        }
	        if (visited.contains(current) || !graph.containsKey(current)) {
	            return false;
	        }
	        
	        visited.add(current);
	        
	        for (String next : graph.get(current)) {
	            if (dfs(graph, start, next, remaining - 1, visited)) {
	                return true;
	            }
	        }
	        
	        visited.remove(current); // Backtrack
	        return false;
	    }	
		
		
}
