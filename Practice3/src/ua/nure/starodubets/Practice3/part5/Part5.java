package ua.nure.starodubets.Practice3.part5;

/**
 * The class contains methods to convert from decimal number system to the Roman and back
 *
 * @author Ihor Starodubets
 *
 */
public class Part5 {

	private static final String[] ROMANS = new String[] {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
	private static final int[] DECIMAL = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100};

	/**
	 * The method converts from decimal number system to the Roman
	 *
	 * @param decimal input decimal number
	 * @return Roman numerals
	 */
	public static String decimalToRoman(int decimal) {
		StringBuilder builder = new StringBuilder();
		int times = 0;
		for(int i = DECIMAL.length - 1; i >= 0; i--) {
			times = decimal / DECIMAL[i];
			decimal %= DECIMAL[i];
			while(times > 0) {
				builder.append(ROMANS[i]);
				times--;
			}
		}
		return builder.toString();
	}

	/**
	 * The method converts from Roman number system to decimal
	 *
	 * @param roman input Roman numeral
	 * @return decimal number
	 */
	public static int romanToDecimal(String roman) {
		int decimal = 0;
		for(int i = 0; i < ROMANS.length; i++) {
			if(roman.startsWith(ROMANS[i])) {
				decimal = DECIMAL[i] + romanToDecimal(roman.substring(ROMANS[i].length()));
			}
		}
		return decimal;
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++) {
			System.out.println(i + " ====> " + decimalToRoman(i) + " ====> " + romanToDecimal(decimalToRoman(i)));
		}
	}
}
