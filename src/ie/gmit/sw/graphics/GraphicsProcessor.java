package ie.gmit.sw.graphics;

import java.util.TreeMap;

/**
 * Processes the other graphics classes.
 * 
 * @author Ronan
 *
 */
public class GraphicsProcessor {
	private WordAnalyser wordAnalyser;
	private TreeMap<String, Integer> sortedWords;
	private int maxWords;
	private int maxIterations;

	/**
	 * Takes in data to be processed and analyzes' the words
	 * 
	 * @param sortedWords
	 *            Sorted words
	 * @param maxWords
	 *            Maximum words to add
	 * @param maxIterations
	 *            Maximum times to check intersection per word
	 */
	public void process(TreeMap<String, Integer> sortedWords, int maxWords, int maxIterations) {
		this.sortedWords = sortedWords;
		this.maxWords = maxWords;
		this.maxIterations = maxIterations;
		analyseWords();
	}

	private void analyseWords() {
		wordAnalyser = new WordAnalyser(sortedWords, maxWords);
	}

	/**
	 * Creates a new displayGraphics object which draws the words to the screen
	 */
	public void displayGraphics() {
		new DisplayGraphics(wordAnalyser.getWords(), maxWords, maxIterations);
	}

}
