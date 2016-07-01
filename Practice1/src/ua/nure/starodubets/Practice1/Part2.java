package ua.nure.starodubets.Practice1;

/**
 * The Part2 class implements the functionality of adding two numbers
 */
public class Part2 {

	/**
	 * The method is an entry point into the program.
	 * It implements the functionality of adding two numbers
	 *
	 * @param args the string array
	 */
	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		double sum = x + y;
		System.out.println("x + y = " + sum);
	}
}