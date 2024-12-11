import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * main
 */
public class Main {

	public static void main(String[] args) {
		WeightedQuickUnionUF unionUF = new WeightedQuickUnionUF(10);
		unionUF.union(1, 2);
		unionUF.union(3, 4);

		System.out.println(unionUF.find(1) + " " + unionUF.find(4));
		unionUF.union(2, 4);
		System.out.println(unionUF.find(1) + " " + unionUF.find(4));

		//learning how the Union.find(p) works
	}
}
