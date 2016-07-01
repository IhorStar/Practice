package ua.nure.starodubets.Practice1;

/**
 * The Demo class demonstrates the work of five subtasks
 *
 */
public class Demo {

	/**
	 * The method is an entry point into the program.
	 * It demonstrates the work of five subtasks
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		System.out.println("=========================== PART1");
		Part1.main(new String[] {});

		System.out.println("=========================== PART2");
		Part2.main(new String[] {"3", "7.5"});

		System.out.println("=========================== PART3");
		Part3.main(new String[] {"6", "12"});

		System.out.println("=========================== PART4");
		Part4.main(new String[] {"12"});

		System.out.println("=========================== PART5");
		Part5.main(new String[] {"AA", "3", "BB"});
	}
}
