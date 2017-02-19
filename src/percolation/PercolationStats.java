package percolation;

/**
 * Created by zheyu on 10/5/16.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Argument can't be less than or equal to 0");
        }
        this.trials = trials;
        fractions = new double[trials];
        for (int count = 0; count < trials; ++count) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;

            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    ++openSites;
                }
            }

            fractions[count] = (double) openSites / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);

        String confidenceInterval = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();
        StdOut.printf("%20s = %f", "mean", percolationStats.mean());
        StdOut.printf("%20s = %f", "stddev", percolationStats.stddev());
        StdOut.printf("%20s = %s", "Confidence Interval", confidenceInterval);
    }
}
