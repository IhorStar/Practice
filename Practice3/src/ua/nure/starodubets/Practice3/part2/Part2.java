package ua.nure.starodubets.Practice3.part2;

/**
 * The class finds and prints all the words maximum and minimum length
 *
 * @author Ihor Starodubets
 *
 */
public class Part2 {

	/**
	 * Input text
	 */
	private static final String TEXT = "When I was younger, so much younger than today\n"
			+ "I never needed anybody's help in any way\n"
			+ "But now these days are gone, I'm not so self-assured\n"
			+ "Now I find I've changed my mind\n"
			+ "I've opened up the doors";

	/**
	 * The method finds longest words in input array
	 *
	 * @param array input String array
	 * @return String with longest words
	 */
	public String findLongest(String[] array) {
		String longWord = array[0].trim();
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i < array.length; i++) {
			if(longWord.length() < array[i].trim().length()) {
				longWord = array[i].trim();
			}
		}
		for(int i = 0; i < array.length; i++) {
        	if(array[i].length() == longWord.length()) {
        		if(!builder.toString().contains(array[i])) {
        			builder.append(array[i] + ", ");
        		}
        	}
        }

		builder.deleteCharAt(builder.lastIndexOf(","));

		return builder.toString();
	}

	/**
	 * The method finds shortest words in input array
	 *
	 * @param array input String array
	 * @return String with shortest words
	 */
	public String findShortest(String[] array) {
        String shortWord = array[0].trim();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < array.length; i++) {
          if (shortWord.length() > array[i].trim().length()) {
        	  shortWord = array[i].trim();
          }
        }

        for(int i = 0; i < array.length; i++) {
        	if(array[i].length() == shortWord.length()) {
        		if(!builder.toString().contains(array[i])) {
        			builder.append(array[i] + ", ");
        		}
        	}
        }

        builder.deleteCharAt(builder.lastIndexOf(","));

        return builder.toString();
  }

	/**
	 * The Method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part2 part2 = new Part2();
		String[] words = TEXT.split("\\W+");
		System.out.println("Min: " + part2.findShortest(words));
		System.out.println("Max: " + part2.findLongest(words));
	}

}
