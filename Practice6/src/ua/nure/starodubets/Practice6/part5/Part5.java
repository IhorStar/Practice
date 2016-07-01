package ua.nure.starodubets.Practice6.part5;

/**
 * The class demonstrates the work of the Tree class
 *
 * @author Ihor Starodubets
 *
 */
public class Part5 {

	/**
	 * The method is the entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<>();

		System.out.println(tree.add(3));
		System.out.println(tree.add(3));

		System.out.println("~~~~~~~");
		tree.add(new Integer[] { 1, 2, 5, 4, 6, 0 });
		tree.print();
		System.out.println("~~~~~~~");
		System.out.println(tree.remove(5));
		System.out.println(tree.remove(5));
		System.out.println("~~~~~~~");
		tree.print();
	}
}
