package ua.nure.starodubets.Practice4.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The class creates and populates a file with random integers
 * from 0 to 50 (only 10 numbers), then reads the file and outputs
 * its contents to another file, sort the numbers in ascending order
 *
 * @author Ihor Starodubets
 *
 */
public class Part2 {

	/**
	 * A random number generator
	 */
	private Random random = new Random();

	/**
	 * An encoding
	 */
	private String encoding = "CP1251";

	/**
	 * Name of the input file
	 */
	private String inputFileName;

	/**
	 * Name of the output file
	 */
	private String outputFileName;

	/**
	 * Numbers of integers for writing in a file
	 */
	private int n = 10;

	/**
	 * A maximum value for integers
	 */
	private int max = 50;

	/**
	 * A class constructor
	 *
	 * @param fileName name of the input file
	 * @param fileName2 name of the output file
	 * @param encoding an encoding
	 */
	Part2(String inputFileName, String outputFileName, String encoding) {
		setInputFileName(inputFileName);
		setOutputFileName(outputFileName);
		setEncoding(encoding);
	}

	/**
	 * The method gets a random number generator
	 *
	 * @return a random number generator
	 */
	public Random getRandom() {
		return random;
	}

	/**
	 * The method gets an encoding
	 *
	 * @return an encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * The method sets an encoding
	 *
	 * @param encoding an input encoding
	 */
	public final void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * The method gets name of the input file
	 *
	 * @return name of the input file
	 */
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * The method sets name of the input file
	 *
	 * @param fileName name of the input file
	 */
	public final void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	/**
	 * The method gets name of the output file
	 * @return
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * The method sets name of the output file
	 *
	 * @param fileName2 name of the output file
	 */
	public final void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/**
	 * The method gets numbers of integers for writing in a file
	 *
	 * @return numbers of integers for writing in a file
	 */
	public int getN() {
		return n;
	}

	/**
	 * The method gets a maximum value for integers
	 *
	 * @return a maximum value for integers
	 */
	public int getMax() {
		return max;
	}

	/**
	 * The method sorts numbers in ascending order
	 *
	 * @param str an input string
	 * @return a sorted string
	 */
	public String sortNumbers(String str) {
		String[] array = str.split(" ");
		int[] array2 = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			array2[i] = Integer.valueOf(array[i]);
		}
		Arrays.sort(array2);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array2[i] + " ");
		}
		return builder.toString();
	}

	/**
	 * The method fills a string random integers from 0 to 50
	 *
	 * @return a filled string
	 */
	public String fillRandomNumbers() {
		int count = 0;
		StringBuilder builder = new StringBuilder();
		while (count != getN()) {
			builder.append(calculateRandomInt() + " ");
			count++;
		}

		return builder.toString().substring(0, builder.length()-1);
	}

	/**
	 * The method calculates random integer
	 *
	 * @return integer
	 */
	public int calculateRandomInt() {
		return getRandom().nextInt(getMax());

	}

	/**
	 * The method writes data in an input file
	 */
	public void writeInputFile() {
		try {
			PrintWriter writer = new PrintWriter(new File(getInputFileName()),
					getEncoding());
			writer.write(fillRandomNumbers());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method writes data in an output file
	 */
	public void writeOutputFile() {
		try {
			Scanner scanner = new Scanner(new File(getInputFileName()), getEncoding());
			PrintWriter writer = new PrintWriter(new File(getOutputFileName()),
					getEncoding());
			while (scanner.hasNextLine()) {
				writer.write(sortNumbers(scanner.nextLine()));
			}
			scanner.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method reads input and output files and returns a string representation of this files
	 *
	 * @return a string representation of files
	 */
	public String output() {
		String str = null;
		try {
			StringBuilder builder = new StringBuilder();
			Scanner scanner = new Scanner(new File(getInputFileName()), getEncoding());
			while (scanner.hasNextLine()) {
				builder.append("input: " + scanner.nextLine() + System.lineSeparator());
			}
			scanner = new Scanner(new File(getOutputFileName()), getEncoding());
			while (scanner.hasNextLine()) {
				builder.append("output: " + scanner.nextLine());
			}
			scanner.close();
			str = builder.toString().substring(0, builder.length() - 1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part2 part2 = new Part2("part2.txt", "part2_sorted.txt", "CP1251");
		part2.writeInputFile();
		part2.writeOutputFile();
		System.out.println(part2.output());

	}
}
