package ie.gmit.sw.runner;

import java.io.*;
import java.util.*;

public class WordCloudRunner {
	
	private static HashMap<String, Integer> validWords = new HashMap<String, Integer>();
	private static TreeSet<String> stopWords = new TreeSet<String>();
	
	private static int stopWordsFound;
	
	public static void main(String[] args) {
		stopWordsFound = 0;
		
		System.out.println("Reading in stopwords file.");
		stopwordsReader();
		
		System.out.println("Reading in sample text file.");
		fileReader();

		System.out.println("printing valid words");
		printValidWords();
		
		System.out.println("fin.");
		
		// paint
		
		// read data from url
		
		// implement design patterns
		
	}
	
	private static void printValidWords() {
		String words = validWords.toString();
		
		System.out.println("words: " + words);
		
	}

	private static void stopwordsReader() {
		// read stopwords in from file
		String fileName = "stopwords.txt";
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String word;
			// one word per line
			while((word = in.readLine()) != null){
				//System.out.println(word);
				stopWords.add(word);
			}
			in.close();
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void fileReader() {
		// read sample text in from file
		String fileName = "SampleText.txt";
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String sentence;
			while((sentence = in.readLine()) != null){
				process(sentence);
			}
			in.close();
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static void process(String sentence) {
		// removes punctuation + to_lowercase
		String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		
		for (String word : words) {
			//System.out.println(word);
			if(word.length() > 1){
				addWord(word);
			}
			
		}
	}

	private static void addWord(String word) {
		if(!stopWords.contains(word)){
			
			if(validWords.containsKey(word)){
				int count = validWords.get(word);
				count++;
				validWords.replace(word, count);
			}
			else{
				validWords.put(word, 1);
			}
		}
		else{
			stopWordsFound++;
		}
	}

}
