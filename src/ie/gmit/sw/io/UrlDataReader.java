package ie.gmit.sw.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Reads in data from a URL
 * 
 * @author Ronan
 *
 */
public class UrlDataReader implements DataReader {
	private String url;

	/**
	 * @param inputDataUrlname
	 *            takes in the url address
	 */
	public UrlDataReader(String inputDataUrlname) {
		this.url = inputDataUrlname;
	}


	/* (non-Javadoc)
	 * @see ie.gmit.sw.io.DataReader#getData()
	 */
	@Override
	public String getData() throws IOException {
		URL oracle;
		String html = "";
		oracle = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String sentence;
		while ((sentence = in.readLine()) != null) {
			html += sentence;
		}
		in.close();

		// Jsoup
		Document doc = Jsoup.parse(html);
		// Element link = doc.select("a").first();

		String text = doc.body().text(); // "An example link"
		System.out.println("\nJsoup text: " + text);

		// OTHER EXAMPLE USE OF JSOUP
		// String linkHref = link.attr("href"); // "http://example.com/"
		// String linkText = link.text(); // "example""

		// String linkOuterH = link.outerHtml(); // "<a
		// href="http://example.com"><b>example</b></a>"
		// String linkInnerH = link.html(); // "<b>example</b>"

		// System.out.println("link: " + link);
		//
		// System.out.println("linkHref: " + linkHref);
		// System.out.println("linkText: " + linkText);
		//
		// System.out.println("linkOuterH: " + linkOuterH);
		// System.out.println("linkInnerH: " + linkInnerH);

		return (text);
	}

	public String getDataLocation() {
		return url;
	}

	public void setDataLocation(String url) {
		this.url = url;
	}

}
