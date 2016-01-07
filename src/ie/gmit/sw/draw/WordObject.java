package ie.gmit.sw.draw;

import java.awt.Color;
import java.awt.Font;

public class WordObject {
	private String word;
	private int count;
	
	private int fontSize;
	private Color color;
	private Font font;

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

	@Override
	public String toString() {
		return "WordObject [word=" + word + ", count=" + count + ", fontSize=" + fontSize + ", color=" + color
				+ ", font=" + font + "]";
	}
	
	
}
