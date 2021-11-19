import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class AntTest {


	public static void main(String[] args) {
		ArrayList<Point2D> points = Util.randomPointList(100, 100);
		Graph graph = Graph.DistanceGraph(points);

		double startTime;
		startTime = System.currentTimeMillis();
		double nearScore = NearestNeighbour.solve(graph, 0);
		System.out.println(nearScore);
		System.out.println(System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		double improvedNearScore = NearestNeighbour.solveForAll(graph);
		System.out.println(improvedNearScore);
		System.out.println(System.currentTimeMillis() - startTime);



		ArrayList<Double> antcolonyScore = new ArrayList<>();
		ArrayList<Double> antcolonyTime = new ArrayList<>();
		AntColony antColony = new AntColony(graph,2.5, 5, 1, 0.2, 10);

		startTime = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			antcolonyScore.add(antColony.solve());
			antcolonyTime.add(System.currentTimeMillis()-startTime);
		}




		System.out.println(Arrays.toString(antcolonyScore.toArray()));
		System.out.println(Arrays.toString(antcolonyTime.toArray()));




	}

}
