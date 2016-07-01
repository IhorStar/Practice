package ua.nure.starodubets.Practice6.part1;

import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The class reads text from the console input and outputs
 * words in descending order of their frequency of occurrence in the text
 *
 * @author Ihor Starodubets
 *
 */
public class Part1 {

	/**
	 * The string array
	 */
	private String[] array;

	/**
	 * The container
	 */
	private WordContainer container = new WordContainer();

	/**
	 * The class constructor
	 */
	public Part1() {
		array = read();
		fill();
	}

	/**
	 * The method reads an input data
	 *
	 * @return a String array
	 */
	public String[] read() {
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(System.in, System.getProperty("file.encoding"));
		while (scanner.hasNextLine()) {
			builder.append(scanner.nextLine());
			break;
		}
		scanner.close();
		return builder.toString().split(" ");
	}

	/**
	 * The method fills the container and sorts it
	 */
	public void fill() {
		for (int i = 0; i < array.length; i++) {
			container.add(new Word(array[i]));
		}
		Collections.sort(container.getList(), new CompareByWord());
		Collections.sort(container.getList(), new CompareByFrequency());
	}

	/**
	 * The method gets an output data and returns a string representation the data
	 *
	 * @return a string representation the data
	 */
	public String getOutput() {
		StringBuilder builder = new StringBuilder();
		for (Iterator<Word> iterator = container.getList().iterator(); iterator.hasNext();) {
			Word word = iterator.next();
			builder.append(word.getWord() + " : " + word.getFrequency()
					+ System.lineSeparator());

		}
		return builder.toString();
	}

	/**
	 * The method is the entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part1 part1 = new Part1();
		System.out.println(part1.getOutput());
	}
}
