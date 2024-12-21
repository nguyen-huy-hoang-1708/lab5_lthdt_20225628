package test;
import java.util.*;


public class GraphBFS {
	
	// khởi tạo linkedlist
	
	private LinkedList<Integer>[] adjList; // Adj list
	private boolean[] visited; // mark visited node
	private int n; // number of nodes
	
	// Initialize the graph
	public GraphBFS(int n) {
		this.n = n;
		adjList = new LinkedList[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++)
		{
			adjList[i] = new LinkedList<>();
		}
	}

	// update con trỏ
	public void addEdge(int u, int v)
	{
		adjList[u].add(v);
		adjList[v].add(u);
	}
	
	// duyệt (luôn luôn sắp xếp)
	public void BFS(int startNode)
	{
		// hàng đợi
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		visited[startNode] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			System.out.print(node + " ");
			
			Collections.sort(adjList[node]);
			for (int neighbor : adjList[node])
			{
				if (!visited[neighbor])
				{
					queue.add(neighbor);
					visited[neighbor] = true;
				}
			}
		}
		
		
	}
	
	public void traverseGraph() {
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                BFS(i);
            }
        }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		GraphBFS graph = new GraphBFS(n);
		
		for (int i = 0; i < m; i++)
		{
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.addEdge(u, v);
		}
		
		 graph.traverseGraph();
	}

}
