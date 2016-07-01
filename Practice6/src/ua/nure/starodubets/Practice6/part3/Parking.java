package ua.nure.starodubets.Practice6.part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class simulates the work of a local car park
 *
 * @author Ihor Starodubets
 *
 */
public class Parking {

	/**
	 * The Object array
	 */
	private Object[] array;

	/**
	 * The list
	 */
	private List<Object> list;

	/**
	 * The class constructor
	 *
	 * @param number number of the car
	 */
	public Parking(int number) {
		list = new ArrayList<>(number);
		array = new Object[number];
	}

	/**
	 * The method gets the Object array
	 *
	 * @return the Object array
	 */
	public Object[] getArray() {
		return Arrays.copyOf(array, array.length);
	}

	/**
	 * The method adds objects to the array
	 *
	 * @param obj an Object
	 */
	public void add(Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = obj;
				System.out.println(obj + " has come");
				break;
			}
		}
	}

	/**
	 * The method removes objects from the array
	 *
	 * @param obj
	 */
	public void remove(Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (obj.equals(array[i])) {
				System.out.println(array[i] + " removed");
				array[i] = null;
				break;
			}
		}

	}
}
