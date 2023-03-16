import org.knowm.xchart.*;
import java.util.*;

public class MainPart1 {

    public static int log2(int n){
        if(n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public static void main(String[] args) {
        int[] inputSizes = new int[]{10, 50, 100, 500, 1000, 5000, 10000, 25000, 50000, 75000, 100000};
        AVLTree<Integer> avlTree = new AVLTree<>();
        Random random = new Random();
        int numTrials = 100000; // number of times to run the search operation for each input size
        int warmupTrials = 1000; // number of warm-up trials
        double[] avgRuntimes = new double[inputSizes.length];
        double[] maxRuntime = new double[inputSizes.length];
        // warm-up phase
        for (int i = 0; i < inputSizes.length; i++) {
            int inputSize = inputSizes[i];
            Integer[] inputValues = new Integer[inputSize];
            for (int j = 0; j < inputSize; j++) {
                inputValues[j] = j;
            }
            Collections.shuffle(Arrays.asList(inputValues), random);
            avlTree = new AVLTree<>(); // create a new tree for each trial
            for (int j = 0; j < inputSize; j++) {
                avlTree.insert(inputValues[j]);
            }
            for (int k = 0; k < numTrials; k++) {
                int keyToSearch = random.nextInt(inputSize);
                avlTree.search(keyToSearch);
            }
        }



        // benchmarking phase
        for (int i = 0; i < inputSizes.length; i++) {
            int inputSize = inputSizes[i];
            Integer[] inputValues = new Integer[inputSize];
            for (int j = 0; j < inputSize; j++) {
                inputValues[j] = j;
            }
            Collections.shuffle(Arrays.asList(inputValues), random);
            avlTree = new AVLTree<>(); // create a new tree once
            for (int j = 0; j < inputSize; j++) {
                avlTree.insert(inputValues[j]);
            }
            long totalRuntime = 0;
            for (int trial = 0; trial < numTrials; trial++) {
                int[] keysToSearch = new int[10];
                for (int j = 0; j < 10; j++) {
                    keysToSearch[j] = random.nextInt(inputSize);
                }
                long startTime = System.nanoTime();
                for (int j = 0; j < 10; j++) {
                    avlTree.search(keysToSearch[j]);
                }
                long endTime = System.nanoTime();
                totalRuntime += (endTime - startTime);
            }
            avgRuntimes[i] = ((double) totalRuntime) / (numTrials * 10);
            maxRuntime[i] = log2(inputSize)*(25);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600)
                .title("Execution Time of AVL Tree Node Search")
                .xAxisTitle("Input Size (n)")
                .yAxisTitle("Time (ns)").build();
        chart.addSeries("Computed Runtime",
                (double[])Arrays.stream(inputSizes).asDoubleStream().toArray(), avgRuntimes);
        chart.addSeries("Scaled O (log n) Runtime",
                (double[])Arrays.stream(inputSizes).asDoubleStream().toArray(), maxRuntime);
        new SwingWrapper<>(chart).displayChart();
    }
}