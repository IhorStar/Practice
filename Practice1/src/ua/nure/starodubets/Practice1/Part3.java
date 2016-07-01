package ua.nure.starodubets.Practice1;

/**
 * The Part3 class implements the functionality of determining the greatest
 * common divisor of two positive integers
 */
public class Part3 {

	/**
	 * The method is an entry point into the program.
	 * It implements the functionality of determining
	 * the greatest common divisor of two positive integers
	 *
	 * @param args the string array
	 */
	public static void main(String[] args) {
		int x = Math.abs(Integer.parseInt(args[0]));
		int y = Math.abs(Integer.parseInt(args[1]));
		System.out.print("Greatest common divisor " + x + " and " + y + " = ");
		while(y != 0) {
			int tmp = x % y;
			x = y;
			y = tmp;
		}
		System.out.println(x);
	}
}
