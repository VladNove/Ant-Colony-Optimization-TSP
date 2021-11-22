import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class AntTest3 {

		public static void main(String[] args) {

			int samples = 100;
			int maxsteps = 50;

			double  improvedNearScore = 0, exactScore = 0;
			double[] antScore = new double[maxsteps];
			double[] antScore2 = new double[maxsteps];
			double[] antTime = new double[maxsteps];
			double[] antTime2 = new double[maxsteps];


			for (int k = 0; k < samples; k++) {
				Util.r = new Random();
				ArrayList<Point2D> points = Util.randomPointList(50, 100);


				Graph graph = Graph.DistanceGraph(points);

				double startTime;

				improvedNearScore += NearestNeighbour.solveForAll(graph);


				Util.resetRandom(k);
				AntColony antColony = new AntColony(graph, 2, 5, 1, 0.2, 50);

				startTime = System.currentTimeMillis();
				for (int i = 0; i < maxsteps; i++) {
					antScore[i] += antColony.solve();
					antTime[i] += System.currentTimeMillis() - startTime;
				}


				Util.resetRandom(k);
				AntColony antColony2 = new AntColony(graph, 2, 5, 0, 0.2, 50);

				startTime = System.currentTimeMillis();
				for (int i = 0; i < maxsteps; i++) {
					antScore2[i] += antColony2.solve();
					antTime2[i] += System.currentTimeMillis() - startTime;
				}

			}

			exactScore=improvedNearScore/samples;

			for (int i = 0; i < maxsteps; i++) {
				antScore[i] = (antScore[i]/samples-exactScore)*100/exactScore;
				antTime[i] /= samples;

				antScore2[i] = (antScore2[i]/samples-exactScore)*100/exactScore;
				antTime2[i] /= samples;
			}

			System.out.println("antScore=" + Arrays.toString(antScore) + ";");
			System.out.println("antTime=" + Arrays.toString(antTime) + ";");
			System.out.println("antScore2=" + Arrays.toString(antScore2) + ";");
			System.out.println("antTime2=" + Arrays.toString(antTime2) + ";");
			System.out.println("plot (antTime(2: " +  maxsteps + "), antScore(2: " +  maxsteps + "),antTime2(2: " +  maxsteps + "), antScore2(2: " +  maxsteps + "))");

			System.out.println();
			System.out.println("best" + antScore[antScore.length-1]);
			System.out.println("time" + antTime[antScore.length-1]);
		}
	}



