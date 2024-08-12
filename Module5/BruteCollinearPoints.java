import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> lines;

    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException("Argument cannot be null");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        int n = points.length;
        lines = new ArrayList<>();

        Arrays.sort(points);

        // Check for duplicate points
        for (int i = 1; i < n; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points found");
            }
        }

        // Iterate over all combinations of 4 points
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        // Check if points p, q, r, s are collinear
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            lines.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

    }    // finds all line segments containing 4 points
    public int numberOfSegments(){
        return lines.size();
    } // the number of line segments
    public LineSegment[] segments() {
        return lines.toArray(new LineSegment[0]);
    } // the line segments list
}
