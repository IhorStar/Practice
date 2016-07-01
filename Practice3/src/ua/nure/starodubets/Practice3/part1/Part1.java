package ua.nure.starodubets.Practice3.part1;

/**
 * The class contains methods that converts input text data into output
 * and prints the information to console
 *
 * @author Ihor Starodubets
 *
 */
public class Part1 {

	/**
	 * String for convert
	 */
	private static final String INPUT = "Login;Name;Email\n"
			+ "ivanov;Ivan Ivanov;ivanov@mail.ru\n"
			+ "petrov;Petr Petrov;petrov@google.com\n"
			+ "obama;Barack Obama;obama@google.com\n"
			+ "bush;George Bush;bush@mail.ru";

	/**
	 * The method converts input text data into output
	 *
	 * @return the converted String
	 */
	public String convert1() {
		String[] array = INPUT.split("\n");
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i < array.length; i++) {
			String[] array2 = array[i].split(";");
			builder.append(array2[0]+ " ==> " + array2[array2.length-1] + "\n");
		}
		return builder.toString();
	}

	/**
	 * The method converts input text data into output
	 *
	 * @return the converted String
	 */
	public String convert2() {
		String[] array = INPUT.split("\n");
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i < array.length; i++) {
			String[] array2 = array[i].split(";");
			String[] array3 = array2[1].split(" ");
			builder.append(array3[1] + " " + array3[0] + " (email: " + array2[array2.length-1] + ")\n");
		}
		return builder.toString();
	}

	/**
	 * The method converts input text data into output
	 *
	 * @return the converted String
	 */
	public String convert3() {
		String[] array = INPUT.split("\n");
		String mailRuDomain = "mail.ru";
		String googleDomain = "google.com";
		StringBuilder mailRuBuilder = new StringBuilder();
		mailRuBuilder.append(mailRuDomain + " ==> ");
		StringBuilder googleBuilder = new StringBuilder();
		googleBuilder.append(googleDomain + " ==> ");
		for(int i = 1; i < array.length; i++) {
			if(array[i].contains(mailRuDomain)) {
				String[] array2 = array[i].split(";");
				mailRuBuilder.append(array2[0] + ", ");
			} else if(array[i].contains(googleDomain)) {
				String[] array2 = array[i].split(";");
				googleBuilder.append(array2[0] + ", ");
			}
		}

		mailRuBuilder.deleteCharAt(mailRuBuilder.lastIndexOf(","));
		googleBuilder.deleteCharAt(googleBuilder.lastIndexOf(","));

		mailRuBuilder.append("\n" + googleBuilder + "\n");

		return mailRuBuilder.toString();
	}

	/**
	 * The method converts input text data into output
	 *
	 * @return the converted String
	 */
	public String convert4() {
		String[] array = INPUT.split("\n");
		StringBuilder builder = new StringBuilder();
		builder.append(array[0] + ";Password\n");
		for(int i = 1; i < array.length; i++) {
			int password = (int)(Math.random() * 10000);
			builder.append(array[i] + ";" + password + "\n");
		}
		return builder.toString();
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part1 part1= new Part1();
		System.out.println(part1.convert1());
		System.out.println(part1.convert2());
		System.out.println(part1.convert3());
		System.out.println(part1.convert4());
	}
}
