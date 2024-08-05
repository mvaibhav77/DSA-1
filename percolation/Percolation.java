import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int openSites;
    private int n;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufBackwash;
    private int top;
    private int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        grid = new boolean[n][n];
        openSites = 0;
        top = 0;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufBackwash = new WeightedQuickUnionUF(n * n + 1);
    }

    // converts 2D coordinate to 1D
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + (col - 1) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int index = xyTo1D(row, col);
            if (row == 1) {
                uf.union(index, top);
                ufBackwash.union(index, top);
            }
            if (row == n) uf.union(index, bottom);
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, xyTo1D(row - 1, col));
                ufBackwash.union(index, xyTo1D(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, xyTo1D(row + 1, col));
                ufBackwash.union(index, xyTo1D(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, xyTo1D(row, col - 1));
                ufBackwash.union(index, xyTo1D(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, xyTo1D(row, col + 1));
                ufBackwash.union(index, xyTo1D(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException();
        return ufBackwash.connected(top, xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(4, 2);
        percolation.open(5, 2);
        System.out.println(percolation.percolates()); // should print true
    }
}
