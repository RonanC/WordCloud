package ie.gmit.sw.test.graphics;

import org.junit.*;
import ie.gmit.sw.graphics.GraphicsProcessor;
import java.util.TreeMap;

/** 
 * @author Ronan
 * Tests the GraphicsProcessor class in the graphics package
 */
public class TestGraphicsProcessor {
	GraphicsProcessor graphicsProcessor;
	TreeMap<String, Integer> sortedWords;
	int maxWords = 50;
	int maxIterations = 2500000;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestGraphicsProcessor class.");
	}

	@Before
	public void setUp() {
		graphicsProcessor = new GraphicsProcessor();
	}

	/**
	 * sortedWords is null so should throw an exception
	 */
	@Test(expected = NullPointerException.class)
	public void testProcessNullException() {
		System.out.println("Inside testProcessNullException()");

		sortedWords = null;
		graphicsProcessor.process(sortedWords, maxWords, maxIterations);
	}
	
	@Test
	public void testProcess() {
		System.out.println("Inside testProcess()");
		
		sortedWords = new TreeMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			sortedWords.put("test" + i, i);
		}
		
		graphicsProcessor.process(sortedWords, maxWords, maxIterations);
	}

	@Test
	public void testDisplayGraphics() {
		System.out.println("Inside testDisplayGraphics()");
		
		sortedWords = new TreeMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			sortedWords.put("test" + i, i);
		}
		graphicsProcessor.process(sortedWords, maxWords, maxIterations);
		graphicsProcessor.displayGraphics();
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
		graphicsProcessor = null;
		sortedWords = null;
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestGraphicsProcessor class.\n");
	}
}
