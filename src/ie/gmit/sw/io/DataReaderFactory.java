package ie.gmit.sw.io;

/**
 * This is a singleton factory that produces either file or url data readers
 * 
 * @author Ronan
 *
 */
public class DataReaderFactory {
	// static instance of DataReaderFactory
	private static DataReaderFactory instance = new DataReaderFactory();

	// private constructor (the class cannot be instantiated)
	private DataReaderFactory() {
	}

	// returns the only object available (static method)
	/**
	 * @return A static instance of the DataReaderFactory (the only instance)
	 */
	public static DataReaderFactory getInstance() {
		return instance;
	}

	/**
	 * @param readerType
	 *            type of reader, either file or url
	 * @param fileLocation
	 *            location of data file
	 * @return returns either a url or file reader
	 */
	public DataReader getReader(String readerType, String fileLocation) {
		if (readerType == null) {
			return null;
		} else if (readerType.equalsIgnoreCase("url")) {
			return new UrlDataReader(fileLocation);
		} else if (readerType.equalsIgnoreCase("file")) {
			return new FileDataReader(fileLocation);
		}

		return null;
	}

}
