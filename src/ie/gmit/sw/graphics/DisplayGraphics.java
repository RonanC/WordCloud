
package ie.gmit.sw.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * Does the actual painting/drawing of the words onto a new JPanel. First
 * calculates the positions of the words. A Spiral algorithm and randomness is
 * used to move the words so that they don't touch. There is a maximum number of
 * tries for the spiral algorithm, then the word is just placed. (needs
 * improvement)
 */
public class DisplayGraphics extends JPanel {

	private static final long serialVersionUID = 1L;

	private int winWidth = 1000;
	private int winHeight = 1000;
	private int midX = winWidth / 2;
	private int midY = winHeight / 2;
	private int maxWords;
	private int maxIntersectTries;
	private Color bgColor;

	// data
	private ArrayList<WordObject> words;
	private ArrayList<Rectangle> shapes;

	// count
	private int paintCount;

	// spiral/coordinate config
	private int x, y;
	private int direction;
	private int inc, smallInc;
	private Rectangle shape;
	private int wordHeight;
	private int wordWidth;

	// misc
	JFrame fr2;

	public DisplayGraphics(ArrayList<WordObject> words) {
		this.words = words;
		this.maxWords = 50;
		this.maxIntersectTries = 2500000;
		config();
	}

	public DisplayGraphics(ArrayList<WordObject> words, int maxWords, int maxIntersectTries) {
		this.words = words;
		this.maxWords = maxWords;
		this.maxIntersectTries = maxIntersectTries;
		config();
	}

	/**
	 * Configuration details for the frame, action listeners and some variables.
	 */
	private void config() {
		paintCount = 0;

		fr2 = new JFrame("Loading...");
		fr2.add(this);
		fr2.setSize(winWidth + 300, winHeight + 100);
		// fr2.setLayout(null);
		fr2.setVisible(true);

		// disable screen resize
		fr2.setResizable(false);
		
		// opens program centre of screen
		// fr2.setLocationRelativeTo(null);

		fr2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				bgColor = getColor();
				setBackground(bgColor);
			}
		});
	}

	/**
	 * Tests if there is an intersection.
	 * 
	 * @param shapeA
	 *            This is the total area.
	 * @param shapeB
	 *            This is the objects to be checked against the total Area.
	 * @return if the two areas intersect then true will be returned.
	 */
	private boolean testIntersection(Shape shapeA, Shape shapeB) {

		return shapeA.getBounds2D().intersects(shapeB.getBounds2D());
	}

	/**
	 * Returns a random color.
	 */
	private Color getColor() {
		// get a random colour
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color color = new Color(red, green, blue);

		return color;
	}

	/**
	 * Analyzes each word and chooses its position. This only happens once. This
	 * is the most CPU intensive part as the spiral algorithm lives here and
	 * each word runs in a loop of its own to find a free space on the
	 * JPanel(JFrame).
	 */
	private void calculateShapes(Graphics g) {
		// reset shapes list
		shapes = new ArrayList<Rectangle>();

		// set a random background colour
		bgColor = getColor();
		Area totalArea = null;

		// spiral/coordinate config
		x = midX;
		y = midY;
		direction = 0;
		inc = 10;
		smallInc = inc / 8;

		int wordCount = 0;

		for (WordObject wordObject : words) {
			wordCount++;
			updateTitleLoading(wordCount);

			// have a maximum number of words (default is 50)
			if (wordCount >= maxWords) {
				break;
			}

			// create new graphics object each time
			Graphics2D g2d = (Graphics2D) g.create();

			// set pallete
			g2d.setColor(wordObject.getColor());
			g2d.setFont(wordObject.getFont());
			// int fontSize = wordObject.getFontSize();
			// int wordLen = wordObject.getWord().length();

			// log
			System.out.println("wordCount: " + wordCount + "\t\tx: " + x + "\t\ty" + y + "\t\tword: "
					+ wordObject.getWord() + "\t\tcount: " + wordObject.getCount());

			// // add word
			// drawnWords.add(g2d);

			// get metrics of font
			FontMetrics metrics = g2d.getFontMetrics(wordObject.getFont());
			wordHeight = metrics.getHeight();
			wordWidth = metrics.stringWidth(wordObject.getWord());

			// create shape
			shape = new Rectangle(x, y - wordHeight / 2, wordWidth, wordHeight / 2);

			// set intersection to true (so that is it always checked)
			boolean intersection = true;

			if (shapes.size() > 0) {
				intersection = testIntersection(totalArea, shape);
			}

			if (shapes.size() == 0) {
				// System.out.println("shapes.size() == 0");

				saveWordPos(x, y, wordObject, g2d);
				// drawWord(x, y, wordObject, g2d);

				// g2d.draw(shape);
				totalArea = new Area(shape);
				shapes.add(shape);
			} else if (!intersection) {
				System.out.println("intersection: " + intersection);

				saveWordPos(x, y, wordObject, g2d);
				// drawWord(x, y, wordObject, g2d);

				// g2d.draw(shape);
				totalArea.add(new Area(shape));
				shapes.add(shape);
			} else {
				// custom spiral
				// final int MAX_N = 2500000; // winWidth * winHeight;
				int count = 1;

				// adds randomness to co-ords before looper on word begins
				AddRandomnessBeforeIntersectLooper();

				// loops each word max number of times trying to find a place
				// where it does not touch other words
				intersection = IntersectionLooper(totalArea, intersection, count);

				saveWordPos(x, y, wordObject, g2d);
				// drawWord(x, y, wordObject, g2d);

				// add area of shape (same area as string) to the total area
				totalArea.add(new Area(shape));

				// add shape to list
				shapes.add(shape);
			} // if else
		} // for each

		// safe guard in case text has less then max words
		fr2.setTitle("100% Loaded");
	}

	/**
	 * Updates the title of the JFrame(JPanel) used for drawing, so that the
	 * user can see the loading percentage.
	 */
	private void updateTitleLoading(int wordCount) {
		double loadPerc;
		loadPerc = (double) wordCount / (double) maxWords;
		// System.out.printf("loadPerc: %2.3f", loadPerc);
		int newTitle = (int) Math.round((loadPerc * 100));
		fr2.setTitle(newTitle + "% Loaded");
	}

	/**
	 * Adds big randomness to a words x/y position before the word goes into the
	 * intersection looper.
	 */
	private void AddRandomnessBeforeIntersectLooper() {
		// add randomness to inc before intersection while loop
		inc = (int) Math.round(inc * 0.8);
		inc %= 100;

		int xRan = winWidth;
		int yRan = winHeight;

		x += randomNum(xRan);
		y += randomNum(yRan);

		// log
		// System.out.println("x: " + x + "\t\ty: " + y);

		// reset x and/or y to middle if out of bounds
		if (x > winWidth - 50 || x < 50) {
			x = midX;
		}

		if (y > winHeight - 50 || y < 50) {
			y = midY;
		}
	}

	/**
	 * Adds small randomness on each iteration. Slowly moves a word around in a
	 * spiral trying to find a free space on the JPanel. If no space is found
	 * after the max iterations stated then the word is placed wherever the last
	 * x/y pos is.
	 */
	private boolean IntersectionLooper(Area totalArea, boolean intersection, int count) {
		// while intersection or count has not been reached (250'000 per
		// word) then continue
		while (intersection && count <= maxIntersectTries) {
			count++;

			// Spiral Algorithm
			switch (direction) {
			case 0: // r
				if (x >= winWidth) {
					direction = 1;
				} else {
					x += inc;
					y += smallInc;
				}
				break;

			case 1: // d
				if (y >= winHeight - 50) {
					direction = 2;
				} else {
					y += inc;
					x -= smallInc;
				}
				break;

			case 2: // l
				if (x <= 50) {
					direction = 3;
				} else {
					x -= inc;
					y -= smallInc;
				}
				break;

			case 3: // u
				if (y <= 50) {
					direction = 0;
				} else {
					y -= inc;
					x += smallInc;
				}
				break;

			default:
				break;
			}

			// add randomness per intersection every second count
			if (count % 2 == 0) {

				randomCoords();
			}

			shape = new Rectangle(x, y - wordHeight / 2, wordWidth, wordHeight / 2);

			intersection = testIntersection(totalArea, shape);

			if (!intersection) {
				if (shape.x + wordWidth >= winWidth) {
					intersection = true;
				}
			}
		}
		return intersection;
	}

	/**
	 * Chooses random coordinates. Used by the iteration looper for small
	 * randomness. If the randomness puts the coordinates out of the window the
	 * positions are reset to left of centre.
	 */
	private void randomCoords() {
		int xRan;
		int yRan;
		xRan = winWidth / 4;
		yRan = winHeight / 4;

		x += randomNum(xRan);
		y += randomNum(yRan);

		// log
		// System.out.println("x: " + x + "\t\ty: " + y);
		if (x > winWidth - 60 || x < 0 + 20 || x > (x + wordWidth + 20)) {
			x = midX - 300;
		}

		if (y > winHeight - 20 || y < 0 + 80) {
			y = midY;
		}
	}

	private void drawWord(int x, int y, WordObject wordObject, Graphics2D g2d) {
		Graphics2D graphics2d = wordObject.getGraphics2d();
		graphics2d.drawString(wordObject.getWord(), wordObject.getX(), wordObject.getY());
	}

	private void saveWordPos(int x, int y, WordObject wordObject, Graphics2D g2d) {
		wordObject.setGraphics2d(g2d);
		wordObject.setX(x);
		wordObject.setY(y);
	}

	/**
	 * Starts the word calculates off (happens once). Then paints the words as
	 * needed. This method is called automatically.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// calculate once
		if (paintCount == 0) {
			System.out.println("\nCalculating shapes...");
			calculateShapes(g);
			paintCount++;
		}

		// paint infinitely
		if (paintCount > 0) {
			if (paintCount == 1)
				System.out.println("\nPainting words...");

			paintCount++;
			// System.out.println("\npaintCount: " + paintCount + "\n");
			drawShapes();
			setBackground(bgColor);
		}
	} // paintComponent

	/**
	 * Draws the words according to the details in the WordObject object.
	 */
	private void drawShapes() {
		int count = 0;
		for (WordObject wordObject : words) {
			count++;
			if (count < maxWords) {
				drawWord(wordObject.getX(), wordObject.getY(), wordObject, wordObject.getGraphics2d());
				// System.out.println(wordObject.toString());
			}
		}
	}

	/**
	 * Creates a random number.
	 */
	private int randomNum(int maximum) {
		// creates a random number, plus/minus sign is also random
		int minimum = 0;
		Random rn = new Random();
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		int randomNum = minimum + i;
		// System.out.println("ranNum: " + randomNum);
		return randomNum;
	} // randomNum
} // class
