import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class NearestTest {
	public static void main(String[] args) {

		ArrayList<Double> nearestTimes = new ArrayList<>();
		ArrayList<Double> nearest2Times = new ArrayList<>();
		ArrayList<Double> antTimes = new ArrayList<>();

		ArrayList<Double> nearest2Imp = new ArrayList<>();
		ArrayList<Double> antImp = new ArrayList<>();
		ArrayList<Double> antImp2 = new ArrayList<>();
		int maxPoints = 60;
		int nrOfTests = 50;

		for (int p = 5; p <= maxPoints; p++) {
			if (p%10==0)
				System.out.println(p+"%");
			double nearestTime = 0;
			double nearest2Time = 0;

			double nearestScore = 0;
			double nearest2Score = 0;

			double antScore = 0;
			double antScore2 = 0;
			double antTime = 0;



			long startTime;

			for (int i = 0; i < nrOfTests; i++) {
				ArrayList<Point2D> points = Util.randomPointList(p, 100);

				startTime = System.currentTimeMillis();
				nearestScore += NearestNeighbour.solve(Graph.DistanceGraph(points), 0);
				nearestTime += (System.currentTimeMillis() - startTime);

				startTime = System.currentTimeMillis();
				nearest2Score += NearestNeighbour.solveForAll(Graph.DistanceGraph(points));
				nearest2Time += (System.currentTimeMillis() - startTime);

				startTime = System.currentTimeMillis();

				AntColony antColony = new AntColony(Graph.DistanceGraph(points), 2.5, 5, 1, 0.2, 10);

				for (int k = 1 ; k < 50; k++)
				{
					antColony.solve();
					if (k==25)
						antScore += antColony.solve();
				}
				antScore2 += antColony.solve();

				antTime += (System.currentTimeMillis() - startTime);




			}


			nearestTime /= nrOfTests;
			nearestScore /= nrOfTests;
			nearest2Time /= nrOfTests;
			nearest2Score /= nrOfTests;
			antScore /= nrOfTests;
			antScore2 /= nrOfTests;
			antTime /= nrOfTests;

			nearestTimes.add(nearestTime);
			nearest2Times.add(nearest2Time);
			antTimes.add(antTime);
			nearest2Imp.add((nearestScore - nearest2Score) * 100 / nearestScore );
			antImp.add((nearestScore - antScore) * 100 / nearestScore );
			antImp2.add((nearestScore - antScore2) * 100 / nearestScore );


		}

		System.out.println(Arrays.toString(nearestTimes.toArray()));
		System.out.println(Arrays.toString(nearest2Times.toArray()));
		System.out.println(Arrays.toString(antTimes.toArray()));
		System.out.println(Arrays.toString(nearest2Imp.toArray()));
		System.out.println(Arrays.toString(antImp.toArray()));
		System.out.println(Arrays.toString(antImp2.toArray()));

	}
}
