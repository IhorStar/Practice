package ua.nure.starodubets.Practice2;

/**
 * The interface similar to but not as complete as java.util.List
 *
 * @author Ihor Starodubets
 *
 */
public interface MyList extends Iterable<Object>{

	/**
	 * The method appends the specified element to the end of this list
	 *
	 * @param e an element
	 */
	void add(Object e);

	/**
	 * The method removes all of the elements from this list
	 */
	void clear();

	/**
	 * The method removes the first occurrence of the specified element from this list
	 *
	 * @param o an occurrence
	 * @return true if this list contained the specified element
	 */
	boolean remove(Object o);

	/**
	 * The method returns an array containing all of the elements in this list in proper sequence
	 *
	 * @return an array
	 */
	Object[] toArray();

	/**
	 * The method returns the number of elements in this list
	 *
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * The method returns true if this list contains the specified element
	 *
	 * @param o element
	 * @return true if this list contains the specified element
	 */
	boolean contains(Object o);

	/**
	 * The method returns true if this list contains all of the elements of the specified list
	 *
	 * @param c list
	 * @return true if this list contains all of the elements of the specified list
	 */
	boolean containsAll(MyList c);
}
