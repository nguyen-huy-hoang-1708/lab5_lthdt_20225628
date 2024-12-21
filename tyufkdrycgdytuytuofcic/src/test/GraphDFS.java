package test;
import java.util.*;


public class GraphDFS {
	

	
	private LinkedList<Integer>[] adjList; // Adj list
	private boolean[] visited; // mark visited node
	private int n; // number of nodes
	
	// Initialize the graph
	public GraphDFS(int n) {
		this.n = n;
		adjList = new LinkedList[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++)
		{
			adjList[i] = new LinkedList<>();
		}
	}


	public void addEdge(int u, int v)
	{
		adjList[u].add(v);
		adjList[v].add(u);
	}

	public void DFS(int node)
	{
		// mark visited
		visited[node] = true;
		System.out.print(node + " ");
		
		// sort
		Collections.sort(adjList[node]);
		

		for (int neighbor : adjList[node])
		{
			if (!visited[neighbor]) {
				DFS(neighbor);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		GraphDFS graph = new GraphDFS(n);
		
		for (int i = 0; i < m; i++)
		{
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.addEdge(u, v);
		}
		
		graph.DFS(1);
	}

}
