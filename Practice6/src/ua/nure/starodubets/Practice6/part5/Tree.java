package ua.nure.starodubets.Practice6.part5;

/**
 * The class implements the data structure of a binary search tree
 *
 * @author Ihor Starodubets
 *
 * @param <E> element type
 */
public class Tree <E extends Comparable<E>> {

	/**
	 * An indentation
	 */
	private static final String INDENT = " ";

	/**
	 * A StringBuilder object
	 */
	private StringBuilder builder = new StringBuilder(INDENT);

	/**
	 * A root Node
	 */
	private Node<E> root;

	/**
	 * A parent Node
	 */
	private Node<E> parent;

	/**
	 * A current Node
	 */
	private Node<E> current;

	/**
	 * The method gets an element
	 *
	 * @return an element
	 */
	public E get() {
		return root.element;
	}

	/**
	 * The method gets a parent Node
	 *
	 * @return a parent Node
	 */
	public Node<E> getParent() {
		return parent;
	}

	/**
	 * The method sets a parent Node
	 *
	 * @param parent a parent Node
	 */
	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	/**
	 * The method gets a current Node
	 *
	 * @return a current Node
	 */
	public Node<E> getCurrent() {
		return current;
	}

	/**
	 * The method sets a current Node
	 *
	 * @param current a current Node
	 */
	public void setCurrent(Node<E> current) {
		this.current = current;
	}

	/**
	 * The method adds an element to the container
	 *
	 * @param element an element
	 * @return true if an element was added
	 */
	public boolean add(E element) {
		if (root == null) {
			root = new Node<E>(element, null, null);
			return true;
		}
		return add(root, element);
	}

	/**
	 * The method adds all of the elements of the array in to a container
	 *
	 * @param elements an element
	 */
	public void add(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);
		}
	}

	/**
	 * The method adds an element to the container
	 *
	 * @param node an container
	 * @param element an element
	 * @return true if a non repeating element was added
	 */
	public boolean add(Node<E> node, E element) {
		if (element.compareTo(node.element) < 0) {
			if (node.left == null) {
				node.left = new Node<E>(element, null, null);
				return true;
			}
			return add(node.left, element);
		}
		if (element.compareTo(node.element) > 0) {
			if (node.right == null) {
				node.right = new Node<E>(element, null, null);
				return true;
			}
			return add(node.right, element);
		}
		return false;
	}

	/**
	 * The method removes an element from the container
	 *
	 * @param element an element
	 * @return true if an element was removed
	 */
	public boolean remove(E element) {
		final int left = -1;
		final int right = 1;
		final int noStep = 0;

		setParent(null);
		setCurrent(root);
		int comp = 0;
		int lastStep = noStep;

		while (current != null
				&& (comp = current.element.compareTo(element)) != 0) {
			parent = current;
			if (comp < 0) {
				lastStep = right;
				current = current.right;
			} else {
				lastStep = left;
				current = current.left;
			}
		}
		if (current == null) {
			return false;
		}
		if (current.left == null) {
			if (lastStep == right) {
				parent.right = current.right;
			} else if (lastStep == left) {
				parent.left = current.right;
			} else {
				root = current.right;
			}
		} else if (current.right == null) {
			if (lastStep == right) {
				parent.right = current.left;
			} else if (lastStep == left) {
				parent.left = current.left;
			} else {
				root = current.left;
			}
		} else {
			Node<E> nodeToReplace = current.right;
			parent = current;
			while (nodeToReplace.left != null) {
				parent = nodeToReplace;
				nodeToReplace = nodeToReplace.left;
			}
			current.element = nodeToReplace.element;
			if (parent.equals(current)) {
				parent.right = nodeToReplace.right;
			} else {
				parent.left = nodeToReplace.right;
			}
		}
		return true;
	}

	/**
	 * The method prints tree
	 */
	public void print() {
		print(root);
	}

	/**
	 * The method prints tree
	 *
	 * @param node a node
	 */
	public void print(Node<E> node) {
		if (node != null) {
			builder.append(INDENT);
			print(node.left);
			builder.append(INDENT);
			if (node.equals(root)) {
				System.out.println(node.element);
			} else {
				System.out.println(builder.toString() + node.element);
			}
			builder.append(INDENT);
			print(node.right);
			builder.delete(0, builder.length());
			builder.append(INDENT);
		}

	}

	/**
	 * The nested class. Objects of this class represent a tree
	 *
	 * @author Ihor Stardubets
	 *
	 * @param <E> element type
	 */
	private static class Node<E> {

		/**
		 * An element
		 */
		private E element;

		/**
		 * A left Node
		 */
		private Node<E> left;

		/**
		 * A right Node
		 */
		private Node<E> right;

		/**
		 * The class constructor
		 *
		 * @param element an element
		 * @param left left Node
		 * @param right right Node
		 */
		Node(E element, Node<E> left, Node<E> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}
}
