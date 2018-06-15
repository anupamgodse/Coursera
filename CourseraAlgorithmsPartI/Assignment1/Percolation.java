import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// grid[][] = false (Closed)
// grid[][] = true (0pen)

public class Percolation {
    private boolean[][] grid;
    private final int size;
    private final WeightedQuickUnionUF connected;
    private final WeightedQuickUnionUF full;
    private int nbOpen;
    
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be greater than 0"); 
        }
        
        size = n;
        grid = new boolean[n][n];
        connected = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
        nbOpen = 0;   
            
        int r, c;
        for (r = 0; r < n; r++) {
            for (c = 0; c < n; c++) {
                grid[r][c] = false;
            }
        }
    }                // create n-by-n grid, with all sites blocked
    
    public void open(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
        
        if (!isOpen(row, col)) {
            nbOpen += 1;
            
            int p = getArrayIndex(row, col); 
            int q;
            
            if (row == 1) {    
                q = size * size;
                connected.union(p, q);
                full.union(p, q);
            }
            
            if (row == size) {
                q = size * size + 1;
                connected.union(p, q);
            }
            
            grid[row - 1][col - 1] = true;
            
            
            if (row - 1 >= 1 && isOpen(row - 1, col)) {
                q = getArrayIndex(row - 1, col);
                connected.union(p, q);
                full.union(p, q);
            }
            if (row + 1 <= size && isOpen(row + 1, col)) {
                q = getArrayIndex(row + 1, col);
                connected.union(p, q);
                full.union(p, q);
            }
            if (col - 1 >= 1 && isOpen(row, col - 1)) {
                q = getArrayIndex(row, col - 1);
                connected.union(p, q);
                full.union(p, q);
            }
            if (col + 1 <= size && isOpen(row, col + 1)) {
                q = getArrayIndex(row, col + 1);
                connected.union(p, q);
                full.union(p, q);
            }
        }
            
    } // open site (row, col) if it is not open already
    
    public boolean isOpen(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
        return grid[row - 1][col - 1];
    } // is site (row, col) open?
    
    public boolean isFull(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
        return full.connected(getArrayIndex(row, col), size * size);
    } // is site (row, col) full?
    
    public int numberOfOpenSites() {
        return nbOpen;
    } // number of open sites
    
    public boolean percolates() {
        return connected.connected(size * size, size * size + 1);
    } // does the system percolate?
    
    private int getArrayIndex(int row, int col) {
        return size * (row - 1) + (col - 1);
    }
    
    public static void main(String[] args) {
        // test client (optional)
    } // test client (optional)
    
}
