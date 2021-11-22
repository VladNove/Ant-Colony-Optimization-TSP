import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	public final double[][] weights;

	public Graph(int n) {
		weights = new double[n][n];
	}

	public int size()
	{
		return weights.length;
	}

	public void setEdge(int i, int j, double value) {
		weights[i][j] = value;
		weights[j][i] = value;
	}

	public void setAll(double value) {
		for (int i = 0 ; i < weights.length; i++)
		{
			for (int j = i+1; j < weights.length; j++)
			{
				weights[i][j]=value;
			}
		}
	}

	public void incEdge(int i, int j, double value) {
		weights[i][j] += value;
		weights[j][i] += value;
	}

	public double getEdge(int i, int j) {
		return weights[i][j];
	}

	public static Graph DistanceGraph (ArrayList<Point2D> points) {
		Graph result = new Graph(points.size());
		for (int i = 0 ; i < points.size(); i++)
		{
			for (int j = i+1; j < points.size(); j++)
			{
				double distance = Util.distance(points.get(i),points.get(j));
				result.setEdge(i,j,distance);
			}
		}
		return result;
	}

}
