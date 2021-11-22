import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TestGenerator {


    public static void main(String[] args) throws IOException {
        String dirName = "IN";
        File dir = new File(dirName);

        //teste mici
        for (int i = 1; i <= 20 ; i ++) {
            int nrOfPoints ;
            if (i<=5)
                nrOfPoints = i + 7;
            else
                nrOfPoints = 100 * (i)/20;
            Graph graph = Graph.DistanceGraph(Util.randomPointList(nrOfPoints,100));
            File actualFile = new File(dir, "test" + i + ".txt");
            FileWriter fileWriter = new FileWriter(actualFile);
            fileWriter.write(nrOfPoints + "\n");
            for (int j = 0; j < nrOfPoints; j++) {
                for (int k = 0; k < nrOfPoints; k++) {
                    fileWriter.write(graph.weights[j][k] + " ");
                }
                fileWriter.write("\n");
            }

            fileWriter.close();
        }

    }

}
