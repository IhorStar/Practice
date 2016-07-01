package ua.nure.starodubets.Practice2;

import java.util.Iterator;

/**
 * The class class demonstrates the work of all subtasks
 *
 * @author Ihor Starodubets
 *
 */
public class Demo {

	/**
	 * The method is an entry point into the program
	 *
	 * @param args a string array
	 */
	public static void main(String[] args) {
		MyListImpl list = new MyListImpl();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		MyListImpl list2 = new MyListImpl();
		list2.add("B");
		list2.add(new Integer(2));
		list2.add(6);
		MyListImpl list3 = new MyListImpl();
		list3.add("D");
		list3.add("E");
		list3.add(new Integer(2));
		list3.add(new Integer(3));
		list3.add(9);
		System.out.println("Testing toString() method on the list object:");
		System.out.println("Expected: {[A],[B],[C],[1],[2],[3],[4],[5],[6],[7]}\nActual: " + list.toString());
		System.out.println();
		System.out.println("Testing size() method on the list object:");
		System.out.println("Expected: 10\nActual: " + list.size());
		System.out.println();
		System.out.println("Testing contains(Object o) method on the list object:");
		System.out.println("Expected: true, false\nActual: " + list.contains("C") + ", " + list.contains(10));
		System.out.println();
		System.out.println("Testing containsAll(MyList c) method on the list object:");
		System.out.println("Expected: true, false\nActual: " + list.containsAll(list2) + ", " + list.containsAll(list3));
		System.out.println();
		System.out.println("Testing toArray() method on the list object:");
		Object[] array = list.toArray();
		System.out.println("Expected: B\nActual: " + array[1]);
		System.out.println();
		System.out.println("Testing remove(Object o) method on the list object:");
		System.out.println("Expected: true, false, 9\nActual: " + list.remove("A") + ", " + list.remove("A") + ", " + list.size());
		System.out.println();
		System.out.println("Testing clear() method on the list object:");
		list.clear();
		System.out.println("Expected: 0\nActual: " + list.size());
		System.out.println();
		System.out.println("Demonstration the work of iterator with for each loop:");
		for(Object o : list2) {
			System.out.print(o + " ");
		}
		System.out.println();
		System.out.println("\nDemonstration the work of iterator with while loop:");
		Iterator<Object> iterator = list2.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println("\n");
		System.out.println("Demonstration the work of ListIterator. Testing hasNext() and next() methods:");
		System.out.print("Expected: D E 2 3 9 \nActual: ");
		ListIterator listIterator = list3.listIterator();
		while(listIterator.hasNext()) {
			System.out.print(listIterator.next() + " ");
		}
		System.out.println("\n");
		System.out.println("Demonstration the work of ListIterator. Testing hasPrevious() and previous() methods:");
		System.out.print("Expected: 9 3 2 E D \nActual: ");
		while(listIterator.hasPrevious()) {
			System.out.print(listIterator.previous() + " ");
		}
		System.out.println("\n");
		System.out.println("Demonstration the work of ListIterator. Testing remove() method:");
		System.out.print("Expected: E 2 3 9 \nActual: ");
		listIterator.next();
		listIterator.remove();
		while(listIterator.hasNext()) {
			System.out.print(listIterator.next() + " ");
		}
		System.out.println("\n");
		System.out.println("Demonstration the work of ListIterator. Testing set() method:");
		System.out.print("Expected: E 2 3 A \nActual: ");
		listIterator.previous();
		listIterator.set("A");
		for(Object o : list3) {
			System.out.print(o + " ");
		}
	}
}
