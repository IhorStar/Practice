package ua.nure.starodubets.Practice6.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * The class compares the time required to remove objects from ArrayList and LinkedList
 *
 * @author Ihor Starodubets
 *
 */
public class Part2 {

	/**
	 * Array capacity
	 */
	private static final int ARRAY_CAPACITY = 50000;

	/**
	 * The method removes objects from ArrayList
	 *
	 * @param persons input String array
	 * @return ArrayList with size 1
	 */
	public ArrayList<String> removePersonUsingArrayList(String[] persons) {

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(persons));
		int numberPersons = list.size();
		while(numberPersons != 1) {
			int removeIndex = (int)(Math.random() * (numberPersons - 1));
			list.remove(removeIndex);
			numberPersons--;
		}
		return list;
	}

	/**
	 * The method removes objects from LinkedList
	 *
	 * @param persons input String array
	 * @return LinkedList with size 1
	 */
	public LinkedList<String> removePersonUsingLinkedList(String[] persons) {

		LinkedList<String> list = new LinkedList<String>(Arrays.asList(persons));
		int numberPersons = list.size();
		while(numberPersons != 1) {
			int removeIndex = (int)(Math.random() * (numberPersons - 1));
			list.remove(removeIndex);
			numberPersons--;
		}
		return list;
	}

	/**
	 * The method fills array
	 *
	 * @param arrayCapacity array capacity
	 * @return filled array
	 */
	public String[] fillArray(int arrayCapacity) {

		String[] array = new String[arrayCapacity];
		for(int i = 0; i < arrayCapacity; i++) {
			array[i] = "Person" + i;
		}

		return array;
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {

		Part2 part2 = new Part2();
		String[] array = part2.fillArray(ARRAY_CAPACITY);
		long start = System.currentTimeMillis();
		part2.removePersonUsingArrayList(array);
		long end = System.currentTimeMillis();
		System.out.println("The time required to remove objects using ArrayList: " + (end - start) + " milliseconds");
		start = System.currentTimeMillis();
		part2.removePersonUsingLinkedList(array);
		end = System.currentTimeMillis();
		System.out.println("The time required to remove objects using LinkedList: " + (end - start) + " milliseconds");
	}
}
