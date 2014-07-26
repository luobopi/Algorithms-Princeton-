
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.introcs.*;

public class PercolationStats {

	private static final double Z95 = 1.96;
	private int N;
	private double[] results;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Must be positive number");
		}
		int i, j, n;
		Percolation p;
		this.N = N;
		results = new double[T];
		for (int t = 0; t < T; t++) {
			n = 0;
			p = new Percolation(N);
			while (!p.percolates()) {
				do {
					i = StdRandom.uniform(1, N + 1);
					j = StdRandom.uniform(1, N + 1);
				} while (p.isOpen(i, j));
				p.open(i, j);
				n++;
			}
			results[t] = ((double) n) / (N * N);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(results);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		if (results.length <= 1) {
			return Double.NaN;
		}
		return StdStats.stddev(results);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return (mean() - (Z95 * stddev() / Math.sqrt(results.length)));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return (mean() + (Z95 * stddev() / Math.sqrt(results.length)));
	}

	// test client, described below
	public static void main(String[] args) {
		int N, T;
		PercolationStats stats;

		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		stats = new PercolationStats(N, T);

		StdOut.println("mean                       =" + stats.mean());
		StdOut.println("stddev                     =" + stats.stddev());
		StdOut.println("95% confidence interval    =" + stats.confidenceLo()
				+ "," + stats.confidenceHi());
	}

}
