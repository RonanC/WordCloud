package ie.gmit.sw.test.io;

import org.junit.*;

import ie.gmit.sw.io.DataProcessor;
import ie.gmit.sw.io.DataReader;
import ie.gmit.sw.io.DataReaderFactory;
import ie.gmit.sw.io.FileDataReader;
import ie.gmit.sw.io.UrlDataReader;

import java.io.FileReader;
import java.util.TreeMap;
import static org.junit.Assert.*;

/**
 * @author Ronan. Tests the UrlDataReader class in the io package.
 */
public class TestUrlDataReader {
	UrlDataReader urlDataReader;
	String fileLocation;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestUrlDataReader class.");
	}

	@Before
	public void setUp() {
		urlDataReader = null;
		fileLocation = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		fileLocation = "http://www.ronanconnolly.ie";
		urlDataReader = new UrlDataReader(fileLocation);
	}

	@Test
	public void testGetDataLocation() {
		System.out.println("Inside testGetReader()");
		fileLocation = "http://www.ronanconnolly.ie";
		urlDataReader = new UrlDataReader(fileLocation);
		
		assertEquals(fileLocation, urlDataReader.getDataLocation());
	}
	
	@Test
	public void testGetData() throws Exception {
		System.out.println("Inside testGetReader()");
		fileLocation = "http://www.ronanconnolly.ie";
		urlDataReader = new UrlDataReader(fileLocation);
	
		assertNotNull(urlDataReader.getData());
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestUrlDataReader class.\n");
	}
}
