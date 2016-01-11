package ie.gmit.sw.io;

import java.util.*;

/**
 * 
 * Used to sort the HashMap by order of word count.
 * 
 * @author Ronan
 */
class ValueComparator implements Comparator<String> {
	private Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	/**
	 * (non-Javadoc) Compares the count of each key from the maps value.
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}