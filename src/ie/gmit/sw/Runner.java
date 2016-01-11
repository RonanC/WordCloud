package ie.gmit.sw;

/**
 * This WordCloud program uses a Swing GUI, it takes in data via a File Chooser
 * or a Url. The windowing system is in this runner package. This data is
 * analyzed in the io package; The words are drawn/painted in the draw package,
 * where font styles, sizing and colors are picked.
 * 
 * @author Ronan
 * @version 1.0
 * @since 2015-01-09
 */
public class Runner {
	/**
	 * creates a new WindowSystem object.
	 * 
	 * @param args
	 *            The default input arguments, unused for this program.
	 */
	public static void main(String[] args) {
		new WindowSystem();
	}
}
