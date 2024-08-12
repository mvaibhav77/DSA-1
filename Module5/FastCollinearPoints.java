import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException("Argument cannot be null");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }
        int n = points.length;


        Arrays.sort(points);
        for (int i = 1; i < n; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points are not allowed");
            }
        }

        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point p = points[i];

            // Sort the points based on the slope they make with 'p'
            Point[] slopeOrder = points.clone();
            Arrays.sort(slopeOrder, p.slopeOrder());

            

        }
    }    // finds all line segments containing 4 points
    public int numberOfSegments(){
        return segments.size();
    } // the number of line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    } // the line segments list
}
