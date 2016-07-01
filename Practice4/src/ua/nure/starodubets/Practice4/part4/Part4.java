package ua.nure.starodubets.Practice4.part4;

import java.io.File;

/**
 * The class contains method which prints sentences from the file
 *
 * @author Ihor Starodubets
 *
 */
public class Part4 {

	/**
	 * Name of an input file
	 */
	private String fileName;

	/**
	 * A class constructor
	 *
	 * @param filename name of an input file
	 */
	Part4(String filename) {
		setFileName(filename);
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
	 * The method name of an input file
	 *
	 * @param fileName name of an input file
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The method prints sentences from the file
	 */
	public void output() {
		Parser parser = new Parser(new File(getFileName()));
		for (String str : parser) {
			System.out.println(str);
		}

	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		new Part4("part4.txt").output();
	}
}
