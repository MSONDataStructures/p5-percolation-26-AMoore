import java.time.OffsetTime;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 *  Name:    Kevin Wayne
 *  Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *  Description:  Modeling Percolation.
 */

public class Percolation {

	private WeightedQuickUnionUF grid;
	private int size;
	private int side; // prevents call to Math.sqrt() which is kinda slow
	private int opened; private Node[] board;

	public Percolation(int n) {
		size = (n * n) + 2;
		side = n;
		board = new Node[(int) Math.pow(n, 2)];
		grid = new WeightedQuickUnionUF(size);

		for (int i = 0; i < board.length; i++) // i kinda dislike the way you dont need parathises for one-liners
			board[i] = new Node();

		for (int i = 1; i <= side; i++) { // much prefferable
			grid.union(0, i);
		}

		for (int i = 0; i <= side; i++)
			grid.union(size - 1, (size - 1) - i);
			

	}

	public void open(int row, int col) {
		if (!board[realIndex(row, col)].open) {
			board[realIndex(row, col)].open = true;
			opened++;
			link(row, col);
		}
	}

	private void link(int row, int col) {
		int index = realIndex(row, col) + 1;
		if (realIndex(row + 1, col) != -1 && board[realIndex(row + 1, col)].open)
			grid.union(index, realIndex(row + 1, col) + 1);

		if (realIndex(row - 1, col) != -1 && board[realIndex(row - 1, col)].open)
			grid.union(index, realIndex(row - 1, col) + 1);

		if (realIndex(row, col + 1) != -1 && board[realIndex(row, col + 1)].open)
			grid.union(index, realIndex(row, col + 1) + 1);

		if (realIndex(row, col - 1) != -1 && board[realIndex(row, col - 1)].open)
			grid.union(index, realIndex(row, col - 1) + 1);
	}


	public boolean isOpen(int row, int col) {
		return board[realIndex(row, col)].open;
	}

	public boolean isFull(int row, int col) {
		return grid.find(realIndex(row, col) + 1) == grid.find(0) && board[realIndex(row, col)].open;
//		return board[realIndex(row, col)].filled;
	}

	public int numberOfOpenSites() {
		return opened;
	}

	public boolean percolates() {
		if (side == 1) { // hacky but works
			return board[0].open;
		}
		return grid.find(size - 1) == grid.find(0);
	}

	public static void main(String[] args) {
		// TODO: test client (optional)
	}

	/**
	 * turns x, y into one int ret: -1 if out of bounds
	 */
	private int realIndex(int y, int x) {

		if (x > side || x < 0 || y > side || y < 0)
			return -1;

		int num = (y - 1) * side + (x - 1);
		int retval = (num >= 0 && num < board.length) ? num : -1;
		return retval;
	}

	/**
	 * InnerPercolation
	 */
	public class Node {
		public boolean open = false;
		public boolean filled = false;

	}
}
