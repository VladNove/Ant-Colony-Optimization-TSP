import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ExactTest {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Double> naiveTimes = new ArrayList<>();
		ArrayList<Double> nearestTimes = new ArrayList<>();
		ArrayList<Double> nearest2Times = new ArrayList<>();

		ArrayList<Double> nearestErrors = new ArrayList<>();
		ArrayList<Double> nearest2Errors = new ArrayList<>();

		int maxPoints = 12;
		int nrOfTests = 1;

		for (int p = 5; p <= maxPoints; p++) {
			double naiveTime = 0;
			double nearestTime = 0;
			double nearest2Time = 0;

			double naiveScore = 0;
			double nearestScore = 0;
			double nearest2Score = 0;

			long startTime;
			long endTime;

			for (int i = 0; i < nrOfTests; i++) {
				ArrayList<Point2D> points = Util.randomPointList(p, 100);
				startTime = System.currentTimeMillis();
				naiveScore += NaiveRecursive.solve(Graph.DistanceGraph(points), 0);
				naiveTime += (System.currentTimeMillis() - startTime);

				startTime = System.currentTimeMillis();
				nearestScore += NearestNeighbour.solve(Graph.DistanceGraph(points), 0);
				nearestTime += (System.currentTimeMillis() - startTime);

				startTime = System.currentTimeMillis();
				nearest2Score += NearestNeighbour.solveForAll(Graph.DistanceGraph(points));
				nearest2Time += (System.currentTimeMillis() - startTime);
			}

			naiveTime /= nrOfTests;
			naiveScore /= nrOfTests;
			nearestTime /= nrOfTests;
			nearestScore /= nrOfTests;
			nearest2Time /= nrOfTests;
			nearest2Score /= nrOfTests;

			naiveTimes.add(naiveTime);
			nearestTimes.add(nearestTime);
			nearest2Times.add(nearest2Time);
			nearestErrors.add((nearestScore - naiveScore) / naiveScore * 100);
			nearest2Errors.add((nearest2Score - naiveScore) / naiveScore * 100);
		}

		System.out.println(Arrays.toString(naiveTimes.toArray()));
		System.out.println(Arrays.toString(nearestTimes.toArray()));
		System.out.println(Arrays.toString(nearest2Times.toArray()));
		System.out.println(Arrays.toString(nearestErrors.toArray()));
		System.out.println(Arrays.toString(nearest2Errors.toArray()));

	}
}
