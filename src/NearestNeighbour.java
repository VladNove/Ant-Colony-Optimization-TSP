import java.util.HashSet;

public class NearestNeighbour {

	public static double bestSolution;
	public static int[] bestPath;
	public static Graph graph;

	public static int closestUnvisited(HashSet<Integer> visited, int o)
	{
		int min = -1;
		for (int i = 0; i< graph.size(); i++)
		{
			if (!visited.contains(i))
				if (min == -1 || graph.getEdge(o,i) < graph.getEdge(o, min))
					min = i;
		}
		return min;
	}

	public static double solve(Graph g, int start)
	{
		graph = g;
		HashSet<Integer> visited = new HashSet<>(graph.size());
		int[] path = new int[graph.size()];
		bestSolution =  Double.POSITIVE_INFINITY;
		visited.add(start);
		path[0] = start;
		for (int i = 1; i < graph.size(); i++)
		{
			path[i] = closestUnvisited(visited, path[i-1]);
			visited.add(path[i]);
		}
		bestPath = Util.cloneArray(path);
		bestSolution = Util.evaluateSolution(path, graph);
		return bestSolution;
	}

	public static double solveForAll(Graph g)
	{
		graph = g;
		double betterSolution = Double.POSITIVE_INFINITY;
		int[] betterPath = new int[0];
		for (int i = 0 ; i < graph.size(); i++)
		{
			if (solve(g, i) < betterSolution) {
				betterSolution = bestSolution;
				betterPath = Util.cloneArray(bestPath);
			}

		}
		bestPath = Util.cloneArray(betterPath);
		bestSolution = betterSolution;

		return bestSolution;
	}


}
