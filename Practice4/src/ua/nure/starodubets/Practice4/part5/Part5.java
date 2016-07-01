package ua.nure.starodubets.Practice4.part5;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * The class in the loop reads the key from the console and name of
 * localization, then prints the appropriate value to the console
 *
 * @author Ihor Starodubets
 *
 */
public class Part5 {

	/**
	 * The base name of the resource bundle
	 */
	private static final String FILE_NAME = "resources";

	/**
	 * The method reads a key and name of a localization
	 * from the console, then prints the appropriate value to the console
	 *
	 */
	public void output() {
		Scanner scanner = new Scanner(System.in,"cp1251");
		while (scanner.hasNext()) {
			try {
				String[] arr = scanner.nextLine().split(" ");
				if (arr[0].equalsIgnoreCase("stop")) {
					System.out.println("Finished");
					break;
				}
				if (arr.length != 2) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale locale = new Locale(arr[1].toLowerCase());
				ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME, locale);
				System.out.println(resourceBundle.getString(arr[0]));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Output required: [key] [value]");
				continue;
			} catch (MissingResourceException e) {
				System.out.println("Invalid key");
				continue;
			}
		}
		scanner.close();
	}


	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		new Part5().output();
	}
}
