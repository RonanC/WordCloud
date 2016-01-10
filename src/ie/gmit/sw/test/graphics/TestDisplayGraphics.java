package ie.gmit.sw.test.graphics;

import org.junit.*;

import ie.gmit.sw.graphics.DisplayGraphics;
import ie.gmit.sw.graphics.WordObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/** 
 * @author Ronan
 * Tests the DisplayGraphics class in the graphics package
 */
public class TestDisplayGraphics {
	DisplayGraphics displayGraphics;
	ArrayList<WordObject> words; 
	int maxWords;
	int maxIntersectTries;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestDisplayGraphics class.");
	}

	@Before
	public void setUp() {
		displayGraphics = null;
		words = null;
		maxWords = 50;
		maxIntersectTries = 2500000;
	}
	
//	@Test(expected = NullPointerException.class)
//	public void testConstructorNullException() {
//		System.out.println("Inside testConstructorNullException()");
//		words = null;
//		displayGraphics = new DisplayGraphics(words);
//	}
	
	@Test
	public void testContructorShort() {
		System.out.println("Inside testContructorShort()");
		words = new ArrayList<WordObject>();
		
		// setup word object
		String word = "test";
		int fontSize = 24;
		Color color = new Color(255, 255, 255);
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		
		for (int i = 0; i < 5; i++) {
			WordObject wo = new WordObject(word + i, i, fontSize, color, font);
			words.add(wo);
		}
		
		displayGraphics = new DisplayGraphics(words);
	}

	@Test
	public void testContructorLong() {
		System.out.println("Inside testContructorLong()");
		words = new ArrayList<WordObject>();
		// setup word object
		String word = "test";
		int fontSize = 24;
		Color color = new Color(255, 255, 255);
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		
		for (int i = 0; i < 5; i++) {
			WordObject wo = new WordObject(word + i, i, fontSize, color, font);
			words.add(wo);
		}
		
		displayGraphics = new DisplayGraphics(words, maxWords, maxIntersectTries);
	}
	
	@Test
	public void testPaintComponents() {
		System.out.println("Inside testPaintComponents()");
		//image 
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = image.createGraphics();

		// setup word object
		String word = "test";
		int fontSize = 24;
		Color color = new Color(255, 255, 255);
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		
		words = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			WordObject wo = new WordObject(word + i, i, fontSize, color, font);
			words.add(wo);
		}
		
		displayGraphics = new DisplayGraphics(words);
		displayGraphics.paintComponents(graphics2D);
	}
	
	@After
	// tearDown used to close the connection or clean up activities
	public void tearDown() {
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("Finishing TestDisplayGraphics class.\n");
	}
}
