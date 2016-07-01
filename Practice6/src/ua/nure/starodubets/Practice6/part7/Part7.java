package ua.nure.starodubets.Practice6.part7;

/**
 * The class demonstrates the work of the Range class
 *
 * @author Ihor Starodubets
 *
 */
public class Part7 {

	/**
	 * The local
	 */
	private static boolean local = false;

	/**
	 * The start
	 */
	private static final int START = 3;

	/**
	 * The end
	 */
	private static final int END = 10;

	/**
	 * The method gets local
	 *
	 * @return a local
	 */
	public static boolean isLocal() {
		return local;
	}

	/**
	 * The method sets a local
	 *
	 * @param local a local
	 */
	public static void setLocal(boolean local) {
		Part7.local = local;
	}

	/**
	 * The method is the entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		System.out.println("Reverse ==> " + local);
		Range range = new Range(START, END, isLocal());
		range.output();
		setLocal(true);
		System.out.println("\nReverse ==> " + isLocal());
		range = new Range(START, END, local);
		range.output();
	}
}
