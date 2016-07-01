package ua.nure.starodubets.Practice2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class is an implementation of MyList and ListIterable
 *
 * @author Ihor Starodubets
 *
 */
public class MyListImpl implements MyList, ListIterable {

	/**
     * The array buffer into which the elements of the MyListImpl are stored
     * The capacity of the MyListImpl is the length of this array buffer
     */
    private Object[] elementData;

    /**
     * Default capacity of the array
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the MyListImpl
     */
    private int size;

    /**
	 * Constructs a MyListImpl with a specified capacity
	 *
	 * @param initialCapacity the initial capacity of the list
	 */
	public MyListImpl (int initialCapacity) {
		elementData = (Object[]) new Object[initialCapacity];
	}

	/**
	 * Constructs a MyArrayList with a default capacity
	 */
	public MyListImpl() {
		this(DEFAULT_CAPACITY);
	}


	/**
	 * The method appends the specified element to the end of this list
	 *
	 * @param e an element
	 */
	@Override
	public void add(Object e) {
		if(size == elementData.length) {
			Object[] temp = new Object[elementData.length + DEFAULT_CAPACITY];
			for(int i = 0; i < elementData.length; i++) {
				temp[i] = elementData[i];
			}
			elementData = temp;
		}
		elementData[size] = e;
		size++;
	}

	/**
	 * The method removes all of the elements from this list
	 */
	@Override
	public void clear() {
		elementData = new Object[DEFAULT_CAPACITY];
		size = 0;
	}


	/**
	 * The method removes the first occurrence of the specified element from this list
	 *
	 * @param o an occurrence
	 * @return true if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index != -1) {
			for(int i = index + 1; i < size; i++) {
				elementData[i - 1] = elementData[i];
			}
			size--;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * The method returns an array containing all of the elements in this list in proper sequence
	 *
	 * @return an array
	 */
	@Override
	public Object[] toArray() {
		Object[] array = this.elementData;
		return array;
	}

	/**
	 * The method returns the number of elements in this list
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * The method returns true if this list contains the specified element
	 *
	 * @param o element
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	/**
	 * The method returns true if this list contains all of the elements of the specified list
	 *
	 * @param c list
	 * @return true if this list contains all of the elements of the specified list
	 */
	@Override
	public boolean containsAll(MyList c) {
		Object[] list = c.toArray();
		for(Object object : list) {
			if(!contains(object)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 *
	 * @param o element to search for
	 * @return the index of the specified element
	 */
	public int indexOf(Object o) {
		if(o == null) {
			for(int i = 0; i < size; i++) {
				if(elementData[i] == null) {
					return i;
				}
			}
		} else {
			for(int i = 0; i < size; i++) {
				if(o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * The method returns a string representation of the object
	 *
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for (int i = 0; i < toArray().length; i++) {
			if (toArray()[i] == null) {
				builder.append("[" + "null" + "],");
				continue;
			}
			builder.append("[" + toArray()[i].toString() + "],");
		}
		builder.deleteCharAt(builder.lastIndexOf(","));
		builder.append("}");
		return builder.toString();
	}

	private class IteratorImpl implements Iterator<Object> {

		/**
		 * Index of next element to return
		 */
		int cursor;

		/**
		 * Index of last element returned; -1 if no such
		 */
		int lastRet = -1;

		/**
		 * The method returns true if the iteration has more elements
		 *
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		/**
		 * The method returns the next element in the iteration
		 *
		 * @return the next element in the iteration
		 */
		@Override
		public Object next() {
			int i = cursor;
			if(i >= size) {
				throw new NoSuchElementException();
			}
			Object[] elementData = MyListImpl.this.elementData;
			if(i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i + 1;
			return elementData[lastRet = i];
		}

		/**
		 * The method removes the last element returned by this iterator
		 */
		@Override
		public void remove() {
			if(lastRet < 0) {
				throw new IllegalStateException();
			}
			MyListImpl.this.remove(elementData[lastRet]);
			cursor = lastRet;
			lastRet = -1;
		}
	}

	/**
	 * The method returns an iterator over a set of elements
	 *
	 * @return an iterator
	 */
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		/**
		 * The method returns true if this list iterator has more elements when traversing the list in the reverse direction
		 *
		 * @return true if this list iterator has more elements when traversing the list in the reverse direction
		 */
		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		/**
		 * The method returns the previous element in the list and moves the cursor position backwards
		 *
		 * @return the previous element in the list and moves the cursor position backwards
		 */
		@Override
		public Object previous() {
			int i = cursor - 1;
			if(i < 0) {
				throw new NoSuchElementException();
			}
			Object[] elementData = MyListImpl.this.elementData;
			if(i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i;
			return elementData[i];
		}

		/**
		 * The method replaces the last element returned by next or previous with the specified element
		 *
		 * @param e element
		 */
		@Override
		public void set(Object e) {
			remove();
			MyListImpl.this.add(e);
		}

		/**
		 * The method removes the last element returned by this iterator
		 */
		@Override
		public void remove() {
			super.remove();
		}
	}

	/**
	 * The method returns an iterator over a set of elements
	 *
	 * @return an iterator
	 */
	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}
}
