package ua.nure.starodubets.Practice4.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class outputs the contents of a text file into the console,
 * replacing in every word longer than three characters all lowercase characters in uppercase
 *
 * @author Ihor Starodubets
 *
 */
public class Part1 {

	/**
	 * An encoding
	 */
	private static final String ENCODING = "CP1251";

	/**
	 * Name of the file
	 */
	private String fileName;

	/**
	 * A class constructor
	 *
	 * @param fileName name of the file
	 */
	Part1(String fileName) {
		setFileName(fileName);
	}

	/**
	 * The method gets name of the file
	 *
	 * @return name of the file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * The method sets name of the file
	 *
	 * @param fileName name of the file
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The method replaces in every word longer than three
	 * characters all lowercase characters in uppercase
	 */
	public void convertToUpperCase() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(getFileName()), ENCODING);
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine() + System.lineSeparator());
				Pattern p = Pattern.compile("[À-ßà-ÿ\\w]{3,}");
				Matcher m = p.matcher(builder);

				while (m.find()) {
					builder.replace(m.start(), m.end(), m.group().toUpperCase());
				}
			}
			System.out.println(builder.toString().substring(0, builder.length() - 2));
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		new Part1("part1.txt").convertToUpperCase();
	}
}
