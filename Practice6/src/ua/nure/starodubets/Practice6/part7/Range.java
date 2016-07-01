package ua.nure.starodubets.Practice6.part7;

import java.util.Arrays;
import java.util.Iterator;

/**
 * The class implements Iterable
 *
 * @author Ihor Starodubets
 *
 */
public class Range implements Iterable<Integer> {

	/**
	 * The reverse
	 */
	private boolean reverse;

	/**
	 * The start
	 */
	private int start;

	/**
	 * The end
	 */
	private int end;

	/**
	 * The int array
	 */
	private int[] array;

	/**
	 * The class constructor
	 *
	 * @param start a start
	 * @param end an end
	 * @param reverse
	 */
	Range(int start, int end, boolean reverse) {
		setStart(start);
		setEnd(end);
		setReverse(reverse);
		array = new int[getEnd() - getStart() + 1];
		fillArray();
	}

	/**
	 * The method gets an array
	 *
	 * @return an array
	 */
	public int[] getArray() {
		return Arrays.copyOf(array, array.length);
	}

	/**
	 * The method returns reverse
	 *
	 * @return reverse
	 */
	public boolean isReverse() {
		return reverse;
	}

	/**
	 * The method sets reverse
	 *
	 * @param reverse reverse
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	/**
	 * The method gets start
	 *
	 * @return start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * The method sets start
	 *
	 * @param start start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * The method gets end
	 *
	 * @return end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * The method sets end
	 *
	 * @param end end
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * The method fills an array
	 */
	public void fillArray() {
		int local = getStart();
		for (int i = 0; i < array.length; i++) {
			array[i] = local;
			local++;
		}
	}

	/**
	 * The method prints to the console
	 */
	public void output() {
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

	/**
	 * The method returns RangeIterator object
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	/**
	 * The class implements Iterator
	 *
	 * @author Ihor Starodubets
	 *
	 */
	private class RangeIterator implements Iterator<Integer> {

		/**
		 * The pointer
		 */
		private int pointer;

		/**
		 * The class constructor
		 */
		RangeIterator() {
			if (reverse) {
				setPointer(array.length);
			} else {
				setPointer(-1);
			}
		}

		/**
		 * The method gets pointer
		 *
		 * @return pointer
		 */
		public int getPointer() {
			return pointer;
		}

		/**
		 * The method sets pointer
		 *
		 * @param pointer pointer
		 */
		public void setPointer(int pointer) {
			this.pointer = pointer;
		}

		/**
		 * The method returns true if the iteration has more elements
		 *
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			if (reverse) {
				if (getPointer() - 1 >= 0) {
					return true;
				}
				return false;
			} else {
				if (getPointer() + 1 < array.length) {
					return true;
				}
			}
			return false;
		}

		/**
		 * The method returns the next element in the iteration
		 *
		 * @return the next element in the iteration
		 */
		@Override
		public Integer next() {
			if (reverse) {
				setPointer(getPointer() - 1);
				return array[pointer];
			} else {
				setPointer(getPointer() + 1);
				return array[pointer];
			}
		}

		/**
		 * The method throw UnsupportedOperationException
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
