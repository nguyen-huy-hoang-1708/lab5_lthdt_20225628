package test;

import java.util.*;

public class FloydWarshallAlgorithm {

	public static void main(String[] args) {
		
		// Scanner
		Scanner scanner = new Scanner(System.in);
		
		// Get Input
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		//Initialize matrix
		
		int INF = 100000000;
		int [][] dist = new int[n][n];
		
		// Fill matrix	
		for (int i = 0; i < n; i++)
		{
			Arrays.fill(dist[i], INF); //Fill every distance with INF
			dist[i][i] = 0; //Distance to itself = 0
		}
		
		// Read the edges
		for (int i = 0; i < m; i++)
		{
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			int w = scanner.nextInt();
			dist[u][v] = w;
		}
		
		// FloydWarshallAlgorithm
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
				{
					if (dist[i][k] < INF && dist[k][j] < INF )
					{
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
		
		 for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (dist[i][j] == INF) {
	                    System.out.print("-1 ");
	                } else {
	                    System.out.print(dist[i][j] + " ");
	                }
	            }
	            System.out.println();
	        }
		 scanner.close();
	}
}
