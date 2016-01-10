package ie.gmit.sw.draw;

import java.util.TreeMap;

public class GraphicsProcessor {
	private WordAnalyser wordAnalyser;
	private TreeMap<String, Integer> sortedWords ;
	private int maxWords;
	private int maxIterations;
	
	public void process(TreeMap<String, Integer> sortedWords, int maxWords, int maxIterations){
		this.sortedWords = sortedWords;
		this.maxWords = maxWords;
		this.maxIterations = maxIterations;
		analyseWords();
	}
	
	public void analyseWords(){
		wordAnalyser = new WordAnalyser(sortedWords, maxWords);
	}
	
	public void displayGraphics(){
		new DisplayGraphics(wordAnalyser.getWords(), maxWords, maxIterations);
	}
	
}
