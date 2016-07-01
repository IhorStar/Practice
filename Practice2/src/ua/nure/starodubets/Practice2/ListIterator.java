package ua.nure.starodubets.Practice2;

import java.util.Iterator;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface ListIterator extends Iterator<Object> {

	/**
	 * The method returns true if this list iterator has more elements when traversing the list in the reverse direction
	 *
	 * @return true if this list iterator has more elements when traversing the list in the reverse direction
	 */
	boolean hasPrevious();

	/**
	 * The method returns the previous element in the list and moves the cursor position backwards
	 *
	 * @return the previous element in the list and moves the cursor position backwards
	 */
	Object previous();

	/**
	 * The method replaces the last element returned by next or previous with the specified element
	 *
	 * @param e element
	 */
	void set(Object e);


	/**
	 * The method removes from the list the last element that was returned by next or previous
	 */
	void remove();

}
