import java.time.OffsetTime;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 *  Name:    Kevin Wayne
 *  Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *  Description:  Modeling Percolation.
 */

public class Old__Percolation {

	private WeightedQuickUnionUF grid;
	private int size;
	private int side; // prevents call to Math.sqrt() which is kinda slow
	private boolean[] board;
	private int opened = 0;

	public Old__Percolation(int n) {
		board = new boolean[n ^ 2];
		size = (n ^ 2) + 2;
		side = n;
		grid = new WeightedQuickUnionUF(size);

		for (int i = 0; i < n; i++) {
			grid.union(0, i);
		}

//		for (int i = (n ^ 2) - n; i < size; i++) {
//			grid.union(i, size - 1);
//		}
		
	}

	public void open(int row, int col) {
		if (!board[(row - 1) * side + col]) {
			board[(row - 1) * side + col] = true;
			opened++;
		}
		chechSides(row, col);
	}

	public boolean isOpen(int row, int col) {
		return board[(row - 1) * side + col];
	}

	public boolean isFull(int row, int col) {
		return grid.find(((row - 1) * side) + col) == 1;
	}

	public int numberOfOpenSites() {
		return opened;
	}

	public boolean percolates() {
		return grid.find(size) == 1;
	}

	public static void main(String[] args) {
		// TODO: test client (optional)
	}

	private int getIndex(int row, int col) {
		return (row * side + col + 1 <= size) ? row * side + col + 1 : -1;
	} 

	private void chechSides(int row, int col) {
		int index = row * size + col + 1;
		
	}
}
