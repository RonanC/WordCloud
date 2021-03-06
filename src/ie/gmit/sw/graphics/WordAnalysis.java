package ie.gmit.sw.graphics;

import java.awt.*;
import java.util.*;

/**
 * Analyzes the words to predict what font details they should have.
 * @author Ronan
 */
public class WordAnalysis implements WordAnalyzer {
	// data
	private TreeMap<String, Integer> sortedWords;
	private ArrayList<WordObject> words = new ArrayList<WordObject>();
	private int maxWords;
	private int maxFontSize = 120;

	// globals
	// font sizer
	double diff, percentDiff;

	
	/**
	 * Sets max words to default of 50
	 * Starts the word analysis
	 * @param sw Sets Sorted Words
	 */
	public WordAnalysis(TreeMap<String, Integer> sw) {
		this.maxFontSize = 150;
		this.maxWords = 50;
		sortedWords = sw;
		analyse();
	}

	/**
	 * @param sw Sets Sorted Words
	 * @param maxWords Starts the word analyzes
	 * @param maxFont max font
	 */
	public WordAnalysis(TreeMap<String, Integer> sw, int maxWords, int maxFont) {
		this.maxFontSize = maxFont;
		this.maxWords = maxWords;
		sortedWords = sw;
		analyse();
	}

	/**
	 * Analyzes each word and chooses its font details. This only happens once.
	 */
	private void analyse() {
		for (Map.Entry<String, Integer> entry : sortedWords.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			// only add words that have a count higher then 1
			if (value > 1) {
				words.add(new WordObject(key, (int) Math.round(value * 0.8)));
			}

			// System.out.println(key + " => " + value);
		}

		// double fontMultiplier = .07;

		// either different font or color
		Random random = new Random();
		int max = 1;
		int min = 0;
		int choice = random.nextInt(max - min + 1) + min;

		// System.out.println("choice: " + choice);

		fontSizer();

		// get default data
		String fontName = getFontName();
		Color color = getColor();

		int count = 0;
		for (WordObject wordObject : words) {
			if (count < maxWords) {
				count++;

				double newFontSize = wordObject.getCount() / percentDiff; // 15
				// System.out.println("diff: " + diff + "\t\tnewFontSize: " +
				// newFontSize);

				if (newFontSize < 10)
					newFontSize = 10;

				wordObject.setFontSize((int) Math.round(newFontSize));

				if (choice == 0) {
					color = getColor();
					fontName = getFontName();
				} else {
					color = getColor();
				}

				Font font = new Font(fontName, Font.PLAIN, wordObject.getFontSize());
				wordObject.setFont(font);

				wordObject.setColor(color);

				// System.out.println(wordObject.toString());
			} else {
				break;
			}
		}
	}

	/**
	 * Calculates the correct ratio and percentage for the font scaling.
	 */
	private void fontSizer() {
		// font sizer
		// get init data
		WordObject firstWord = words.get(0);
		double highestCount = firstWord.getCount();

		// have diff of first word

		// find out weather a number is higher or lower
		diff = maxFontSize - highestCount;
		double newSize = highestCount + diff;

		// now we need to get the percentage diff between the num and max num
		// we then use that percentage to scale all other
		// if word one is scaled by 50%, they all should
		percentDiff = highestCount / maxFontSize;

		// we divide all numbers by the percetDiff

		// log
		System.out.printf(
				"Font Scaling:\t\thighestCount: %2.3f\t\tdiff: %2.3f\t\tpercentDiff: %2.3f\t\tnewSize: %2.3f\n",
				highestCount, diff, percentDiff, newSize);
	}

	/**
	 * Returns a random font name based on the available fonts on the system.
	 */
	private String getFontName() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int index = (int) (Math.random() * (fontNames.length - 1));
		String fontName = fontNames[index];

		return fontName;
	}

	/**
	 * Returns a random color.
	 */
	private Color getColor() {
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color color = new Color(red, green, blue);

		return color;
	}

	
	/**
	 * @return A list of WordObjects
	 */
	public ArrayList<WordObject> getWords() {
		return words;
	}
	
	public int getMaxFontSize() {
		return maxFontSize;
	}

	public void setMaxFontSize(int maxFontSize) {
		this.maxFontSize = maxFontSize;
	}
	
	

}
