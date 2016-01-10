package ie.gmit.sw.test.graphics;

import org.junit.*;
import ie.gmit.sw.graphics.WordAnalyser;
import ie.gmit.sw.graphics.WordObject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;

/** 
 * @author Ronan
 * Tests the WordAnalyser class in the graphics package
 */
public class TestWordAnalyser {
	WordAnalyser wordAnalyser;
	
	private TreeMap<String, Integer> sortedWords;
	private int maxWords;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestWordAnalyser class.");
	}

	@Before
	public void setUp() {
		wordAnalyser = null;
//		wordAnalyser = new WordAnalyser(sw, maxWords);
	}

	@Test(expected = NullPointerException.class)
	public void testConstructorNullException() {
		System.out.println("Inside testConstructorNullException()");
		wordAnalyser = new WordAnalyser(sortedWords);
	}
	
	@Test
	public void testConstructorShort() {
		System.out.println("Inside testConstructorShort()");
		sortedWords = new TreeMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			sortedWords.put("test" + i, i);
		}
		wordAnalyser = new WordAnalyser(sortedWords);
	}
	
	@Test
	public void testConstructorLong() {
		System.out.println("Inside testConstructorShort()");
		sortedWords = new TreeMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			sortedWords.put("test" + i, i);
		}
		maxWords = 50;
		wordAnalyser = new WordAnalyser(sortedWords, maxWords);
	}
	
	@Test
	public void testGetWords() {
		System.out.println("Inside testConstructorShort()");
		sortedWords = new TreeMap<String, Integer>();
		for (int i = 1; i <= 5; i++) {
			sortedWords.put("test" + i, i);
		}
		wordAnalyser = new WordAnalyser(sortedWords);
		
		ArrayList<WordObject> words = wordAnalyser.getWords();
		System.out.println(words);
		// 5 words added, first had a count of 1 so that should be removed, leaving 4 words
		assertEquals(4, words.size());
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestWordAnalyser class.\n");
	}
}
