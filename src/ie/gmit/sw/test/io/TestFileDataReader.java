package ie.gmit.sw.test.io;

import org.junit.*;

import ie.gmit.sw.io.FileDataReader;
import static org.junit.Assert.*;

/**
 * @author Ronan. Tests the FileDataReader class in the io package.
 */
public class TestFileDataReader {
	FileDataReader fileDataReader;
	String fileLocation;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestFileDataReader class.");
	}

	@Before
	public void setUp() {
		fileDataReader = null;
		fileLocation = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		fileLocation = "sampleData/SampleText.txt";
		fileDataReader = new FileDataReader(fileLocation);
	}

	@Test
	public void testGetDataLocation() {
		System.out.println("Inside testGetReader()");
		fileLocation = "sampleData/SampleText.txt";
		fileDataReader = new FileDataReader(fileLocation);

		assertEquals(fileLocation, fileDataReader.getDataLocation());
	}

	@Test
	public void testGetData() throws Exception {
		System.out.println("Inside testGetReader()");
		fileLocation = "sampleData/SampleText.txt";
		fileDataReader = new FileDataReader(fileLocation);

		assertNotNull(fileDataReader.getData());
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestFileDataReader class.\n");
	}
}
