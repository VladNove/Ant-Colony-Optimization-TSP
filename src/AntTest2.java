import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class AntTest2 {

	public static void main(String[] args) {

		int samples = 10;
		int maxsteps = 20;

		double nearScore = 0, improvedNearScore = 0, exactScore = 0;
		double[] antScore = new double[maxsteps];
		double[] antTime = new double[maxsteps];


		{
			ArrayList<Point2D> points = Util.randomPointList(11, 100);


			Graph graph = Graph.DistanceGraph(points);
			double starTime = System.currentTimeMillis();
			NaiveRecursive.solve(graph, 0);
			System.out.println("exact sol time = " + (System.currentTimeMillis() - starTime));

		}


		for (int k = 0; k < samples; k++) {
			ArrayList<Point2D> points = Util.randomPointList(11, 100);


			Graph graph = Graph.DistanceGraph(points);

			double startTime;
			exactScore += NaiveRecursive.solve(graph, 0);

			nearScore += NearestNeighbour.solve(graph, 0);

			improvedNearScore += NearestNeighbour.solveForAll(graph);


			AntColony antColony = new AntColony(graph, 2, 5, 1, 0.2, 10);

			startTime = System.currentTimeMillis();
			for (int i = 0; i < maxsteps; i++) {
				antScore[i] += antColony.solve();
				antTime[i] += System.currentTimeMillis() - startTime;
			}

		}

		exactScore/=samples;

		for (int i = 0; i < maxsteps; i++) {
			antScore[i] = (antScore[i]/samples-exactScore)*100/exactScore;
			antTime[i] /= samples;
		}
		nearScore = (nearScore/samples-exactScore)*100/exactScore;
		improvedNearScore = (improvedNearScore/samples-exactScore)*100/exactScore;

		System.out.println("nearT=[" + 0 + "," + antTime[maxsteps-1] + "];");
		System.out.println("nearScore=[" + nearScore + "," + nearScore + "];");
		System.out.println("nearT2=[" + 0 + "," + antTime[maxsteps-1] + "];");
		System.out.println("nearScore2=[" + improvedNearScore  + "," + improvedNearScore + "];");

		System.out.println("antScore=" + Arrays.toString(antScore) + ";");
		System.out.println("antTime=" + Arrays.toString(antTime) + ";");
		System.out.println("plot (nearT, nearScore, nearT2, nearScore2, antTime, antScore)");
	}
}
