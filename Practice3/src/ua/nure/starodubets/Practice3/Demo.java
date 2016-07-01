package ua.nure.starodubets.Practice3;

import java.security.NoSuchAlgorithmException;

import ua.nure.starodubets.Practice3.part1.Part1;
import ua.nure.starodubets.Practice3.part2.Part2;
import ua.nure.starodubets.Practice3.part3.Part3;
import ua.nure.starodubets.Practice3.part4.Part4;
import ua.nure.starodubets.Practice3.part5.Part5;

/**
 * The class class demonstrates the work of all subtasks
 *
 * @author Ihor Starodubets
 *
 */
public class Demo {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		System.out.println("=========================== PART1");
		Part1.main(args);

		System.out.println("=========================== PART2");
		Part2.main(args);

		System.out.println("=========================== PART3");
		Part3.main(args);

		System.out.println("=========================== PART4");
		Part4.main(args);

		System.out.println("=========================== PART5");
		Part5.main(args);

	}
}
