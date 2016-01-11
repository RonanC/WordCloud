package ie.gmit.sw.test.runner;

import org.junit.*;

import ie.gmit.sw.WindowSystem;

/**
 * @author Ronan Tests the TestWindowSystem class in the graphics package
 */
public class TestWindowSystem {
	WindowSystem windowSystem;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestWindowSystem class.");
	}

	@Before
	public void setUp() {
		windowSystem = null;
	}

	@Test
	public void testConstructor() {
		System.out.println("Inside testConstructor()");
		windowSystem = new WindowSystem();
	}

	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestWindowSystem class.\n");
	}
}
