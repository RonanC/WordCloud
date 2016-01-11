package ie.gmit.sw.test.graphics;

import org.junit.*;
import ie.gmit.sw.graphics.WordObject;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Ronan Tests the TestWordObject class in the graphics package
 */
public class TestWordObject {
	WordObject wo;
	// basic
	private String word;
	private int count;
	// font
	private int fontSize;
	private Color color;
	private Font font;

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Starting TestWordObject class.");
	}

	@Before
	public void setUp() {
		count = 0;
		// font
		fontSize = 0;
		wo = null;
	}

	@Test
	public void testConstructorShort() {
		System.out.println("Inside testConstructorShort()");
		word = "test";
		count = 5;

		wo = new WordObject(word, count);
		assertEquals(word, wo.getWord());
		assertEquals(count, wo.getCount());
	}

	@Test
	public void testConstructorLong() {
		System.out.println("Inside testConstructorLong()");
		word = "test";
		count = 5;
		fontSize = 24;
		color = new Color(255, 255, 255);
		font = new Font("Arial", Font.PLAIN, fontSize);

		wo = new WordObject(word, count, fontSize, color, font);
		assertEquals(word, wo.getWord());
		assertEquals(count, wo.getCount());
		assertEquals(fontSize, wo.getFontSize());
		assertEquals(color, wo.getColor());
		assertEquals(font, wo.getFont());
	}

	@Test
	public void testConstructorExtras() {
		System.out.println("Inside testConstructorExtras()");
		word = "test";
		count = 5;

		wo = new WordObject(word, count);

		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = image.createGraphics();
		wo.setGraphics2d(graphics2D);
		assertEquals(wo.getGraphics2d(), graphics2D);

		int x = 50;
		int y = 100;
		wo.setX(x);
		assertEquals(wo.getX(), x);
		wo.setY(y);
		assertEquals(wo.getY(), y);
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
