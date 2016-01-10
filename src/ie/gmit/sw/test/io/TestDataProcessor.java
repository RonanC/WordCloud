package ie.gmit.sw.test.io;

import org.junit.*;

import ie.gmit.sw.io.DataProcessor;
import java.util.TreeMap;
import static org.junit.Assert.*;

/**
 * @author Ronan. Tests the DataProcessor class in the io package.
 */
public class TestDataProcessor {
	DataProcessor dataProcessor;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestDataProcessor class.");
	}

	@Before
	public void setUp() {
		dataProcessor = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		dataProcessor = new DataProcessor();
	}

	@Test
	public void testAddSort() {
		System.out.println("Inside testAddSort()");
		dataProcessor = new DataProcessor();

		int wordNum = 10;

		for (int i = 0; i < wordNum; i++) {
			dataProcessor.addWord("test" + i);
		}

		dataProcessor.sortWords();
		TreeMap<String, Integer> words = dataProcessor.getSortedWords();
		assertEquals(wordNum, words.size());

		// log
		dataProcessor.printSortedWords();
		dataProcessor.printValidWords();
	}

	@Test
	public void testClear() {
		System.out.println("Inside testClear()");
		dataProcessor = new DataProcessor();

		int wordNum = 10;

		for (int i = 0; i < wordNum; i++) {
			dataProcessor.addWord("test" + i);
		}

		dataProcessor.sortWords();
		TreeMap<String, Integer> words = dataProcessor.getSortedWords();
		assertEquals(wordNum, words.size());

		dataProcessor.clearValidWords();
		dataProcessor.sortWords();
		assertEquals(0, words.size());
	}

	@Test
	public void testStopwordsFileName() {
		System.out.println("Inside testStopwordsFileName()");
		dataProcessor = new DataProcessor();

		String stopWordsFileName = "stopwords2.txt";
		dataProcessor.setStopWordsFileName(stopWordsFileName);
		assertEquals(stopWordsFileName, dataProcessor.getStopWordsFileName());
	}

	@Test
	public void testProcess() {
		System.out.println("Inside testProcess()");
		dataProcessor = new DataProcessor();

		String stopWordsFileName = "stopwords2.txt";
		dataProcessor.setStopWordsFileName(stopWordsFileName);
		assertEquals(stopWordsFileName, dataProcessor.getStopWordsFileName());

		// sir is the only word not in stopwords, '?' should be removed.
		String words = "how are you sir sir?";
		String word = "sir";
		dataProcessor.process(words);
		dataProcessor.sortWords();
		TreeMap<String, Integer> validWords = dataProcessor.getSortedWords();
		dataProcessor.printSortedWords();
		assertEquals(1, validWords.size());

		// first and only key should be "sir"
		assertEquals(word, validWords.firstKey());
	}

	@Test(expected = Exception.class)
	public void testFileReaderException() throws Exception {
		System.out.println("Inside testFileReaderException()");
		dataProcessor = new DataProcessor();

		String fileLocation = "incorrectFileName";
		dataProcessor.fileReader(fileLocation);
	}

	@Test
	public void testFileReader() throws Exception {
		System.out.println("Inside testFileReader()");
		dataProcessor = new DataProcessor();

		String fileLocation = "sampleData/SampleText.txt";
		dataProcessor.fileReader(fileLocation);
	}

	@Test(expected = Exception.class)
	public void testUrlReaderException() throws Exception {
		System.out.println("Inside testUrlReaderException()");
		dataProcessor = new DataProcessor();

		String fileLocation = "incorrectUrl";
		dataProcessor.urlReader(fileLocation);
	}

	@Test
	public void testUrlReader() throws Exception {
		System.out.println("Inside testUrlReader()");
		dataProcessor = new DataProcessor();

		String fileLocation = "http://www.ronanconnolly.ie";
		dataProcessor.urlReader(fileLocation);
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestDataProcessor class.\n");
	}
}
