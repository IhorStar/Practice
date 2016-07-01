package ua.nure.starodubets.Practice5.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Part6 extends Thread {

	/**
	 * Number of Threads
	 */
	private static final int THREADS_NUMBER = 10;

	/**
	 * Number of columns
	 */
	private static final int COLUMNS = 20;

	/**
	 * Name of the file
	 */
	private static String fileName = "test.txt";

	/**
	 * An encoding
	 */
	private static String encoding = "cp1251";

	/**
	 * The RandomAccessFile object
	 */
	private RandomAccessFile out;

	/**
	 * A counter
	 */
	private int counter = -1;

	/**
	 * A class constructor
	 *
	 * @param filename name of the file
	 * @param mode the access mode
	 */
	public Part6(String filename, String mode) {
		try {
			out = new RandomAccessFile(fileName, mode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method gets an encoding
	 *
	 * @return an encoding
	 */
	public static String getEncoding() {
		return encoding;
	}

	/**
	 * The method sets an encoding
	 *
	 * @param encoding an encoding
	 */
	public static void setEncoding(String encoding) {
		Part6.encoding = encoding;
	}

	/**
	 * The method gets a counter
	 *
	 * @return a counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * The method sets a counter
	 *
	 * @param counter a counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < COLUMNS; i++) {
				out.write(48 + Integer.parseInt(currentThread().getName()
						.substring(currentThread().getName().length() - 1)));
			}
			setCounter(getCounter() + 1);

			if (getCounter() != 9
					&& !currentThread().getName().equals(
							"Thread-" + (THREADS_NUMBER - 1))) {
				out.write(System.lineSeparator().getBytes(getEncoding()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method writes data to the file
	 */
	public static void writeToFile() {
		for (int i = 0; i < THREADS_NUMBER; i++) {
			Part6 myThread = new Part6(fileName, "rw");
			try {
				myThread.out.seek(i * 22);
			} catch (IOException e) {
				e.printStackTrace();
			}
			myThread.start();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * The method returns a string representation of the files
	 *
	 * @return a string representation of the files
	 */
	public static String getOutput() {
		StringBuilder builder = new StringBuilder();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("test.txt"), getEncoding());
			while (scanner.hasNext()) {
				builder.append(scanner.next());
				builder.append(System.lineSeparator());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.substring(0, builder.length() - System.lineSeparator().length())
				.toString();
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		writeToFile();
		System.out.println(getOutput());

	}
}