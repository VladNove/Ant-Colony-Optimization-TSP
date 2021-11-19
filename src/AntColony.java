import java.util.*;

public class AntColony {

	protected Graph graph;
	protected Graph feromoneGraph;
	protected double alpha;
	protected double beta;
	protected double feromoneQ;
	protected double disipation;
	protected int numberOfAnts;
	double bestPathLength;
	ArrayList<Integer> bestPath;
	List<Ant> ants;

	protected double dezirabilitate(int i, int j) {
		return Math.pow(feromoneGraph.getEdge(i,j),alpha)*Math.pow(1/graph.getEdge(i,j),beta);
	}


	class Ant {
		int position;
		HashSet<Integer> nonvisited;
		ArrayList<Integer> path;
		double pathLenght;

		private int chooseNextNode() {
				List<Integer> noduri = nonvisited.stream().toList();
				double suma_probabilitati = 0;
				List<Double> distributie_cumulativa = new ArrayList<>();
				for (int nod : noduri)
				{
					suma_probabilitati += dezirabilitate(position, nod);
					distributie_cumulativa.add(suma_probabilitati);
				}
				double randomValue = Util.randomDouble(0,suma_probabilitati);
				int i = 0;
				while (distributie_cumulativa.get(i) < randomValue)
					i++;
				return noduri.get(i);
		}

		void goToNextNode() {
			if (nonvisited.isEmpty())
			{
				path.add(path.get(0));
				pathLenght += graph.getEdge(path.get(0), position);
				return;
			}

			position = chooseNextNode();
			pathLenght += graph.getEdge(path.get(path.size()-1), position);
			path.add(position);
			nonvisited.remove(position);

		}

		Ant(int startPos)
		{
			nonvisited = new HashSet<>(graph.size());
			path = new ArrayList<>();
			for (int i = 0; i < graph.size(); i++)
				nonvisited.add(i);
			nonvisited.remove(startPos);
			path.add(startPos);
			pathLenght = 0;
			position = startPos;
		}
	}

	public AntColony(Graph graph, double alpha, double beta, double feromoneQ, double disipation, int numberOfAnts) {
		this.graph = graph;
		this.alpha = alpha;
		this.beta = beta;
		this.feromoneQ = feromoneQ;
		this.disipation = disipation;
		this.numberOfAnts = numberOfAnts;
		this.feromoneGraph = new Graph(graph.size());
		feromoneGraph.setAll(1);
		bestPathLength = Double.POSITIVE_INFINITY;
	}

	public double solve()
	{
			ants = new ArrayList<>();
			for (int i = 0; i < numberOfAnts; i++)
				ants.add(new Ant(Util.randomInt(graph.size())));

			for (int i = 0; i < graph.size(); i++) {
				for (Ant ant : ants) {
					ant.goToNextNode();
				}
			}

			for (int i = 0; i < graph.size(); i++)
				for (int j = i+1; j < graph.size(); j++)
					feromoneGraph.setEdge(i, j, feromoneGraph.getEdge(i,j) * (1-disipation));

			for (Ant ant : ants) {
				if (ant.pathLenght < bestPathLength)
				{
					bestPathLength = ant.pathLenght;
					//System.out.println(Arrays.toString(ant.path.toArray()));
					bestPath = new ArrayList<>(ant.path);
				}
				for (int i = 0; i < graph.size(); i++) {
					int a=ant.path.get(i);
					int b=ant.path.get(i+1);
					feromoneGraph.setEdge(a,b,feromoneGraph.getEdge(a,b) + feromoneQ / ant.pathLenght);
				}
			}



			return bestPathLength;


	}
}
