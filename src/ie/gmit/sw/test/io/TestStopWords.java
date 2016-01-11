package ie.gmit.sw.test.io;

import org.junit.*;

import ie.gmit.sw.io.Stopwords;
import static org.junit.Assert.*;

/**
 * @author Ronan. Tests the StopWords class in the io package.
 */
public class TestStopWords {
	Stopwords stopwords;
	String fileLocation;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestStopWords class.");
	}

	@Before
	public void setUp() {
		stopwords = null;
		fileLocation = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		fileLocation = "stopwords.txt";
		stopwords = new Stopwords(fileLocation);
	}

	@Test
	public void testAddWords() {
		System.out.println("Inside testGetReader()");
		fileLocation = "stopwords.txt";
		stopwords = new Stopwords(fileLocation);

		assertEquals(0, stopwords.getStopWords().size());
		stopwords.addWords();
		int size = stopwords.getStopWords().size();
		assertTrue(size > 0);
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestStopWords class.\n");
	}
}
