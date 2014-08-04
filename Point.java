/*************************************************************************
 * Name: Yi Xing
 * Written: 07/31/2014
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.introcs.*;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER; // YOUR DEFINITION HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
		SLOPE_ORDER = new SlopeOrder();
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		/* YOUR CODE HERE */
		if (this.x == that.x && this.y != that.y) {
			return Double.POSITIVE_INFINITY;
		} else if (this.x == that.x && this.y == that.y) {
			return Double.NEGATIVE_INFINITY;
		} else if (this.y == that.y) {
			return 0.0;
		}
		return ((double) that.y - this.y) / ((double) that.x - this.x);
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		/* YOUR CODE HERE */
		if (this.y == that.y) {
			return this.x - that.x;
		}
		return this.y - that.y;
	}

	private class SlopeOrder implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			double slope1 = slopeTo(p1);
			double slope2 = slopeTo(p2);
			if (slope1 < slope2)
				return -1;
			else if (slope1 > slope2)
				return 1;
			else
				return 0;
		}
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		/* YOUR CODE HERE */
	}
}
