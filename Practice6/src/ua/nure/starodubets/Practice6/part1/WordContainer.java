package ua.nure.starodubets.Practice6.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * The class aggregates Word objects
 *
 * @author Ihor Starodubets
 *
 */
public class WordContainer {

	/**
	 * A list
	 */
	private List<Word> list = new ArrayList<Word>();

	/**
	 * The method gest a list
	 *
	 * @return a list
	 */
	public List<Word> getList() {
		return list;
	}

	/**
	 * The method adds Word objects to the list
	 *
	 * @param word a Word object
	 */
	public void add(Word word) {
		for (Word w : list) {
			if (w.getWord().equals(word.getWord())) {
				w.setFrequency(w.getFrequency() + 1);
				return;
			}
		}
		list.add(word);
	}
}
