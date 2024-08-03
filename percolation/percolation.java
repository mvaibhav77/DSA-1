import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final int topVirtual;
    private final int bottomVirtual;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 1); // include only 1 virtual top site
        topVirtual = n * n;
        bottomVirtual = n * n + 1; // This is now used only logically, not in the union-find structure
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        if(!isOpen(row,col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);

    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;

    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(topVirtual, bottomVirtual);
    }


    private void connectIfOpen(int row1, int col1, int row2, int col2) {
        if (row2 > 0 && row2 <= n && col2 > 0 && col2 <= n && isOpen(row2, col2)) {
            int index1 = getIndex(row1, col1);
            int index2 = getIndex(row2, col2);
            uf.union(index1, index2);
            ufBackwash.union(index1, index2);
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }
    // test client (optional)
//    public static void main(String[] args)
}
