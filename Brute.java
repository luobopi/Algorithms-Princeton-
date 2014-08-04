/*
 * Author: Yi Xing
 * Written: 08/02/2014 
 * 
 * Write a program Brute.java that examines 4 points at a time 
 * and checks whether they all lie on the same line segment, 
 * printing out any such line segments to standard output and 
 * drawing them using standard drawing. To check whether the 4 
 * points p, q, r, and s are collinear, check whether the slopes 
 * between p and q, between p and r, and between p and s are all 
 * equal.
 */

import edu.princeton.cs.introcs.*;

import java.util.Arrays;

public class Brute {

	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.001);

		// read name of input file
		String filename = args[0];

		Point[] input = readFile(filename);
		collinearPointer(input);

		// StdDraw.setPenRadius();

		// collinearPointer(points);
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

	private static void collinearPointer(Point[] points) {
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				for (int k = j + 1; k < points.length - 1; k++) {
					for (int l = k + 1; l < points.length; l++) {
						if (isLine(points[i], points[j], points[k], points[l])) {
							Point[] result = new Point[4];

							result[0] = points[i];
							result[1] = points[j];
							result[2] = points[k];
							result[3] = points[l];

							Arrays.sort(result);

							// StdOut.print(points[]);

							StdOut.print(result[0] + " -> ");
							StdOut.print(result[1] + " -> ");
							StdOut.print(result[2] + " -> ");
							StdOut.print(result[3] + "\n");

							result[0].drawTo(result[3]);

							StdDraw.show(0);
						}
					}
				}
			}
		}
	}

	private static boolean isLine(Point a, Point b, Point c, Point d) {
		double slope = a.slopeTo(b);
		return (a.slopeTo(c) == slope && a.slopeTo(d) == slope);
	}
}
