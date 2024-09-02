package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] thresholdStats;
    private int n;
    private int t;

    /** perform t independent experiments on an n-by-n grid */
    public PercolationStats(int n, int t, PercolationFactory pf) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("n and t must be positive");
        }
        thresholdStats = new double[t];
        int area = n * n;
        for (int i = 0; i < t; i++) {
            Percolation p = pf.make(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                p.open(row, col);
            }
            thresholdStats[i] = p.numberOfOpenSites() / area;
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(thresholdStats);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(thresholdStats);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }

}
