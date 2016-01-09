package ie.gmit.sw.draw;

import java.awt.*;
import java.util.*;

public class WordAnalyser {
	// data
	private TreeMap<String, Integer> sortedWords;
	private ArrayList<WordObject> words = new ArrayList<WordObject>();
	private int maxWords;
	
	// globals
	// font sizer
	double diff, percentDiff;

	public WordAnalyser(TreeMap<String, Integer> sw) {
		this.maxWords = 50;
		sortedWords = sw;
		analyse();
	}

	public WordAnalyser(TreeMap<String, Integer> sw, int maxWords) {
		this.maxWords = maxWords;
		sortedWords = sw;
		analyse();
	}

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

//		System.out.println("choice: " + choice);

		fontSizer();

		// get default data
		String fontName = getFontName();
		Color color = getColor();

		int count = 0;
		for (WordObject wordObject : words) {
			if (count < maxWords) {
				count++;

				double newFontSize = wordObject.getCount() / percentDiff; // 15
//				System.out.println("diff: " + diff + "\t\tnewFontSize: " + newFontSize);

				if(newFontSize < 10)
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

	private void fontSizer() {
		// font sizer
		// get init data
		WordObject firstWord = words.get(0);
		double highestCount = firstWord.getCount();
		double maxFontSize = 150;
		
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
		System.out.printf("Font Scaling:\t\thighestCount: %2.3f\t\tdiff: %2.3f\t\tpercentDiff: %2.3f\t\tnewSize: %2.3f\n", highestCount, diff, percentDiff, newSize);
	}

	private String getFontName() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int index = (int) (Math.random() * (fontNames.length - 1));
		String fontName = fontNames[index];

		return fontName;
	}

	private Color getColor() {
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color color = new Color(red, green, blue);

		return color;
	}

	public ArrayList<WordObject> getWords() {
		return words;
	}

}
