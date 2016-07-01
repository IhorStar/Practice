package ua.nure.starodubets.Practice1;

/**
 * The Part5 class contains methods for determining an ordinal and alphabetic numbers
 *
 */
public class Part5 {

	/**
	 * The method determines an ordinal number of the column
	 * in its an alphabetic number
	 *
	 * @param name an alphabetic number of the column
	 * @return an actual column number
	 */
	public static int charsToDigits(String name) {
		int number = 0;
		for(int i = 0; i < name.length(); i++) {
			number = number * 26 + (name.charAt(i) - ('A' - 1));
		}
		return number;
	}

	/**
	 * The method determines an alphabetic number of the column
	 * in its an ordinal number
	 *
	 * @param number an ordinal number of the column
	 * @return a column name
	 */
	public static String digitsToChars(int number) {
		StringBuilder sb = new StringBuilder();
		while(number-- > 0) {
			sb.append((char)('A' + (number % 26)));
			number /= 26;
		}
		return sb.reverse().toString();
	}

	/**
	 * The method determines a number of the column to the right of the given
	 *
	 * @param number an alphabetic number of the column
	 * @return a right column
	 */
	public static String rightColumn(String number) {
		String chars = "";
		int num;
		num = charsToDigits(number);
		num++;
		chars = digitsToChars(num);
		return chars;
	}

	/**
	 * The method is an entry point into the program
	 *
	 * @param args the string array
	 */
	public static void main(String[] args) {
		String name1 = String.valueOf(args[0]);
		int number = Integer.parseInt(args[1]);
		String name2 = String.valueOf(args[2]);
		System.out.println(Part5.charsToDigits(name1));
		System.out.println(Part5.digitsToChars(number));
		System.out.println(Part5.rightColumn(name2));
	}
}