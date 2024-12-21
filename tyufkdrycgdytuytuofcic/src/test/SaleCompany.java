package test;

import java.io.*;
import java.util.*;

public class SaleCompany {

	static class SaleOrder {
		String CustomerID;
		String ProductID;
		int Price;
		String ShopID;
		int TimePoint;
		
		public SaleOrder(String CustomerID, String ProductID, int Price, String ShopID, String TimePoint) {
			this.CustomerID = CustomerID;
			this.ProductID = ProductID;
			this.Price = Price;
			this.ShopID = ShopID;
			this.TimePoint = timeToSeconds(TimePoint);
		}
		
		private static int timeToSeconds(String time) {
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			int seconds = Integer.parseInt(parts[2]);
			return hours * 3600 + minutes * 60 + seconds;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<SaleOrder> saleOrders = new ArrayList<>();
		Map<String, Integer> shopRevenue = new HashMap<>();
		Map<String, Map<String, Integer>> customerShopRevenue = new HashMap<>();
		TreeMap<Integer, Integer> timeOrderedSales = new TreeMap<>();
		
		int totalRevenue = 0;
		
		String line;
		while (!(line = reader.readLine()).equals("#")) {
			String[] parts = line.split(" ");
			String customerID = parts[0];
			String productID = parts[1];
			int price = Integer.parseInt(parts[2]);
			String shopID = parts[3];
			String timePoint = parts[4];
			
			SaleOrder order = new SaleOrder(customerID, productID, price, shopID, timePoint);
			saleOrders.add(order);
			
			totalRevenue += price;
			
			shopRevenue.put(shopID, shopRevenue.getOrDefault(shopID, 0) + price);
			
			customerShopRevenue.putIfAbsent(customerID, new HashMap<>());
			Map<String, Integer> shopMap = customerShopRevenue.get(customerID);
			shopMap.put(shopID, shopMap.getOrDefault(shopID, 0) + price);
			
			timeOrderedSales.put(order.TimePoint, timeOrderedSales.getOrDefault(order.TimePoint, 0) + order.Price);
		}
		
		TreeMap<Integer, Integer> prefixSum = new TreeMap<>();
		int runningSum = 0;
		for (Map.Entry<Integer, Integer> entry : timeOrderedSales.entrySet()) {
            runningSum += entry.getValue();
            prefixSum.put(entry.getKey(), runningSum);
        }
		
		
		while (!(line = reader.readLine()).equals("#")) {
			String[] queryParts = line.split(" ");
			String queryType = queryParts[0];
			
			switch(queryType) {
				case "?total_number_orders":
					System.out.println(saleOrders.size());
					break;
				case "?total_revenue":
					System.out.println(totalRevenue);
					break;
				case "?revenue_of_shop":
					String shopID = queryParts[1];
					System.out.println(shopRevenue.getOrDefault(shopID, 0));
					break;
				case "?total_consume_of_customer_shop":
					String customerID = queryParts[1];
					shopID = queryParts[2];
					System.out.println(customerShopRevenue.getOrDefault(customerID, new HashMap<>()).getOrDefault(shopID, 0));
					break;
				case "?total_revenue_in_period":
					int fromTime = SaleOrder.timeToSeconds(queryParts[1]);
					int toTime = SaleOrder.timeToSeconds(queryParts[2]);
					int revenueInPeriod = 0;
					Integer sumToTime = prefixSum.floorEntry(toTime) != null ? prefixSum.floorEntry(toTime).getValue() : 0;
                    Integer sumFromTime = prefixSum.floorEntry(fromTime - 1) != null ? prefixSum.floorEntry(fromTime - 1).getValue() : 0;

                    revenueInPeriod = sumToTime - sumFromTime;
					System.out.println(revenueInPeriod);
					break;
				 default:
	                    System.out.println("Unknown query: " + queryType);
			}
					
		}
	
	}

}
