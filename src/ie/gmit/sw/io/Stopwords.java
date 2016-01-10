package ie.gmit.sw.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Stopwords {
	// stop words
	private int stopWordsFound;
	private String stopWordsFileName;
	private TreeSet<String> stopWords = new TreeSet<String>();
	
	public Stopwords(String dataLocation) {
		this.stopWordsFileName = dataLocation;
	}
	
	/**
	 * Reads in the stop words list (words to be blocked while parsing in data).
	 * you can call this method with different file names
	 */
	public void addWords() {
		// read stopwords in from file
		try {
			BufferedReader in = new BufferedReader(new FileReader(stopWordsFileName));
			String word;
			// one word per line
			while ((word = in.readLine()) != null) {
				// System.out.println(word);
				stopWords.add(word);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Updates file location then calls addwords method
	 * @param stopWordsFileName
	 */
	public void addWords(String stopWordsFileName){
		setStopWordsFileName(stopWordsFileName);
		addWords();
	}

	/**
	 * Returns the stopWordsFound count.
	 * 
	 * @return int This returns the number of stop words that were found during
	 *         parsing.
	 */
	public int getStopWordsFound() {
		return stopWordsFound;
	}

	public void setStopWordsFound(int stopWordsFound) {
		this.stopWordsFound = stopWordsFound;
	}

	public String getStopWordsFileName() {
		return stopWordsFileName;
	}

	public void setStopWordsFileName(String stopWordsFileName) {
		this.stopWordsFileName = stopWordsFileName;
	}

	public TreeSet<String> getStopWords() {
		return stopWords;
	}
}
