package ua.nure.starodubets.Practice4.part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class contains methods for reading data from a file
 * and printing these data to the console
 *
 * @author Ihor Starodubets
 *
 */
public class Part3 {

	/**
	 * An encoding
	 */
	private static final String ENCODING = "CP1251";

	/**
	 * Name of an input file
	 */
	private String fileName;

	/**
	 * A class constructor
	 *
	 * @param fileName name of an input file
	 */
	Part3(String fileName) {
		setFileName(fileName);
	}

	/**
	 * The method gets name of an input file
	 *
	 * @return name of an input file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * The method sets name of an input file
	 *
	 * @param fileName name of an input file
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * A regular expression for Integer
	 */

	private static final String REGEXP_FOR_INTEGER = "(^|\\s)(\\d+)(\\s|$)";
	/**
	 * A regular expression for Double
	 */
	private static final String REGEXP_FOR_DOUBLE = "(^|\\s)([\\d+]*\\.\\d+)(\\s|$)";

	/**
	 * A regular expression for Character
	 */
	private static final String REGEXP_FOR_CHAR = "(?i)(^|(?<=\\s))[a-zà-ÿ]($|(?=\\s))";

	/**
	 * A regular expression for String
	 */
	private static final String REGEXP_FOR_STRING = "[À-ßà-ÿa-zA-Z]{2,}";

	/**
	 * The method reads file and returns a string representation of the file
	 *
	 * @return A string representation of the file
	 */
	public String getString() {
		String str = null;
		try {
			Scanner scanner = new Scanner(new File(getFileName()), ENCODING);
			while (scanner.hasNextLine()) {
				str = scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * The method gets a string which contains only Integer values
	 *
	 * @return a string which contains only integers value
	 */
	public String getIntegerValue() {
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEXP_FOR_INTEGER);
		Matcher matcher = pattern.matcher(getString());
		while (matcher.find()) {
			builder.append(matcher.group(2) + " ");
		}
		return builder.toString().substring(0, builder.length() - 1);
	}

	/**
	 * The method gets a string which contains only Double values
	 *
	 * @return a string which contains only double value
	 */
	public String getDoubleValue() {
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEXP_FOR_DOUBLE);
		Matcher matcher = pattern.matcher(getString());
		while (matcher.find()) {
			builder.append(matcher.group(2) + " ");
		}
		return builder.toString().substring(0, builder.length() - 1);

	}

	/**
	 * The method gets a string which contains only Character values
	 *
	 * @return a string which contains only character value
	 */
	public String getCharValue() {
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEXP_FOR_CHAR);
		Matcher matcher = pattern.matcher(getString());
		while (matcher.find()) {
			builder.append(matcher.group() + " ");
		}
		return builder.toString().substring(0, builder.length() - 1);

	}

	/**
	 * The method gets a string which contains only String values
	 *
	 * @return a string which contains only character value
	 */
	public String getStringValue() {
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEXP_FOR_STRING);
		Matcher matcher = pattern.matcher(getString());
		while (matcher.find()) {
			builder.append(matcher.group() + " ");
		}
		return builder.toString().substring(0, builder.length() - 1);

	}

	/**
	 * The method gets an input value and prints all values which appropriate
	 * the input value
	 */
	public void input() {
		Scanner scanner = new Scanner(System.in, ENCODING);
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			if ("int".equals(str)) {
				System.out.println(getIntegerValue());
			} else if ("double".equals(str)) {
				System.out.println(getDoubleValue());
			} else if ("char".equals(str)) {
				System.out.println(getCharValue());
			} else if ("String".equals(str)) {
				System.out.println(getStringValue());
			} else if ("stop".equals(str)) {
				System.out.println("End of input");
				return;
			} else {
				System.out.println("No such element");
			}
		}
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part3 part3 = new Part3("part3.txt");
		part3.input();
	}
}
