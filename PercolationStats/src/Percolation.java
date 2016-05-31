import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 *
 * @author andrew
 */
public class Percolation {
    private static final int V_TOP = 0;
    private final boolean[][] grid;
    private final int gridSize;
    private final int dims;
    private final int vBot;
    private final WeightedQuickUnionUF wquuf;


    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        dims = N;
        if (dims <= 0) {
            throw new java.lang.IllegalArgumentException("Size must be greater than zero.");
        }
        grid = new boolean[dims+2][dims+2];
        gridSize = dims * dims;
        vBot = dims * dims + 1;
        wquuf = new WeightedQuickUnionUF(dims * dims + 2);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (offGrid(i, j)) {
            throw new IndexOutOfBoundsException("Outside acceptable range");
        }
        else if (!grid[i][j]) {
            grid[i][j] = true;
        }

        if (i == 1) {
            wquuf.union(V_TOP, xyMap(i, j));
        }

        if (i == dims) {
            wquuf.union(vBot, xyMap(i, j));
        }

        if (grid[i-1][j]) {
            wquuf.union(xyMap(i, j), xyMap(i-1, j));
        }

        if (grid[i+1][j]) {
            wquuf.union(xyMap(i, j), xyMap(i+1, j));
        }

        if (grid[i][j-1]) {
            wquuf.union(xyMap(i, j), xyMap(i, j-1));
        }

        if (grid[i][j+1]) {
            wquuf.union(xyMap(i, j), xyMap(i, j+1));
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (offGrid(i, j)) {
            throw new IndexOutOfBoundsException("Outside acceptable range");
        }
        else return grid[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (offGrid(i, j)) {
            throw new IndexOutOfBoundsException("Outside acceptable range");
        }
        else return wquuf.connected(xyMap(i, j), V_TOP);
    }

    // does the system percolate?
    public boolean percolates() {
        return wquuf.connected(vBot, V_TOP);
    }
    
    private int xyMap(int r, int c) {
        return (r-1) * dims + c;
    }
    private boolean offGrid(int i, int j) {
        return (i < 1 || i > dims || j < 1 || j > dims);
    }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation test = new Percolation(5);
//        test.open(1, 1);
//        test.open(2, 1);
//        test.open(3, 1);
//        test.open(4, 2);
//        test.open(5, 1);
//        if (test.percolates()) {
//            System.out.println("Percolates!");
//        }
//        else {
//            System.out.println("Nope!");
//        }
    }
}
