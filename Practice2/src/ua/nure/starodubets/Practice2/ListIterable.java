package ua.nure.starodubets.Practice2;

/**
 * Implementing this interface allows an object to be the target of the "foreach" statement
 *
 * @author Ihor Starodubets
 *
 */
public interface ListIterable {

	/**
	 * The method returns an iterator over a set of elements
	 *
	 * @return an iterator
	 */
	ListIterator listIterator();
}
