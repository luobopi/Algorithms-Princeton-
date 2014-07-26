/*
 * Author: Yi Xing
 * Written: 07/23/2014
 */

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.introcs.*;

public class Percolation {
	
	private int size;
	private int top;
	private int bottom;
	private WeightedQuickUnionUF path;
	private WeightedQuickUnionUF wash;
	private boolean[] site;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0)
			throw new IllegalArgumentException();
		size = N;
		path = new WeightedQuickUnionUF(N * N + 2);
		wash = new WeightedQuickUnionUF(N * N + 2);
		top = N * N;
		bottom = N * N + 1;
		site = new boolean[N * N];
	}
            
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {

		if (isOpen(i, j)) {
			return;
		}

		int currentSite = xyTo1D(i, j);
		site[currentSite] = true;

		// union with top virtual cell
		if (i == 1) {
			path.union(top, currentSite);
			wash.union(top, currentSite);
		}
		// union with bottom virtual cell
		if (i == size) {
			path.union(bottom, currentSite);
		}
		// union with bottom cell
		if (i < size && isOpen(i + 1, j)) {
			path.union(xyTo1D(i + 1, j), currentSite);
			wash.union(xyTo1D(i + 1, j), currentSite);
		}
		// union with top cell
		if (i > 1 && isOpen(i - 1, j)) {
			path.union(xyTo1D(i - 1, j), currentSite);
			wash.union(xyTo1D(i - 1, j), currentSite);
		}
		// union with right cell
		if (j < size && isOpen(i, j + 1)) {
			path.union(xyTo1D(i, j + 1), currentSite);
			wash.union(xyTo1D(i, j + 1), currentSite);
		}
		// union with left cell
		if (j > 1 && isOpen(i, j - 1)) {
			path.union(xyTo1D(i, j - 1), currentSite);
			wash.union(xyTo1D(i, j - 1), currentSite);
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return site[xyTo1D(i, j)];
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		if (!isOpen(i, j)) {
			return false;
		}
		int pos = xyTo1D(i, j);
		return wash.connected(top, pos);
	}

	// does the system percolate?
	public boolean percolates() {
		if (path.connected(top, bottom)) {
			return true;
		}
		return false;
	}

	private int xyTo1D(int i, int j) {
		if (i <= 0 || j <= 0 || i > size || j > size) {
			throw new IndexOutOfBoundsException("Out of Bounds");
		}
		int pos = size * (i - 1) + j - 1;
		return pos;
	}
}
