package ua.nure.starodubets.Practice5.part2;

import java.io.IOException;

/**
 * The class takes an array of time intervals in milliseconds and agreed with
 * it an array of messages and displays the appropriate message on the screen
 * at specified intervals of time.
 *
 * @author Ihor Starodubets
 *
 */
public class Spam extends Thread {

	/**
	 * A flag
	 */
	private static boolean flag;

	/**
	 * An array of time
	 */
	private static int[] time = new int[] { 1000, 1000, 1000, 1000, 1000, 1000 };

	/**
	 * An array of messages
	 */
	private static String[] messages = new String[] { "message1", "message2",
			"message3", "message4", "message5", "message6" };

	/**
	 * The method gets a flag
	 *
	 * @return a flag
	 */
	public static boolean isFlag() {
		return flag;
	}

	/**
	 * The method sets a flag
	 *
	 * @param flag a flag
	 */
	public static void setFlag(boolean flag) {
		Spam.flag = flag;
	}

	/**
	 * The method prints messages to the console at specified intervals of time
	 */
	public void run() {
		while (true) {
			try {
				for (int i = 0; i < messages.length; i++) {
					System.out.println(messages[i]);
					Thread.sleep(time[i]);
					if (flag) {
						break;
					}
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The method starts the Thread
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		final Spam spam = new Spam();
		spam.setDaemon(true);
		spam.start();

		new Thread() {
			public void run() {
				byte[] buffer = new byte[10];
				int count;
				try {
					do {

						while ((count = System.in.read(buffer)) == -1) {
							if (count == -1) {
								continue;
							}
						}
					} while (!System.lineSeparator().equals(
							new String(buffer, 0, count, "cp1251")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("ENTER has been obtained");
			}
		}.start();
	}
}