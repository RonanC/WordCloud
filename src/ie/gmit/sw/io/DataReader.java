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

	public void printValidWords() {
		/**
		 * Prints out the parsed words (with the unnecessary words removed).
		 */
		System.out.println("words: " + validWords);
		System.out.println(validWords.size() + " unique words");
	}

	public void printSortedWords() {
		/**
		 * Prints out the valid words in sorted order.
		 */
		System.out.println("sorted words: " + sortedWords);
		System.out.println(sortedWords.size() + " unique words");
	}

	private void stopwordsReader() {
		/**
		 * Reads in the stop words list (words to be blocked while parsing in
		 * data).
		 */
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

	public void fileReader(String inputDataFileName) {
		/**
		 * Read in data from a file.
		 */
		// read sample text in from file
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputDataFileName));
			wordLooper(in);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	private void wordLooper(BufferedReader in) throws IOException {
		/**
		 * Loops through sentences, once finished starts the sorting.
		 */
		String sentence;
		while ((sentence = in.readLine()) != null) {
			process(sentence);
		}
		in.close();

		sortWords();
	}

	public void urlReader(String inputDataUrlname) throws Exception {
		/**
		 * Reads in data from a URL and parses it using a Third party library
		 * called JSOUP.
		 */
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

	private void process(String sentence) {
		/**
		 * Removes punctuation and calls the add word method.
		 */
		// removes punctuation + to_lowercase
		String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

		for (String word : words) {
			// System.out.println(word);
			if (word.length() > 1) {
				addWord(word);
			}
		}
	}

	private void addWord(String word) {
		/**
		 * Adds a word to the map or else increases its count. Checks words
		 * against the stop word list.
		 */
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

	private void sortWords() {
		/**
		 * Uses the ValueComparator class to sort the Map into a Treeset.
		 */
		sortedWords.clear();
		sortedWords.putAll(validWords);
	}

	public int getStopWordsFound() {
		/**
		 * Returns the stopWordsFound count.
		 */
		return stopWordsFound;
	}
}
