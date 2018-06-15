import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double MULTIPLIER = 1.96;
    private final double[] threshold;
    private final int totalTrials;
    private final double mean;
    private final double sd;
       
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0"); 
        }
        
        totalTrials = trials;
        int trialNo;
        threshold = new double[trials];
        
        for (trialNo = 0; trialNo < trials; trialNo++) {
            Percolation p = new Percolation(n);
            
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            
            threshold[trialNo] = (double) (p.numberOfOpenSites())/(n*n);
        }
        mean = StdStats.mean(threshold);
        sd = StdStats.stddev(threshold);
    }    // perform trials independent experiments on an n-by-n grid
    public double mean() {
        return mean;
    }                          // sample mean of percolation threshold
    public double stddev() {
        return sd;
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean - ((MULTIPLIER * sd) / Math.sqrt(totalTrials));
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((MULTIPLIER * sd) / Math.sqrt(totalTrials));
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = ["+ps.confidenceLo()+", "+ps.confidenceHi()+"]");
    }        // test client (described below)
}
