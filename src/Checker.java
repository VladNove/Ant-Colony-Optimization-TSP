import java.io.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Checker {



    public static void check(Scanner scanner, FileWriter writer) throws IOException {



        int nrOfNodes = scanner.nextInt();
        Graph graph = new Graph(nrOfNodes);
        for (int j = 0 ; j < nrOfNodes; j++)
            for (int k = 0; k < nrOfNodes; k++)
                graph.weights[j][k] = scanner.nextDouble();


            String afisNoduri = "numar de noduri:" + nrOfNodes;
        System.out.println(afisNoduri);
        if (writer != null)
            writer.write(afisNoduri + "\n");

        long starTime;
        double scor;
        long duration;
        DecimalFormat df=new DecimalFormat("#.##");

        if (nrOfNodes < 15) {
            starTime = System.currentTimeMillis();
            scor = NaiveRecursive.solve(graph, 0);
            duration = System.currentTimeMillis()-starTime;
            System.out.println("Solutie Exacta: \tt:" + duration + "ms \tl: " + df.format(scor));
            if (writer != null) writer.write("EXACT:" + scor + "\n" + Arrays.toString(NaiveRecursive.bestPath) + "\n");
        }

        starTime = System.currentTimeMillis();
        scor = NearestNeighbour.solve(graph, 0);
        duration = System.currentTimeMillis()-starTime;
        System.out.println("Nearest Neighbour: \tt:" + duration + "ms \tl: " + df.format(scor));
        if (writer != null) writer.write("NEAREST: " + scor + "\n" + Arrays.toString(NearestNeighbour.bestPath) + "\n");

        starTime = System.currentTimeMillis();
        scor = NearestNeighbour.solveForAll(graph);
        duration = System.currentTimeMillis()-starTime;
        System.out.println("Nearest Neighbour Improved: \t\tt:" + duration + "ms \tl: " + df.format(scor));
        if (writer != null) writer.write("IMP NEAREST: " + scor + "\n" + Arrays.toString(NearestNeighbour.bestPath) + "\n");

        starTime = System.currentTimeMillis();
        AntColony antColony = new AntColony(graph, 2, 5, 1, 0.2, nrOfNodes);
        for (int i = 0; i < 25; i++)
            scor = antColony.solve();
        duration = System.currentTimeMillis()-starTime;
        System.out.println("Optimizare Colonie de Furnici: \t\tt:" + duration + "ms \tl: " + df.format(scor));
        String bestPath = Arrays.toString(antColony.bestPath.subList(0,antColony.bestPath.size()-1).toArray());
        if (writer != null) writer.write("ANT: " + scor + "\n" + bestPath + "\n");





    }

    public static void main(String[] args) throws IOException {
        File inputDir = new File("IN");
        File outputDir = new File("OUTPUT");
        for (int i = 1; i <= 20 ; i ++) {
            String fileName = "test" + i + ".txt";

            File inputFile = new File(inputDir, fileName);
            Scanner scanner = new Scanner(inputFile);
            File outputFile = new File(outputDir, fileName);
            FileWriter fileWriter = new FileWriter(outputFile);
            System.out.println("test" + i);
            check(scanner, fileWriter);
            scanner.close();
            fileWriter.close();
            System.out.println();
        }
    }

}
