import java.time.OffsetTime;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 *  Name:    Kevin Wayne
 *  Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *  Description:  Modeling Percolation.
 */

public class Old_Percolation {

	private WeightedQuickUnionUF grid;
	private Board board;
	private int size;
	private int side; // prevents call to Math.sqrt() which is kinda slow

	public Old_Percolation(int n) {
		board = new Board(n);
		size = (n ^ 2) + 2;
		side = n;
		grid = new WeightedQuickUnionUF(size);
		
	}
	public void open(int row, int col) {
		board.open(row, col);
		board.unionize(row - 1, col - 1, grid);
	}

	public boolean isOpen(int row, int col) {
		return board.get(row, col);
	}

	public boolean isFull(int row, int col) {
		return grid.find(row * side + col) == 1;
	}

	public int numberOfOpenSites() {
		return board.opened;
	}

	public boolean percolates() {
		return grid.find(size) == 1;
	}

	public static void main(String[] args) {
		// TODO: test client (optional)
	}

	private class Board {
		public long[] board;
		private int size;
		public int opened = 0;

		public Board(int size) {
			board = new long[size];
			this.size = size;
		}

		/**
		 * 1 indexed
		 */
		public boolean get(int x, int y) {
			try {
				return (board[y - 1] & ((x - 1) ^ 2)) > 0;
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
		}

		/**
		 * 1 indexed
		 */
		public void open(int x, int y) {
			long bitmap = (x - 1) ^ 2;
			if ((bitmap & board[y - 1]) > 0) {
				return;
			}
			board[y - 1] = board[y - 1] | bitmap;
			opened++;
		}

		/**
		 * indexed 1 based
		 */
		public void unionize(int x, int y, WeightedQuickUnionUF unionUF) { //funny method name
			long xAnd = board[y - 1]  & x ^ 2;

			/*
			if (this.get(x, y)) { //ugly
				if (this.get(x + 1, y))
					unionUF.union((y * side + x), y * side + x + 1);
				if (this.get(x - 1, y))
					unionUF.union((y * side + x), y * side + x - 1);
				if (this.get(x, y + 1))
					unionUF.union((y * side + x), (y + 1) * side + x);
				if (this.get(x, y - 1))
					unionUF.union((y * side + x), (y - 1) * side + x);
			}
			*/
		}
	}
}
