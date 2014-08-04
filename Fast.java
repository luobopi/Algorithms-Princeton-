/*
 * Author: Yi Xing
 * Wriiten: 08/03/2014
 * 
 * Think of p as the origin.
 * For each other point q, determine the slope it makes with p.
 * Sort the points according to the slopes they makes with p.
 * Check if any 3 (or more) adjacent points in the sorted order have 
 * equal slopes with respect to p. If so, these points, together with 
 * p, are collinear.
 */
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;

public class Fast {

	public static void main(String[] args) {

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01);

		// read name of input file
		String filename = args[0];

		Point[] input = readFile(filename);
		fastCollinearPointer(input);
	}

	private static Point[] readFile(String filename) {
		In in = new In(filename);
		int N = in.readInt();

		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();

			points[i] = new Point(x, y);
			points[i].draw();

			StdDraw.show(0);
		}

		StdDraw.setPenRadius();
		return points;
	}

	private static void fastCollinearPointer(Point[] points) {
		Arrays.sort(points);
		Point[] slopeOrder;
		

		for (int i = 0; i < points.length - 3; i++) {
			Point p = points[i];
			
			slopeOrder = Arrays.copyOfRange(points, i + 1, points.length);
			Arrays.sort(slopeOrder,p.SLOPE_ORDER);
			
			int size = 1;
			for (int j = 0; j < slopeOrder.length - 2; j += size, size = 1) {
				double slope = p.slopeTo(slopeOrder[j]);

				while (j + size < slopeOrder.length
						&& p.slopeTo(slopeOrder[j + size]) == slope) {
					size++;
				}

				if (size < 3 || isLine(points, i, p, slope)) {
					continue;
				}

				StdOut.print(p + " -> ");
				for (int k = 0; k < size - 1; k++) {
					StdOut.print(slopeOrder[j + k] + " -> ");
				}
				StdOut.print(slopeOrder[j + size - 1] + "\n");

				p.drawTo(slopeOrder[j + size - 1]);
				StdDraw.show(0);
			}
			

		}
	}

	private static boolean isLine(Point[] points, int N, Point p, double slope) {
		for (int i = N; i >= 0; i--) {
			if (p.slopeTo(points[i]) == slope)
				return true;
		}
		return false;
	}
}
