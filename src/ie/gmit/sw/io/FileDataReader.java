package ie.gmit.sw.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Ronan
 *
 */
public class FileDataReader implements DataReader {
	/**
	 * 
	 */
	private String fileLocation;
	
	/**
	 * @param fileLocation
	 */
	public FileDataReader(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	/* (non-Javadoc)
	 * @see ie.gmit.sw.io.DataReader#getData()
	 */
	@Override
	public String getData() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(fileLocation));
		StringBuilder sb = new StringBuilder();
		String sentence;

		while ((sentence = in.readLine()) != null) {
			sb.append(sentence);
		}
		in.close();

		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see ie.gmit.sw.io.DataReader#getDataLocation()
	 */
	public String getDataLocation() {
		return fileLocation;
	}

	/* (non-Javadoc)
	 * @see ie.gmit.sw.io.DataReader#setDataLocation(java.lang.String)
	 */
	public void setDataLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
