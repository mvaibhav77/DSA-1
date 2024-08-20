import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private final SearchNode solutionNode;
    private final boolean solvable;

    // SearchNode class to represent each node in the A* search tree
    private class SearchNode {
        private final Board board;
        private final int moves;
        private final SearchNode previous;
        private final int priority;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = board.manhattan() + moves;  // Priority based on Manhattan distance
        }

        public int priority() {
            return this.priority;
        }
    }

    // Comparator for comparing search nodes based on their priority
    private static class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            return Integer.compare(n1.priority(), n2.priority());
        }
    }

    // Constructor to find a solution to the initial board using A* algorithm
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board cannot be null");
        }

        MinPQ<SearchNode> pq = new MinPQ<>(new NodeComparator());
        MinPQ<SearchNode> twinPQ = new MinPQ<>(new NodeComparator());

        pq.insert(new SearchNode(initial, 0, null));
        twinPQ.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode solution = null;

        while (true) {
            solution = step(pq);
            if (solution != null || step(twinPQ) != null) {
                solvable = (solution != null);
                break;
            }
        }

        solutionNode = solution;
    }

    // Helper method to perform a step in the A* search algorithm
    private SearchNode step(MinPQ<SearchNode> pq) {
        if (pq.isEmpty()) return null;

        SearchNode node = pq.delMin();
        if (node.board.isGoal()) return node;

        for (Board neighbor : node.board.neighbors()) {
            if (node.previous == null || !neighbor.equals(node.previous.board)) {
                pq.insert(new SearchNode(neighbor, node.moves + 1, node));
            }
        }
        return null;
    }

    // Method to check if the initial board is solvable
    public boolean isSolvable() {
        return solvable;
    }

    // Method to return the minimum number of moves to solve the puzzle, or -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return solutionNode.moves;
    }

    // Method to return the sequence of boards in the shortest solution, or null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        List<Board> solution = new ArrayList<>();
        for (SearchNode node = solutionNode; node != null; node = node.previous) {
            solution.add(node.board);
        }
        Collections.reverse(solution);
        return solution;
    }

    // Test client to solve the puzzle
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
