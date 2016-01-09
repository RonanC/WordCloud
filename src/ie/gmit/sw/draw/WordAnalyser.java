package ie.gmit.sw.draw;

import java.awt.*;
import java.util.*;

public class WordAnalyser {
	// data
	private TreeMap<String, Integer> sortedWords;
	private ArrayList<WordObject> words = new ArrayList<WordObject>();

	public WordAnalyser(TreeMap<String, Integer> sw) {
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

		// int wordCount = words.size();

		// font sizer
		// WordObject firstWord = words.get(0);
		// int highestCount = firstWord.getCount();

		double fontMultiplier = .07;

		// either different font or color
		Random random = new Random();
		int max = 2;
		int min = 0;
		int choice = random.nextInt(max - min + 1) + min;

		System.out.println("choice: " + choice);

		// get default data
		String fontName = getFontName();
		Color color = getColor();

		for (WordObject wordObject : words) {
			int newFontSize = (int) Math.round(wordObject.getCount() / fontMultiplier); // 15
			wordObject.setFontSize(newFontSize);

			if (choice == 0) {
				fontName = getFontName();
			} else if (choice == 1) {
				color = getColor();
			} else {
				fontName = getFontName();
				color = getColor();
			}

			Font font = new Font(fontName, Font.PLAIN, wordObject.getFontSize());
			wordObject.setFont(font);

			wordObject.setColor(color);

			// System.out.println(wordObject.toString());
		}
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
