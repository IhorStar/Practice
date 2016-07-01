package ua.nure.starodubets.Practice4.part4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class implements Iterable interface, parses a text file and returns sentences from a file
 *
 * @author Ihor Starodubets
 *
 */
class Parser implements Iterable<String> {

	/**
	 * A regular expression
	 */
	private static final String REGEXP = "\\p{javaUpperCase}.*?\\.";

	/**
	 * An encoding
	 */
	private String encoding = "Cp1251";

	/**
	 * A matcher
	 */
	private Matcher matcher;

	/**
	 * A class constructor
	 *
	 * @param file an input file
	 */
	public Parser(File file) {
		StringBuilder builder = new StringBuilder();
		Scanner scanner;
		try {
			scanner = new Scanner(file, getEncoding());
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine()).append(" ");
				builder.delete(builder.length() - 1, builder.length());
				Pattern pattern = Pattern.compile(REGEXP);
				setMatcher(pattern.matcher(builder));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method gets an encoding
	 *
	 * @return an encoding
	 */
	public final String getEncoding() {
		return encoding;
	}

	/**
	 * The method gets a matcher
	 *
	 * @return matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * The method sets a Matcher
	 *
	 * @param matcher an input matcher
	 */
	public final void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	/**
	 * The method returns an Iterator
	 */
	@Override
	public Iterator<String> iterator() {
		return new ParserIterator(getMatcher());
	}

	/**
	 * The class implements Iterator interface
	 *
	 * @author Ihor Starodubets
	 *
	 */
	private static class ParserIterator implements Iterator<String> {

		/**
		 * A matcher
		 */
		private Matcher matcher;

		/**
		 * A class constructor
		 *
		 * @param matcher a matcher
		 */
		public ParserIterator(Matcher matcher) {
			setMatcher(matcher);
		}

		/**
		 * The method gets a matcher
		 *
		 * @return matcher
		 */
		public Matcher getMatcher() {
			return this.matcher;
		}

		/**
		 * The method sets a Matcher
		 *
		 * @param matcher an input matcher
		 */
		public void setMatcher(Matcher matcher) {
			this.matcher = matcher;
		}

		/**
		 * The method returns true if the iteration has more elements
		 *
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return getMatcher().find();
		}

		/**
		 * The method returns the next element in the iteration
		 *
		 * @return the next element in the iteration
		 */
		@Override
		public String next() {
			return getMatcher().group();
		}

		/**
		 * The method throws UnsupportedOperationException
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
