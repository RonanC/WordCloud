package ie.gmit.sw.draw;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class DisplayGraphics extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int winWidth = 1000;
	private int winHeight = 1000;
	private int midX = winWidth / 2;
	private int midY = winHeight / 2;
	
	// data
	private TreeMap<String, Integer> sortedWords;
	private ArrayList<WordObject> words;
	ArrayList<Graphics2D> drawnWords;
	ArrayList<Rectangle> shapes;

	
	// count
	private int paintCount;
	
	public DisplayGraphics(ArrayList<WordObject> words) {
		drawnWords = new ArrayList<Graphics2D>();
		
		this.words = words;
		paintCount = 0;
		start();
		
	}
	
	private void createShapes() {
		int width = 0;
		int height = 0;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D)image.getGraphics();

		//Paint with texturing brush
		Rectangle2D rect2D = new Rectangle2D.Double(100, 100, width, height);
		graphics.setPaint(new TexturePaint(image, rect2D));
		graphics.fill(rect2D);

		//Draw text
		graphics.drawString("my text goes here", midX, midY);
	}

	public void start(){
		JFrame fr2 = new JFrame();
		fr2.add(this);
		fr2.setSize(winWidth + 300, winHeight + 100);
		//fr2.setLayout(null);
		fr2.setVisible(true);
	}

//	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
//		   Area areaA = new Area(shapeA);
//		   areaA.intersect(new Area(shapeB));
//		   return !areaA.isEmpty();
//		}
	
	public boolean testIntersection(Shape shapeA, Shape shapeB) {
	    return shapeA.getBounds2D().intersects(shapeB.getBounds2D());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//createShapes();
		shapes = new ArrayList<Rectangle>();
		
		paintCount++;
		System.out.println("\npainting: " + paintCount + "\n");
		setBackground(Color.white);
		
//		int xInc = 20; //(int) Math.round(winWidth * .02);
//		int yInc = 50; //(int) Math.round(winHeight * .02);
		
//		int xInc = 100;
//		int yInc = 25;
		
//		int xStart = 50;//(int) Math.round(midX - winWidth * .01);
//		int yStart  = 150;//(int) Math.round(midY - winHeight * .01);
		
//		int x = midX - 200;
//		int y = midY - 100;
		
//		int fontSize = 24;
		
		Area totalArea = null;
		
		
		// spiral stuff
//		int spiral [] [] = new int[1000][1000];
//		final int N = 200;
		
		
//		int current = 1;
//		// Start in the corner
//		int x = midX, y = midY;
//		int dx = 25, dy = 0;
		
		
		int x = midX, y = midY;
//		int x = 50, y = 75;
		int direction = 0;
		int inc = 10;
		int smallInc = inc / 8;
		
//		int incH = 20;
//		int incV = 10;
		
		
		for (WordObject wordObject : words) {
//			int x = midX, y = midY;
			// save area
			Graphics2D g2d = (Graphics2D) g.create();
			
			// set pallete
			g2d.setColor(wordObject.getColor());
			g2d.setFont(wordObject.getFont());
			int fontSize = wordObject.getFontSize();
			int wordLen = wordObject.getWord().length();
			
			
			// spiral init
//			int current = 1;
//			// Start in the corner
//			int x = midX, y = midY;
//			int dx = 1, dy = 0;
			
//			while (current <= N*N) {
//			    // Go in a straight line
//			    spiral[x][y] = current++;
//			    int nx = x + dx, ny = y + dy;
//			    // When you hit the edge...
//			    if (nx < 0 || nx == N || ny < 0 || ny == N || spiral[nx][ny] != 0) {
//			        // ...turn right
//			        int t = dy;
//			        dy = dx;
//			        dx = -t;
//			    }
//			    x += dx;
//			    y += dy;
//			    g2d.setColor(Color.blue);
//			    g2d.drawLine(x, y, x+5, y+5);
//			}
			

			

//			int fontSizeX = wordObject.getFontSize() * 4;
			
//			System.out.println("fontSize: " + fontSize * 3 + "\t\tx: " + x + "\t\txInc: " + (xInc + (fontSize * 2) + (wordLen * 10))  + "\t\ty: " + y + "\t\tyInc: " + (yInc + fontSize) + "\t\twordLen: " + (wordLen * 10) + "\t\tword: " + wordObject.getWord());
//			System.out.println("fontSize: " + fontSize * 3 + "\t\ty: " + y + "\t\tyInc: " + (yInc + fontSize) );
			
			
			
//			int x = midX, y = midY;
//			int x = 50, y = 50;
			
//			System.out.println("x: " + x + "\t\ty" + y + "\t\tword: " + wordObject.getWord());
			

			
			// add word
			drawnWords.add(g2d);
//			((Shape) g2d).getBounds2D().intersects(drawnWords.get(0).getClipBounds());
			
			FontMetrics metrics = g2d.getFontMetrics(wordObject.getFont());
//			System.out.println(metrics.getHeight() + " " + metrics.stringWidth(wordObject.getWord()));
			
			int wordHeight = metrics.getHeight();
			int wordWidth = metrics.stringWidth(wordObject.getWord());
			
//			g2d.fillRect(x, y - wordHeight, wordWidth, wordHeight);
//			g2d.drawRect(x, y - wordHeight / 2, wordWidth, wordHeight / 2);
			
			
			Rectangle shape = new Rectangle(x, y - wordHeight / 2, wordWidth, wordHeight / 2);
			
			
			
			
			
			
//			for (Rectangle rect : shapes) {
//				totalArea.add(new Area(rect));
//			}
			
//			Area areaB;
			boolean intersection = true;
			
			if(shapes.size() > 0){
				intersection = testIntersection(totalArea, shape);
				
//				areaA = new Area(shapes.get(0));
//				areaB = new Area(shape);
//				areaA.intersect(areaB);
//				intersection = !areaA.isEmpty();
				System.out.println("start-intersection: " + intersection);
			}

			
			if(shapes.size() == 0){
				System.out.println("shapes.size() == 0");
			
				
				g2d.drawString(wordObject.getWord(), x, y);
//				g2d.draw(shape);
				totalArea = new Area(shape);
				shapes.add(shape);
			} else if(!intersection){
				System.out.println("intersection: " + intersection);

				g2d.drawString(wordObject.getWord(), x, y);
				g2d.draw(shape);
				totalArea.add(new Area(shape));
				shapes.add(shape);
			}else{
				System.out.println("intersection: " + intersection);
				
//				areaA = new Area(shapes.get(0));

				
				// custom spiral
				final int MAX_N = 5000000; //winWidth * winHeight;
				int count = 1; 
//				boolean goingRight, goingDown, goingLeft, goingUp;
//				goingRight = true;
//				goingDown = goingLeft = goingUp = false;
//				
//				if(inc >= 20){
//					inc /= 1.1;
//					smallInc /= 1.1;
//				}
//				else if(inc <= 25){
//					inc *= 1.1;
//					smallInc *= 1.1;
//				}
				
				inc = (int) Math.round(inc * 0.8);
//				inc = (int) Math.round(incV * 0.8);
//				inc = (int) Math.round(inc * 1.1);

				
				inc %= 100;
//				inc %= 100;
						
				
				// between -90 and 90
//				int maximum = 30;
//				int minimum = 0;
//				Random rn = new Random();
//				int n = maximum - minimum + 1;
//				int i = rn.nextInt() % n;
//				int randomNum =  minimum + i;
//				System.out.println("ranNum: " + randomNum);
				
				
				
				
				
//				int incR, incL, incU, incD;
//				incR = 30;
//				incL = 20;
//				incU = 20;
//				incD = 20;
				
				while(intersection && count <= MAX_N){ // && current <= N*N
					
//					inc = (int) Math.round(inc * 1.5);
					
//					System.out.println("before intersection: " + intersection);
			
//					while (current <= N*N && intersection) {
//					    // Go in a straight line
//			    	if(x < 0){
//				        x = 1;
//			    	}
					
					
//					    spiral[x][y] = current++;
//					    int nx = x + dx, ny = y + dy;
//					    // When you hit the edge...
//					    if (nx < 0 || nx == N || ny < 0 || ny == N || spiral[nx][ny] != 0) {
//					        // ...turn right
//					        int t = dy;
//					        dy = dx;
//					        dx = -t;
//					    	
//					        // turn on y
////					        int t = dx;
////					        dx = dy;
////					        dy = -t;
//
//					    }
//					    x += dx*2;
//					    y += dy*2;
					    
					
					count++;
					
					
					
					// 0 = r
					// 1 = d
					// 2 = l
					// 3 = u
					
					switch (direction) {
					case 0: // r
						if(x >= winWidth){
							direction = 1;
						}
						else{
							x += inc;
							y += smallInc;
						}
						break;
						
					case 1: // d
						if(y >= winHeight - 50){
							direction = 2;
						}else{
							y += inc;
							x -= smallInc;
						}
						break;
						
					case 2: // l
						if(x <= 50){
							direction = 3;
						}
						else{
							x -= inc;
							y -= smallInc;
						}
						break;
						
					case 3: // u
						if(y <= 50){
							direction = 0;
						}
						else{
							y -= inc;
							x += smallInc;
						}
						break;

					default:
						break;
					}
					
//					if(goingRight){
//
//						
//						if(x >= winWidth){
//							goingRight = false;
//							goingDown = true;
//						}
//						else{
//							x += incR;
////							y += smallInc;
//						}
//						
//					} else if(goingDown){
//						if(y >= winHeight - 50){
//							goingDown = false;
//							goingLeft = true;
//						}else{
//							y += incD;
////							x -= smallInc;
//						}
//						
//					} else if(goingLeft){
//						if(x <= 50){
//							goingLeft = false;
//							goingUp = true;
//						}
//						else{
//							x -= incL;
////							y -= smallInc;
//						}
//						
//
//					} else if(goingUp){
//
//						
//						if(y <= 50){
//							goingUp = false;
//							goingRight = true;
//						}
//						else{
//							y -= incU;
////							x += smallInc;
//						}
//						
//
//					}
					
					
					    
						
						shape = new Rectangle(x, y - wordHeight / 2, wordWidth, wordHeight / 2);
						
						intersection = testIntersection(totalArea, shape);
						
						if(!intersection){
							if(shape.x + wordWidth >= winWidth){
								intersection = true;
							}
						}
						
//						System.out.print("intersection: " + intersection + "\t\tx: " + x + "\t\ty: " + y + "\t\tword: " + wordObject.getWord());
//						System.out.print("\t\tcount: " + count + "\t\t\t\t");
					}
//					areaB = new Area(shape);
//					areaA.intersect(areaB);
					// area a now contains the intersection, if it is empty then there is no intersection
//					intersection = !areaA.isEmpty();
//					System.out.println("after intersection: " + intersection);
//				}
				AffineTransform at = g2d.getTransform();
				
//				g2d.rotate(randomNum);
				g2d.drawString(wordObject.getWord(), x, y);
				System.out.println("x: " + x + "\t\ty" + y + "\t\tword: " + wordObject.getWord());
//				g2d.draw(shape);
				totalArea.add(new Area(shape));
				shapes.add(shape);
			}
			

			
			
//			if(shapes.size() == 0){
//				System.out.println("shapes.size() == 0");
//				
//				g2d.drawString(wordObject.getWord(), x, y);
//				shapes.add(shape);
//			} else if(!shapes.get(0).intersects(shape) && !shapes.get(0).contains(shape)){
//				System.out.println("!shapes.get(0).intersects(shape) && !shapes.get(0).contains(shape)");
//
//				g2d.drawString(wordObject.getWord(), x, y);
//				shapes.add(shape);
//			}else{
//				System.out.println("intersecting");
//				
//				while(shapes.get(0).intersects(shape) || shapes.get(0).contains(shape)){
//					System.out.println("x: " + x + "\t\ty: " + y);
//					x += xInc;
//					y += yInc;
//					shape = new Rectangle(x, y - wordHeight / 2, wordWidth, wordHeight / 2);
//				}
//				g2d.drawString(wordObject.getWord(), x, y);
//				shapes.add(shape);
//			}
			
			
			

			
//			g2d.draw(new Line2D.Double(midX, midY, x, y));
//			g2d.draw(new Rectangle(x, y, midX, midY));
			
//			if(g2d.getClipBounds().intersects(drawnWords.get(0).getClipBounds())){
//				while(g2d.getClipBounds().intersects(drawnWords.get(0).getClipBounds())){
//					x += xInc * 5;
//					y += yInc * 5;
//				}
			
//				g2d.drawString(wordObject.getWord(), x, y);
//			}
//			else{
//				g2d.drawString(wordObject.getWord(), x, y);
//			}
			
			
			
//			g2d.getBounds2D().intersects(drawnWords.get(0).getClipBounds());
			
//			((Shape) drawnWords.get(0)).getBounds2D().intersects(g2d.getClipBounds());
			
//			g.
//			Shape shape = new ;
//			Area area = new Area(s);
//			
//			area.intersects(r);
			
			// draw it 
//			if(!drawnWords.get(0).hit(g2d.getClipRect(), null, true)){
				
//			}
			

			//if(x < winWidth - (x + (wordObject.getWord().length() * wordObject.getFontSize())) / 2){

				
			
//			if(y > (winHeight - yStart) || y > (y + fontSize / 2)){
//				y = fontSizeY;
//			}else{
//				y += fontSize / 2;
//			}
				
			
			
			// incrementor
//			if(x + fontSize * 3 + xInc < winWidth){
//				x += xInc + (fontSize * 3) + (wordLen * 10);
//			}else{
//				x = xStart;
//				y += yInc + fontSize;
//			}
//			
//			if(y > winHeight){
//				y = yStart;
//			}
			
		}
		
		
		
	}

//	public void paint(Graphics g){
//		paintCount++;
//		System.out.println("\npainting: " + paintCount + "\n");
//		setBackground(Color.white);
//		
////		int xInc = 20; //(int) Math.round(winWidth * .02);
////		int yInc = 50; //(int) Math.round(winHeight * .02);
//		
//		int xInc = 100;
//		int yInc = 25;
//		
//		int xStart = 50;//(int) Math.round(midX - winWidth * .01);
//		int yStart  = 150;//(int) Math.round(midY - winHeight * .01);
//		
//		int x = midX - 200;
//		int y = midY - 100;
//		
////		int fontSize = 24;
//		
//		for (WordObject wordObject : words) {
//			// save area
//			Graphics2D g2d = (Graphics2D) g.create();
//			
//			int fontSize = wordObject.getFontSize();
//			int wordLen = wordObject.getWord().length();
////			int fontSizeX = wordObject.getFontSize() * 4;
//			
//			System.out.println("fontSize: " + fontSize * 3 + "\t\tx: " + x + "\t\txInc: " + (xInc + (fontSize * 2) + (wordLen * 10)) + "\t\twordLen: " + (wordLen * 10));
////			System.out.println("fontSize: " + fontSize * 3 + "\t\ty: " + y + "\t\tyInc: " + (yInc + fontSize) );
//
//			
//			g2d.setColor(wordObject.getColor());
//			g2d.setFont(wordObject.getFont());
//			
//			// add word
//			drawnWords.add(g2d);
//			
////			g.
////			Shape shape = new ;
////			Area area = new Area(s);
////			
////			area.intersects(r);
//			
//			// draw it 
//			if(!drawnWords.get(0).hit(g2d.getClipRect(), null, true)){
//				g2d.drawString(wordObject.getWord(), x, y);
//			}
//			
//
//			//if(x < winWidth - (x + (wordObject.getWord().length() * wordObject.getFontSize())) / 2){
//			if(x + fontSize * 3 + xInc < winWidth){
//				x += xInc + (fontSize * 3) + (wordLen * 10);
//			}else{
////				System.out.println("newLine");
//				x = xStart;
//				y += yInc + fontSize;
//			}
//			
//				
//			
////			if(y > (winHeight - yStart) || y > (y + fontSize / 2)){
////				y = fontSizeY;
////			}else{
////				y += fontSize / 2;
////			}
//				
//				if(y > winHeight){
//					y = yStart;
////					y += yInc;
////				}else{
////					y = yStart;
//				}
//			
//		}
//		
//		
//	}
	
//	public static void main(String[] args) {
//		DisplayGraphics dg = new DisplayGraphics();
//
//	}
}
