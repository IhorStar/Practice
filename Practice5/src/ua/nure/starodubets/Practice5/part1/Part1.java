package ua.nure.starodubets.Practice5.part1;

/**
 * The class demonstrates how to create an instance of Thread in two ways
 *
 * @author Ihor Starodubets
 *
 */
public class Part1 {

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 * @throws InterruptedException if any thread has interrupted the current thread
	 */
	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread() {
			public void run() {
				for(int i = 0; i < 10; i++) {
					try {
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();

		Thread runnable = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < 10; i++) {
					try {
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		runnable.start();
	}
}