package ua.nure.starodubets.Practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ihor Starodubets
 *
 */
public class Part6 {

	/**
	 * Name of the file
	 */
	private String filename;

	/**
	 * A text
	 */
	private String text;

	/**
	 * A Map object
	 */
	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * A SortByValue object
	 */
	private SortByValue sortByValue = new SortByValue(map);

	/**
	 * A Map object
	 */
	private Map<String, Integer> map2 = new TreeMap<String, Integer>(sortByValue);

	/**
	 * A String array
	 */
	private String[] array;

	/**
	 * The method gets name of the file
	 *
	 * @return name of the file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * The method sets name of the file
	 *
	 * @param filename name of the file
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * The method initializes an array
	 */
	public void arrayInitialize() {

		try {
			StringBuilder builder = new StringBuilder();
			Scanner scanner = new Scanner(new File(getFilename()), "CP1251");
			while (scanner.hasNext()) {
				builder.append(scanner.next() + " ");
			}
			text = builder.toString();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		array = initialize();

	}

	/**
	 * The method returns String array from text
	 *
	 * @return String array
	 */
	public String[] initialize() {
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile("[A-za-zÀ-ßà-ÿ]+");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			builder.append(matcher.group() + " ");
		}
		return builder.toString().split(" ");

	}

	/**
	 * The method searches in the input file three words that occur most frequently
	 */
	public void frequency() {
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				int local = map.get(array[i]) + 1;
				map.put(array[i], local);
				continue;
			}
			map.put(array[i], 1);
		}
		map2.putAll(map);
	}

	/**
	 * The method searches in the input file three longest word
	 */
	public void length() {
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				continue;
			}
			map.put(array[i], array[i].length());
		}
		map2.putAll(map);
	}

	/**
	 * The method makes the inversion of words
	 *
	 * @param str input String
	 * @return reversed String
	 */
	public String inverseString(String str) {
		StringBuilder builder = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			builder.append(String.valueOf(str.charAt(i)).toUpperCase());
		}
		return builder.toString();
	}

	/**
	 * The method searches in the input file the first three words that have duplicates
	 * and makes the inversion of words
	 *
	 * @return reversed String
	 */
	public String inverse() {
		int counter = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; counter < 3 && i < array.length; i++) {
			if (map.containsKey(array[i])) {
				builder.append(inverseString(array[i]) + System.lineSeparator());
				counter++;
				continue;
			}
			map.put(array[i], array[i].length());
		}
		return builder.toString();
	}

	/**
	 * The method prints to the console the processed data
	 */
	public void output() {
		int i = 0;
		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
			if (i == 3) {
				break;
			}
			System.out.println(entry.getKey() + " ==> " + entry.getValue());
			i++;
		}
	}

	/**
	 * The method returns true if success
	 *
	 * @param input an input
	 * @param filename name of the file
	 * @param task a task
	 * @param operation an operation
	 * @return true if success
	 */
	public boolean consoleOutput(String input, String filename, String task, String operation) {
		if (!(input.equals("--input") || input.equals("-i"))) {
			System.err.println("Wrong operation");
			return false;
		}
		setFilename(filename);
		if (!(task.equals("--task") ||task.equals("-t"))) {
			System.err.println("Wrong task");
			return false;
		}
		if (operation.equals("frequency")) {
			this.arrayInitialize();
			this.frequency();
			this.output();
			return true;
		} else if (operation.equals("length")) {
			this.arrayInitialize();
			this.length();
			this.output();
			return true;
		} else if (operation.equals("duplicates")) {
			this.arrayInitialize();
			System.out.println(this.inverse());
		}
		return true;
	}

	/**
	 * The class implements Comparator
	 *
	 * @author Ihor Starodubets
	 *
	 */
	private static class SortByValue implements Comparator<String> {

		/**
		 * The Map object
		 */
		private Map<String, Integer> map;

		/**
		 * The class constructor
		 *
		 * @param map Map object
		 */
		public SortByValue(Map<String, Integer> map) {
			this.map = map;
		}

		/**
		 * Compares its two arguments for order
		 */
		@Override
		public int compare(String str1, String str2) {
			if (map.get(str1) >= map.get(str2)) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	/**
	 * The method is the entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part6 part6 = new Part6();
		part6.consoleOutput(args[0], args[1], args[2], args[3]);
	}
}
