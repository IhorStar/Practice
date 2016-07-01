package ua.nure.starodubets.Practice6.part3;

/**
 * The class demonstrates the work of a local car park
 *
 * @author Ihor Starodubets
 *
 */
public class Part3 {

	/**
	 * The method is the entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Parking parking = new Parking(10);
		parking.add(1);
		parking.add(2);
		parking.add(3);
		parking.add(4);
		for (int i = 0; i < parking.getArray().length; i++) {
			if (parking.getArray()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArray()[i] + "]");
			}
		}
		System.out.println();
		parking.remove(3);
		for (int i = 0; i < parking.getArray().length; i++) {
			if (parking.getArray()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArray()[i] + "]");
			}
		}
		System.out.println();
		parking.add(5);
		for (int i = 0; i < parking.getArray().length; i++) {
			if (parking.getArray()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArray()[i] + "]");
			}
		}

	}
}
