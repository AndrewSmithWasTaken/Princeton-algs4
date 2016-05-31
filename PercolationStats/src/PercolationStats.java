import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

   private final int dims;
   private final double numExpts;
   private final double[] threshArray;
   private final double mean;
   private final double stddev;
   private int openSites = 0;

   public PercolationStats(int dims, int numExpts) {

      if (dims <= 0 || numExpts <= 0) {
         throw new IllegalArgumentException();
      }
      this.dims = dims;
      this.numExpts = numExpts;
      this.threshArray = new double[numExpts];
      doExpts();
      this.mean = StdStats.mean(threshArray);
      this.stddev = StdStats.stddev(threshArray);
   }

   public double confidenceHi() {
      return mean + ((1.96 * stddev) / (Math.sqrt(numExpts)));
   }

    public double confidenceLo() {
      return mean - ((1.96 * stddev) / (Math.sqrt(numExpts)));
   }

   public double mean() {
      return mean;
   }

   public double stddev() {
      return stddev;
   }

   private void doExpts() {
      // outer loop numRuns
      for (int i = 0; i < numExpts; i++) {
         Percolation percTest = new Percolation(dims);
         while (!percTest.percolates()) {
             int r = StdRandom.uniform(1, dims+1);
             int c = StdRandom.uniform(1, dims+1);
             if (!percTest.isOpen(r, c)) {
                 percTest.open(r, c);
                 openSites++;
             }
         }
         threshArray[i] = openSites/(double) (dims*dims);
      }
   }

   public static void main(String[] args) {

      int dims = Integer.parseInt(args[0]);
      int numExpts = Integer.parseInt(args[1]);

      PercolationStats theStats = new PercolationStats(dims, numExpts);

      System.out.println("mean                    = " + theStats.mean());
      System.out.println("stddev                  = " + theStats.stddev());
      System.out.println("95% confidence interval = " + theStats.confidenceLo() + ", "+ theStats.confidenceHi());
   }
}