package ua.nure.starodubets.Practice5.part3;

/**
 * The class contains methods which prints to the console the result
 * of the comparison of two counters
 *
 * @author Ihor Starodubets
 *
 */
public class Part3 {

	/**
	 * A first counter
	 */
	private int firstCounter;

	/**
	 * A second counter
	 */
	private int secondCounter;

	/**
	 * The method gets a first counter
	 *
	 * @return a first counter
	 */
	public int getFirstCounter() {
		return firstCounter;
	}

	/**
	 * The method sets a first counted
	 *
	 * @param counter a first counter
	 */
	public void setFirstCounter(int counter) {
		this.firstCounter = counter;
	}

	/**
	 * The method gets a second counter
	 *
	 * @return a second counter
	 */
	public int getSecondCounter() {
		return secondCounter;
	}

	/**
	 * The method sets a second counter
	 *
	 * @param counter a second counter
	 */
	public void setSecondCounter(int counter) {
		this.secondCounter = counter;
	}

	/**
	 * The method prints to the console the result of the comparison of two counters
	 */
	public void output() {

		setFirstCounter(getFirstCounter() + 1);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setSecondCounter(getSecondCounter() + 1);
		System.out.print(firstCounter - secondCounter);
	}

	/**
	 * The method prints to the console the result of the synchronized comparison of two counters
	 */
	public synchronized void synchronizedOutput() {

		setFirstCounter(getFirstCounter() + 1);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setSecondCounter(getSecondCounter() + 1);
		System.out.print(firstCounter - secondCounter);
	}

	/**
	 * The method prints to the console the result of the synchronized and
	 * not synchronized comparison of two counters
	 */
	public void result() {
		final Part3 obj = new Part3();
		System.out.println("Synchronized output: ");
		for (int i = 0; i < 10; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.synchronizedOutput();
					System.out.println();
				};
			};
			myThread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Not synchronized output: ");
		for (int i = 0; i < 10; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.output();
					System.out.println();
				};
			};
			myThread.start();
		}
	}

	public static void main(String[] args) {
		new Part3().result();
	}
}
