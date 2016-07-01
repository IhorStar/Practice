package ua.nure.starodubets.Practice4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import ua.nure.starodubets.Practice4.part1.Part1;
import ua.nure.starodubets.Practice4.part2.Part2;
import ua.nure.starodubets.Practice4.part3.Part3;
import ua.nure.starodubets.Practice4.part4.Part4;
import ua.nure.starodubets.Practice4.part5.Part5;

/**
 * The class class demonstrates the work of all subtasks
 *
 * @author Ihor Starodubets
 *
 */
public class Demo {

	/**
	 * An input stream
	 */
	private static final InputStream STD_IN = System.in;

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
	 * @param encoding an encoding
	 */
	public static void setEncoding(String encoding) {
		Demo.encoding = encoding;
	}

	public static void main(String[] args) {

		try {
			System.out.println("=========================== PART1");
			Part1.main(args);

			System.out.println("=========================== PART2");
			Part2.main(args);

			System.out.println("=========================== PART3");
			System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble"
					.getBytes(getEncoding())));
			Part3.main(args);
			System.setIn(STD_IN);

			System.out.println("=========================== PART4");
			Part4.main(args);

			System.out.println("=========================== PART5");
			System.setIn(new ByteArrayInputStream(
					"table ru\ntable en\napple ru".getBytes(getEncoding())));
			Part5.main(args);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding in Demo.java");
		}
	}
}
