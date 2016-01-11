package ie.gmit.sw.test.io;

import org.junit.*;

import ie.gmit.sw.io.DataReaderFactory;
import ie.gmit.sw.io.FileDataReader;
import ie.gmit.sw.io.UrlDataReader;

/**
 * @author Ronan. Tests the DataReaderFactory class in the io package.
 */
public class TestDataReaderFactory {
	DataReaderFactory dataReaderFactory;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestWordObject class.");
	}

	@Before
	public void setUp() {
		dataReaderFactory = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		// constructor so we use the getInstance of the singleton
		dataReaderFactory = DataReaderFactory.getInstance();
	}

	@Test
	public void testGetReader() {
		System.out.println("Inside testGetReader()");
		dataReaderFactory = DataReaderFactory.getInstance();
		String fileLocation;

		fileLocation = "sampleData/SampleText.txt";
		FileDataReader fdr = (FileDataReader) dataReaderFactory.getReader("file", fileLocation);

		fileLocation = "http://www.ronanconnolly.ie";
		UrlDataReader udr = (UrlDataReader) dataReaderFactory.getReader("url", fileLocation);
	}

	@Test(expected = ClassCastException.class)
	public void testGetReaderException() {
		System.out.println("Inside testGetReaderException()");
		dataReaderFactory = DataReaderFactory.getInstance();
		String fileLocation;

		fileLocation = "http://www.ronanconnolly.ie";
		FileDataReader fdr = (FileDataReader) dataReaderFactory.getReader("url", fileLocation);
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestWordObject class.\n");
	}
}
