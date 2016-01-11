package ie.gmit.sw.io;

import java.util.TreeSet;

public interface Stopworder {

	/**
	 * Reads in the stop words list (words to be blocked while parsing in data).
	 * you can call this method with different file names
	 */
	void addWords();

	/**
	 * Updates file location then calls addwords method
	 * 
	 * @param stopWordsFileName Location of stopwords file
	 */
	void addWords(String stopWordsFileName);

	/**
	 * Returns the stopWordsFound count.
	 * 
	 * @return int This returns the number of stop words that were found during
	 *         parsing.
	 */
	int getStopWordsFound();

	void setStopWordsFound(int stopWordsFound);

	String getStopWordsFileName();

	void setStopWordsFileName(String stopWordsFileName);

	TreeSet<String> getStopWords();

}