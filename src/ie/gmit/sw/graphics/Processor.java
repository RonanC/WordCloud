package ie.gmit.sw.graphics;

import java.util.TreeMap;

public abstract class Processor {

	protected WordAnalyzer wordAnalysis;
	private TreeMap<String, Integer> sortedWords;
	protected int maxWords;
	protected int maxFontSize;

	public Processor() {
		super();
	}

	/**
	 * Takes in data to be processed and analyzes' the words
	 * 
	 * @param sortedWords
	 *            Sorted words
	 * @param maxWords
	 *            Maximum words to add
	 * @param maxFont
	 *            Maximum times to check intersection per word
	 */
	public void process(TreeMap<String, Integer> sortedWords, int maxWords, int maxFont) {
		this.sortedWords = sortedWords;
		this.maxWords = maxWords;
		this.maxFontSize = maxFont;
		analyseWords();
	}

	private void analyseWords() {
		wordAnalysis = new WordAnalysis(sortedWords, maxWords, maxFontSize);
	}

}