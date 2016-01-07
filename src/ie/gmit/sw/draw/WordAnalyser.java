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
		for(Map.Entry<String, Integer> entry : sortedWords.entrySet()){
			String key = entry.getKey();
			Integer value = entry.getValue();
			
			// only add words that have a count higher then 1
			if(value > 1){
				words.add(new WordObject(key, (int)Math.round(value * 0.8)));
			}
			
			
			
			System.out.println(key + " => " + value);
		}
		
		int wordCount = words.size();
		
		// font sizer
		WordObject firstWord = words.get(0);
		int highestCount = firstWord.getCount();
		
		double fontMultiplier = .07;
		
//		if(highestCount > 6){
//			fontMultiplier = 10;
//		}
		
		for (WordObject wordObject : words) {
//			System.out.println((wordObject.getCount() * 10) + "\t\t" + (wordCount / 10) + "\t\t" + (wordObject.getCount() * 10) / (wordCount / 100));
//			int newFontSize = (wordObject.getCount() * 30) / (wordCount / 75);
			
			
			
			
			int newFontSize = (int) Math.round(wordObject.getCount() / fontMultiplier); //15
//			System.out.println("wordObject.getCount(): " + wordObject.getCount() + "\t\t" + "fontMultiplier: " + fontMultiplier);
			wordObject.setFontSize(newFontSize);
			
			String fontName = getFontName();
			Font font = new Font(fontName, Font.PLAIN, wordObject.getFontSize());
			wordObject.setFont(font);
					
			Color color = getColor();
			wordObject.setColor(color);
					
			System.out.println(wordObject.toString());
		}
		
//		for (int i = 0; i < sortedWords.size(); i++) {
//			sortedWords.forEach(
//
//
//					g.drawString("Hello", midX, midY);
//					);
//		}
		
		
	}

	private String getFontName() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int index = (int)(Math.random() * (fontNames.length - 1));
		String fontName = fontNames[index];
		
		return fontName;
	}

	private Color getColor() {
		int red = (int)(Math.random() * 255);
		int green = (int)(Math.random() * 255);
		int blue = (int)(Math.random() * 255);
		Color color = new Color(red, green, blue);
		
		return color;
	}

	public ArrayList<WordObject> getWords() {
		return words;
	}
	
	
	
}
