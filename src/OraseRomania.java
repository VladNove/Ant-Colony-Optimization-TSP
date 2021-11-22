import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OraseRomania {



    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("IN");
        File inputFile = new File (dir, "orase.csv");
        Scanner scanner = new Scanner(inputFile);
        ArrayList<Point2D> listaCoord = new ArrayList<>();
        ArrayList<String> numeOrase = new ArrayList<>();
        scanner.nextLine();
        int numarMinimLocuitori = 5000;
        while (scanner.hasNextLine())
        {
            String linie = scanner.nextLine();
            String[] valori = linie.split(",");
            if (!valori[5].isEmpty())
            if (Integer.parseInt(valori[5])>numarMinimLocuitori)
            {
                numeOrase.add(valori[2]);
                listaCoord.add(new Point2D.Double(Double.parseDouble(valori[0]),Double.parseDouble(valori[1])));
               // System.out.println(valori[2] + " " + Double.parseDouble(valori[0]) + " " + Double.parseDouble(valori[1]));
            }
        }


        int nrOrase = numeOrase.size();
        System.out.println("numar orase: " + nrOrase + " (cu peste " + numarMinimLocuitori + " locuitori)");
        Graph graph = Graph.DistanceGraph(listaCoord);

        double scorNN = NearestNeighbour.solve(graph,0);
        System.out.println("NN: " + scorNN);
        double scorINN = NearestNeighbour.solveForAll(graph);
        System.out.println("INN: " + scorINN);
        double scorAco = 0;
        AntColony antColony = new AntColony(graph, 2, 5, 1, 0.2, nrOrase);
        System.out.println("nr iteratii = 20");
        for (int i = 0; i < 20; i++) {
            scorAco = antColony.solve();
        }
        System.out.println("ANT: " + scorAco);

        System.out.println("traseu:");

        for(int i : antColony.bestPath)
            System.out.print(numeOrase.get(i) + " ");

        System.out.print("\nx = [");
        for (int i : antColony.bestPath)
            System.out.print(listaCoord.get(i).getX() + " ");
        System.out.print("];\ny = [");
        for (int i : antColony.bestPath)
            System.out.print(listaCoord.get(i).getY() + " ");
        System.out.print("];\n");

        System.out.println("Distanta aprox in km: " + scorAco*111);




    }
}
