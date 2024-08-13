import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument cannot be null");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        // Clone and sort to avoid mutation of original array
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        int n = sortedPoints.length;

        // Check for duplicate points
        for (int i = 1; i < n; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points are not allowed");
            }
        }

        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point p = sortedPoints[i];

            // Sort the rest based on the slope they make with 'p'
            Point[] slopeOrder = sortedPoints.clone();
            Arrays.sort(slopeOrder, p.slopeOrder());

            int count = 1;
            for (int j = 1; j < n; j++) {
                if (p.slopeTo(slopeOrder[j]) == p.slopeTo(slopeOrder[j - 1])) {
                    count++;
                    if (count >= 3 && j == n - 1) { // End of the array
                        addSegmentIfNotDuplicate(p, slopeOrder, j - count + 1, j);
                    }
                } else {
                    if (count >= 3) {
                        addSegmentIfNotDuplicate(p, slopeOrder, j - count, j - 1);
                    }
                    count = 1;
                }
            }
        }
    }

    // Ensure the segment is only added if 'p' is the smallest point in the segment
    private void addSegmentIfNotDuplicate(Point p, Point[] slopeOrder, int start, int end) {
        Point[] collinearPoints = new Point[end - start + 2];
        collinearPoints[0] = p;
        for (int i = start, j = 1; i <= end; i++, j++) {
            collinearPoints[j] = slopeOrder[i];
        }
        Arrays.sort(collinearPoints);

        if (collinearPoints[0].compareTo(p) == 0) {  // Only add if 'p' is the smallest point
            segments.add(new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]));
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
