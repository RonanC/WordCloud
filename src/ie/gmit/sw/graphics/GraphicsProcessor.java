package ie.gmit.sw.graphics;

/**
 * Processes the other graphics classes.
 * 
 * @author Ronan
 *
 */
public class GraphicsProcessor extends Processor {
	/**
	 * Creates a new displayGraphics object which draws the words to the screen
	 */
	public void displayGraphics() {
		new DisplayGraphics(wordAnalysis.getWords(), maxWords, maxFontSize);
	}

}
