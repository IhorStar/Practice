package ua.nure.starodubets.Practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The class implements the writing to the buffer and
 * further sequential read from the buffer
 *
 * @author Ihor Starodubets
 *
 */
public class Part53 {

	/**
	 * A ReentrantLock object
	 */
	private static final ReentrantLock LOCK = new ReentrantLock();

	/**
	 * Number of iterations
	 */
	private static final int ITERATION_NUMBER = 3;

	/**
	 * Number of readers
	 */
	private static final int READERS_NUMBER = 3;

	/**
	 * A shared resource (not thread-safe!!!)
	 */
	private static final StringBuilder BUFFER = new StringBuilder();

	/**
	 * A length of buffer
	 */
	private static final int BUFFER_LENGTH = 5;

	/**
	 * A speed parameter
	 */
	private static final int PAUSE = 5;

	/**
	 * A stop signal
	 */
	private static boolean stop;

	/**
	 * The class extends Thread. The class is reader.
	 *
	 * @author Ihor Starodubets
	 *
	 */
	private static class Reader extends Thread {

		public void run() {
			while (!stop) {
				try {
					while (true) {
						if (LOCK.tryLock()) {
							read(getName());
							LOCK.unlock();
							sleep(1500);
							break;
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * The class extends Thread. The class  is writer.
	 *
	 * @author Ihor Starodubets
	 *
	 */
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					while (true) {
						if (LOCK.tryLock()) {
							write();
							LOCK.unlock();
							sleep(1300);
							break;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	/**
	 * The method reads data from buffer
	 *
	 * @param threadName name of the Thread
	 * @throws InterruptedException if any thread has interrupted the current thread
	 */
	private static void read(String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFFER.charAt(j));
		}
		System.out.println();
		Thread.sleep(5);
	}

	/**
	 * The method writes data to buffer
	 *
	 * @throws InterruptedException if any thread has interrupted the current thread
	 */
	private static void write() throws InterruptedException {
		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFFER.append(ch);
		}
		System.err.println();
		Thread.sleep(5);
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 * @throws InterruptedException if any thread has interrupted the current thread
	 */
	public static void main(String[] args) throws InterruptedException {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start writer
		Thread.sleep(10);
		writer.start();

		// start readers
		Thread.sleep(10);
		for (Thread reader : readers) {
			reader.start();
		}

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
	}
}
