package ie.gmit.sw.io;

/**
 * @author Ronan
 *
 */
public interface DataReader {
	/**
	 * @return
	 * @throws Exception
	 */
	abstract String getData() throws Exception;
	/**
	 * @return
	 */
	public String getDataLocation();
	/**
	 * @param dataLocation
	 */
	public void setDataLocation(String dataLocation);
}
