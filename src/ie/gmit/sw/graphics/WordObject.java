package ie.gmit.sw.graphics;

import java.awt.*;

/**
 * 
 * Contains all the information for each word. All the font details, coordinates
 * and the graphics object.
 *
 */
public class WordObject {
	private String word;
	private int count;

	private int fontSize;
	private Color color;
	private Font font;

	// on canvas
	private Graphics2D graphics2d;
	private int x;
	private int y;

	public WordObject(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}

	public WordObject(String word, int count, int fontSize, Color color, Font font) {
		super();
		this.word = word;
		this.count = count;
		this.fontSize = fontSize;
		this.color = color;
		this.font = font;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Graphics2D getGraphics2d() {
		return graphics2d;
	}

	public void setGraphics2d(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "WordObject [word=" + word + ", count=" + count + ", fontSize=" + fontSize + ", color=" + color
				+ ", font=" + font + ", graphics2d=" + graphics2d + ", x=" + x + ", y=" + y + "]";
	}

}
