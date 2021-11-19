import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class AntOptimization {


	public static void main(String[] args) {
		ArrayList<Point2D> points = Util.randomPointList(100, 100);
		Graph graph = Graph.DistanceGraph(points);

		//double exactScore = NaiveRecursive.solve(graph, 0);
		//System.out.println(exactScore);

		ArrayList<Double> score = new ArrayList<>();

		for (int a = 1; a <= 30; a++)
		{
			double sum = 0;

			for (int j = 0; j < 10; j++) {
				AntColony antColony = new AntColony(graph, 2.5, 5, 1, 0.2, a);
				for (int i = 1; i < 25; i++) {
					antColony.solve();
				}
				sum += antColony.solve();
			}

			score.add(sum/10);
			System.out.print(sum/10 + "\t" + a + "\n");
		}




		System.out.println(Arrays.toString(score.toArray()));




	}

}
