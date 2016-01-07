package ie.gmit.sw.draw;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DisplayGraphics extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int winWidth = 1000;
	private int winHeight = 500;
	private int midX = winWidth / 2;
	private int midY = winHeight / 2;
	
	// data
	private TreeMap<String, Integer> sortedWords;
	private ArrayList<WordObject> words;
	
	// count
	private int paintCount;
	
	public DisplayGraphics(ArrayList<WordObject> words) {
		this.words = words;
		paintCount = 0;
		start();
	}
	
	public void start(){
		JFrame fr2 = new JFrame();
		fr2.add(this);
		fr2.setSize(winWidth + 200, winHeight + 100);
		//fr2.setLayout(null);
		fr2.setVisible(true);
		
//		sortedWords = sw;
	}
	
	public void paint(Graphics g){
		paintCount++;
		System.out.println("\npainting: " + paintCount + "\n");
		setBackground(Color.white);
		
//		int xInc = 20; //(int) Math.round(winWidth * .02);
//		int yInc = 50; //(int) Math.round(winHeight * .02);
		
		int xInc = 100;
		int yInc = 25;
		
		int xStart = 50;//(int) Math.round(midX - winWidth * .01);
		int yStart  = 150;//(int) Math.round(midY - winHeight * .01);
		
		int x = midX - 200;
		int y = midY - 100;
		
		
		
//		int fontSize = 24;
		
		for (WordObject wordObject : words) {
			int fontSize = wordObject.getFontSize();
			int wordLen = wordObject.getWord().length();
//			int fontSizeX = wordObject.getFontSize() * 4;
			
			System.out.println("fontSize: " + fontSize * 3 + "\t\tx: " + x + "\t\txInc: " + (xInc + (fontSize * 2) + (wordLen * 10)) + "\t\twordLen: " + (wordLen * 10));
//			System.out.println("fontSize: " + fontSize * 3 + "\t\ty: " + y + "\t\tyInc: " + (yInc + fontSize) );

			
			g.setColor(wordObject.getColor());
			g.setFont(wordObject.getFont());
			g.drawString(wordObject.getWord(), x, y);

			//if(x < winWidth - (x + (wordObject.getWord().length() * wordObject.getFontSize())) / 2){
			if(x + fontSize * 3 + xInc < winWidth){
				x += xInc + (fontSize * 3) + (wordLen * 10);
			}else{
//				System.out.println("newLine");
				x = xStart;
				y += yInc + fontSize;
			}
			
				
			
//			if(y > (winHeight - yStart) || y > (y + fontSize / 2)){
//				y = fontSizeY;
//			}else{
//				y += fontSize / 2;
//			}
				
				if(y > winHeight){
					y = yStart;
//					y += yInc;
//				}else{
//					y = yStart;
				}
			
		}
		
		
	}
	
//	public static void main(String[] args) {
//		DisplayGraphics dg = new DisplayGraphics();
//
//	}
}
