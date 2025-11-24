import java.util.Random;
import document.*;

public class DocumentBenchmarking {

    public static void main(String[] args) {

        // Number of trials for each size to reduce noise
        int trials = 100;

        // Start size = 5000 characters
        int increment = 5000;
        int numSteps = 20;
        int start = 5000;

        String file = "data/sample.txt";


        System.out.println("NumberOfChars\tBasicTime\tEfficientTime");

        for (int numToCheck = start; numToCheck < start + numSteps * increment; numToCheck += increment) {

            String text = getStringFromFile(file, numToCheck);

            // ------------------- BASIC DOCUMENT TIMING --------------------
            long basicStart = System.nanoTime();
            for (int i = 0; i < trials; i++) {
                Document d = new BasicDocument(text);
                d.getFleschScore();
            }
            long basicEnd = System.nanoTime();
            double basicTime = (basicEnd - basicStart) / 1e9; // seconds

            // ----------------- EFFICIENT DOCUMENT TIMING ------------------
            long effStart = System.nanoTime();
            for (int i = 0; i < trials; i++) {
                Document d = new EfficientDocument(text);
                d.getFleschScore();
            }
            long effEnd = System.nanoTime();
            double effTime = (effEnd - effStart) / 1e9; // seconds

            // Print result
            System.out.println(numToCheck + "\t" + basicTime + "\t" + effTime);
        }
    }

    public static String getStringFromFile(String filename, int numChars) {
        StringBuilder sb = new StringBuilder();
        try {
            java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename));
            int count = 0;

            while (sc.hasNext() && count < numChars) {
                String word = sc.next();
                sb.append(word).append(" ");
                count += word.length() + 1;
            }
            sc.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
            return "";
        }
        return sb.toString();
    }
}
