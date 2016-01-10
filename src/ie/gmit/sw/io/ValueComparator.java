package ie.gmit.sw.io;

import java.util.*;

/**
 * 
 * Used to sort the HashMap by order of word count.
 *
 */
class ValueComparator implements Comparator<String> {
	private Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	/**
	 * Compares the count of each key from the maps value.
	 */
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}