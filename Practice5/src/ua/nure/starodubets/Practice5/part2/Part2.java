package ua.nure.starodubets.Practice5.part2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part2 {

	/**
	 * An encoding
	 */
	private static String encoding = "cp1251";

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
	 * @param encoding an input encoding
	 */
	public static void setEncoding(String encoding) {
		Part2.encoding = encoding;
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		// save standard input
		InputStream stdIn = System.in;

		// create input stream with line terminator (=ENTER)
		ByteArrayInputStream bais;
		try {
			bais = new ByteArrayInputStream(System.lineSeparator().getBytes(
					getEncoding()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// main functionality
		Spam.main(args);

		// assign new value of standard input
		System.setIn(bais);
		// wait for 5 seconds
		try {
			Thread.sleep(5001);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Spam.setFlag(true);
		System.out.println("Try to send enter to standard input");
		// move internal pointer to begin of input
		bais.reset();

		// restore standard input
		System.setIn(stdIn);
	}
}