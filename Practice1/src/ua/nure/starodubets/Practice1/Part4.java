package ua.nure.starodubets.Practice1;

/**
 * The Part4 class implements the functionality of determining
 * the sum of digits of a positive integer
 */
public class Part4 {

	/**
	 * The method is an entry point into the program.
	 * It implements the functionality of determining
	 * the sum of digits of a positive integer
	 *
	 * @param args the string array
	 */
	public static void main(String[] args) {
		int x = Math.abs(Integer.parseInt(args[0]));
		int sum = 0;
		while(x != 0) {
			sum += x % 10;
			x /= 10;
		}
		System.out.println("Sum = " + sum);
	}
}
