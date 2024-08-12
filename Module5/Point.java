import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x; // x-coordinate of this point
    private final int y; // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(final Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     *
     * @param that the other point
     * @return -1 if this point is less than the other point,
     * 1 if greater, 0 if equal
     */
    public int compareTo(final Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns the slope between this point and the specified point.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(final Point that) {
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } // degenerate line segment
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } // vertical line segment
        if (this.y == that.y) {
            return +0.0;
        } // horizontal line segment

        return (double) (that.y - this.y) / (that.x - this.x); // slope
    }

    /**
     * Compares two points by slopes they make with this point.
     *
     * @return a comparator that compares two points by slopes they make with this point
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            return Double.compare(slope1, slope2);
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Main method for testing
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(3, 2);

        // Testing toString
        System.out.println("p1: " + p1.toString());  // Expected output: (1, 2)
        System.out.println("p2: " + p2.toString());  // Expected output: (3, 4)
        System.out.println("p3: " + p3.toString());  // Expected output: (3, 2)

        // Testing compareTo

        System.out.println("p1.compareTo(p2): "
                + p1.compareTo(p2)); // Expected output: -1
        System.out.println("p1.compareTo(p3): "
                + p1.compareTo(p3)); // Expected output: 1
        System.out.println("p2.compareTo(p3): "
                + p2.compareTo(p3)); // Expected output: 1

        // Testing slopeTo
        System.out.println("p1.slopeTo(p2): " + p1.slopeTo(p2)); // Expected output: 1.0
        System.out.println("p1.slopeTo(p3): " + p1.slopeTo(p3)); // Expected output: 0.0

        // Testing slopeOrder
        Comparator<Point> comparator = p1.slopeOrder();
        System.out.println("comparator.compare(p2, p3): " + comparator.compare(p2, p3)); // Expected output: -1 or 1
    }
}
