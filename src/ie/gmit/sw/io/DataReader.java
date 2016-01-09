package ie.gmit.sw.io;

import java.io.*;
import java.net.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;

/**
 * 
 * Used for reading data from URLs, or Files locally. Sorts and parses the data
 * into lists and blocks unnecessary words. Sorts the data using the
 * ValueComparator class.
 * 
 */
public class DataReader {
	private Map<String, Integer> validWords = new HashMap<String, Integer>();
	private TreeSet<String> stopWords = new TreeSet<String>();

	private int stopWordsFound;

	private String stopWordsFileName = "stopwords.txt";

	// sorting
	private ValueComparator vc = new ValueComparator(validWords);
	private TreeMap<String, Integer> sortedWords = new TreeMap<String, Integer>(vc);

	public TreeMap<String, Integer> getSortedWords() {
		return sortedWords;
	}

	public void clearValidWords() {
		validWords.clear();
	}

	public DataReader() {
		// inputDataFileName = "SampleText.txt";
		stopwordsReader();
	}

	/**
	 * Prints out the parsed words (with the unnecessary words removed).
	 */
	public void printValidWords() {
		System.out.println("words: " + validWords);
		System.out.println(validWords.size() + " unique words");
	}

	/**
	 * Prints out the valid words in sorted order.
	 */
	public void printSortedWords() {
		System.out.println("sorted words: " + sortedWords);
		System.out.println(sortedWords.size() + " unique words");
	}

	/**
	 * Reads in the stop words list (words to be blocked while parsing in data).
	 */
	private void stopwordsReader() {
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
	 * Read in data from a file.
	 * 
	 * @param inputDataFileName
	 *            is the path to the local file.
	 */
	public void fileReader(String inputDataFileName) {
		// read sample text in from file
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputDataFileName));
			wordLooper(in);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	/**
	 * Loops through sentences, once finished starts the sorting.
	 */
	private void wordLooper(BufferedReader in) throws IOException {
		String sentence;
		while ((sentence = in.readLine()) != null) {
			process(sentence);
		}
		in.close();

		sortWords();
	}

	/**
	 * Reads in data from a URL and parses it using a Third party library called
	 * JSOUP.
	 * 
	 * @param inputDataUrlname
	 *            is the URL to be retrieved.
	 * @exception Exception
	 *                on input error.
	 * @see Exception
	 */
	public void urlReader(String inputDataUrlname) throws Exception {
		URL oracle;
		String html = "";
		oracle = new URL(inputDataUrlname);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String sentence;
		while ((sentence = in.readLine()) != null) {
			html += sentence;
		}
		in.close();

		// Jsoup
		Document doc = Jsoup.parse(html);
		// Element link = doc.select("a").first();

		String text = doc.body().text(); // "An example link"
		System.out.println("\nJsoup text: " + text);

		// OTHER EXAMPLE USE OF JSOUP
		// String linkHref = link.attr("href"); // "http://example.com/"
		// String linkText = link.text(); // "example""

		// String linkOuterH = link.outerHtml(); // "<a
		// href="http://example.com"><b>example</b></a>"
		// String linkInnerH = link.html(); // "<b>example</b>"

		// System.out.println("link: " + link);
		//
		// System.out.println("linkHref: " + linkHref);
		// System.out.println("linkText: " + linkText);
		//
		// System.out.println("linkOuterH: " + linkOuterH);
		// System.out.println("linkInnerH: " + linkInnerH);

		process(text);
		sortWords();
	}

	/**
	 * Removes punctuation and calls the add word method.
	 */
	private void process(String sentence) {
		// removes punctuation + to_lowercase
		String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

		for (String word : words) {
			// System.out.println(word);
			if (word.length() > 1) {
				addWord(word);
			}
		}
	}

	/**
	 * Adds a word to the map or else increases its count. Checks words against
	 * the stop word list.
	 */
	private void addWord(String word) {
		if (!stopWords.contains(word)) {

			if (validWords.containsKey(word)) {
				int count = validWords.get(word);
				count++;
				validWords.replace(word, count);
			} else {
				validWords.put(word, 1);
			}
		} else {
			stopWordsFound++;
		}
	}

	/**
	 * Uses the ValueComparator class to sort the Map into a Treeset.
	 */
	private void sortWords() {
		sortedWords.clear();
		sortedWords.putAll(validWords);
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
}
