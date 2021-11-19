import java.awt.geom.Point2D;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Util {

	public static Random r = new Random(0);

	public static void resetRandom(int seed)
	{
		r = new Random(seed);
	}

	public static double randomDouble(double min, double max)
	{

		return min + (max - min) * r.nextDouble();
	}

	public static int randomInt(int max)
	{

		return r.nextInt(max);
	}

	public static ArrayList<Point2D> randomPointList(int n, double planeSize)
	{
		ArrayList<Point2D> points = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			points.add(new Point2D.Double(randomDouble(0, planeSize), randomDouble(0, planeSize)));
		}

		return points;
	}

	public static double distance(Point2D a, Point2D b)
	{
		return a.distance(b);
	}

	public static double evaluateSolution(int[] solution, Graph g)
	{
		double sum = 0;
		int i = 0;
		for (i = 0; i < solution.length-1; i++)
			sum += g.getEdge(solution[i],solution[i+1]);
		sum += g.getEdge(solution[i],solution[0]);
		return sum;
	}

	public static int[] cloneArray(int[] o)
	{
		int[] res = new int[o.length];
		for (int i = 0; i <  o.length; i++)
			res[i] = o[i];
		return res;
	}

}
