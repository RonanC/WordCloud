package ie.gmit.sw.io;

/**
 * Data Reader Interface, used in Factory.
 * 
 * @author Ronan
 *
 */
public interface DataReader {
	/**
	 * @return String of data
	 * @throws Exception
	 *             If data cannot be found
	 */
	abstract String getData() throws Exception;

	/**
	 * @return Data
	 */
	public String getDataLocation();

	/**
	 * @param dataLocation
	 *            File location
	 */
	public void setDataLocation(String dataLocation);
}
