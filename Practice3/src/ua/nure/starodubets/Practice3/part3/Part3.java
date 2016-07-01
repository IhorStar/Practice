package ua.nure.starodubets.Practice3.part3;

/**
 * The class contains a method which puts the first character of each word to uppercase
 *
 * @author Ihor Starodubets
 *
 */
public class Part3 {

	private static final String TEXT = "When I was younger\nI never needed";

	/**
	 * The method puts the first character of each word to uppercase
	 * @param array input array
	 * @return String with uppercase of the first character of each word
	 */
	public String toUpperCaseEachWord(String[] array) {
		StringBuilder result = new StringBuilder();
		for(String word : array) {
			char[] stringArray = word.trim().toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			word = new String(stringArray);
			result.append(word).append(" ");
		}

		return result.toString();
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part3 part3 = new Part3();
		String[] array = TEXT.split(" ");
		System.out.println(part3.toUpperCaseEachWord(array));
	}

}
