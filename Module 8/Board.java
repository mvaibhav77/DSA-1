import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private final int n;

    // Constructor
    public Board(int[][] tiles) {
        if (tiles == null || tiles.length < 2 || tiles.length >= 128 || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException("Invalid board size");
        }
        this.n = tiles.length;
        this.tiles = copyTiles(tiles);
    }

    // Helper method to copy the 2D array
    private int[][] copyTiles(int[][] tiles) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    // String representation of the board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int[] row : tiles) {
            for (int tile : row) {
                s.append(String.format("%2d ", tile));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // Board dimension n
    public int dimension() {
        return n;
    }

    // Hamming distance (number of tiles out of place)
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalValue(i, j)) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // Manhattan distance (sum of distances from the goal)
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = tiles[i][j];
                if (value != 0 && value != goalValue(i, j)) {
                    int goalRow = (value - 1) / n;
                    int goalCol = (value - 1) % n;
                    manhattan += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return manhattan;
    }

    // Check if the board is the goal board
    public boolean isGoal() {
        return hamming() == 0;
    }

    // Compare two boards for equality
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board board = (Board) y;
        return n == board.n && Arrays.deepEquals(tiles, board.tiles);
    }

    // Find all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int blankRow = 0, blankCol = 0;

        // Locate the blank space
        loopA:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break loopA;
                }
            }
        }

        // Generate neighbors by moving the blank space
        if (blankRow > 0) neighbors.add(new Board(swap(blankRow, blankCol, blankRow - 1, blankCol)));
        if (blankRow < n - 1) neighbors.add(new Board(swap(blankRow, blankCol, blankRow + 1, blankCol)));
        if (blankCol > 0) neighbors.add(new Board(swap(blankRow, blankCol, blankRow, blankCol - 1)));
        if (blankCol < n - 1) neighbors.add(new Board(swap(blankRow, blankCol, blankRow, blankCol + 1)));

        return neighbors;
    }

    // Swap two tiles and return the new board
    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] copy = copyTiles(tiles);
        int temp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = temp;
        return copy;
    }

    // Create a twin board by swapping any pair of tiles
    public Board twin() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
                    return new Board(swap(i, j, i, j + 1));
                }
            }
        }
        throw new RuntimeException("No twin possible");
    }

    // Get the goal value for a specific position
    private int goalValue(int row, int col) {
        if (row == n - 1 && col == n - 1) return 0;
        return row * n + col + 1;
    }

    // Unit testing
    public static void main(String[] args) {
        int[][] tiles = {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        };
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Twin:\n" + board.twin());

        System.out.println("Neighbors:");
        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }
    }
}
