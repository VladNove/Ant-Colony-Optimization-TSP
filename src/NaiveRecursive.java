import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class NaiveRecursive {


	public static double bestSolution;
	public static int[] bestPath;
	public static Graph graph;
	

	private static void recursive_backtrack(HashSet<Integer> visited, int[] path, int path_lenght)
	{
		if (path_lenght == graph.size()){
			double sol = Util.evaluateSolution(path, graph);
			if (sol < bestSolution) {
				bestSolution = sol;
				bestPath = Util.cloneArray(path);
			}
		}
		for(int i = 0; i < graph.size(); i++)
		{
			if (!visited.contains(i))
			{
				visited.add(i);
				path[path_lenght] = i;
				recursive_backtrack(visited, path, path_lenght+1);
				visited.remove(i);
			}
		}
	}

	public static double solve(Graph g, int start)
	{
		graph = g;
		HashSet<Integer> visited = new HashSet<>(graph.size());
		int[] path = new int[graph.size()];
		bestSolution =  Double.POSITIVE_INFINITY;
		visited.add(start);
		path[0] = start;
		recursive_backtrack(visited,path,1);
		return bestSolution;

	}



}
