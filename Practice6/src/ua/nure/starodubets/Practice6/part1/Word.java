package ua.nure.starodubets.Practice6.part1;

import java.util.Comparator;

/**
 *
 * @author Ihor Starodubets
 *
 */
public class Word {

	/**
	 * A word
	 */
	private String word;

	/**
	 * A frequency
	 */
	private int frequency;

	/**
	 * A class constructor
	 *
	 * @param word a word
	 */
	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	/**
	 * The method gets a word
	 *
	 * @return a word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * The method sets a word
	 *
	 * @param word a word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * The method gets a frequency
	 *
	 * @return a frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * The method sets a frequency
	 *
	 * @param frequency a frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}

/**
 * The class implements Comparator
 *
 * @author Ihor Starodubets
 *
 */
class CompareByFrequency implements Comparator<Word> {

	/**
	 * Compares its two arguments for order.
	 */
	@Override
	public int compare(Word word1, Word word2) {
		return -(word1.getFrequency() - word2.getFrequency());

	}

}

/**
 * The class implements Comparator
 *
 * @author Ihor Starodubets
 *
 */
class CompareByWord implements Comparator<Word> {

	/**
	 * Compares its two arguments for order.
	 */
	@Override
	public int compare(Word word1, Word word2) {
		return word1.getWord().compareTo(word2.getWord());
	}

}
