package ua.nure.starodubets.Practice5;

import ua.nure.starodubets.Practice5.part1.Part1;
import ua.nure.starodubets.Practice5.part2.Part2;
import ua.nure.starodubets.Practice5.part3.Part3;
import ua.nure.starodubets.Practice5.part4.Part4;
import ua.nure.starodubets.Practice5.part5.Part5;
import ua.nure.starodubets.Practice5.part6.Part6;

/**
 * The class class demonstrates the work of all subtasks
 *
 * @author Ihor Starodubets
 *
 */
public class Demo {

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 * @throws Exception if an exception has occurred
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("=========================== Part1");
		Part1.main(args);
		Thread.sleep(6000);

		System.out.println("=========================== Part2");
		Part2.main(args);
		Thread.sleep(1000);

		System.out.println("=========================== Part3");
		Part3.main(args);
		Thread.sleep(1000);

		System.out.println("=========================== Part4");
		Part4.main(args);
		Thread.sleep(1000);

		System.out.println("=========================== Part5");
		Part5.main(args);
		Thread.sleep(1000);

		System.out.println("=========================== Part6");
		Part6.main(args);
	}

}