package ie.gmit.sw.io;

import java.util.TreeMap;

public interface DataProcesser {

	TreeMap<String, Integer> getSortedWords();

	/**
	 * Clears the valid words list.
	 */
	void clearValidWords();

	/**
	 * Prints out the parsed words (with the unnecessary words removed).
	 */
	void printValidWords();

	/**
	 * Prints out the valid words in sorted order.
	 */
	void printSortedWords();

	/**
	 * Reads in data from a file. Returns the result.
	 * 
	 * @param fileLocation
	 *            is the path to the local file.
	 * @throws Exception
	 *             If file is not found
	 */
	void fileReader(String fileLocation) throws Exception;

	/**
	 * Reads in data from a URL. Returns the result.
	 * 
	 * and parses it using a Third party library called JSOUP.
	 * 
	 * @param fileLocation
	 *            is the URL to be retrieved.
	 * @exception Exception
	 *                on input error.
	 * @see Exception
	 */
	void urlReader(String fileLocation) throws Exception;

	/**
	 * Removes punctuation and calls the add word method.
	 * 
	 * @param data
	 *            Data to be processed, split and punctuation removed.
	 */
	void process(String data);

	/**
	 * Adds a word to the map or else increases its count. Checks words against
	 * the stop word list.
	 * 
	 * @param word
	 *            Word to be added, checked against stop words first
	 */
	void addWord(String word);

	/**
	 * Uses the ValueComparator class to sort the Map into a Treeset.
	 */
	void sortWords();

	String getStopWordsFileName();

	void setStopWordsFileName(String stopWordsFileName);

}