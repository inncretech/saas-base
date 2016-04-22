package com.inncretech.data.domain;

/**
 * A specific type of value that may be used in a filter to identify a range of possible values (inclusive)
 *
 */
public class RangeValue {
	static final String delimiter = "..";
	private static final char PREFIX_RANGE = '[';
	private static final char SUFFIX_RANGE = ']';
	private static final char PREFIX_RANGE_EXCLUSIVE = '(';
	private static final char SUFFIX_RANGE_EXCLUSIVE = ')';
	
	/**
	 * The start of the range; Empty if there is no start
	 */
	String start;
	
	/**
	 * Is the beginning of the range exclusive
	 */
	boolean exclusiveStart;
	
	/**
	 * The end of the range; Empty if there is no end
	 */
	String end;
	
	/**
	 * Is the end of the range exclusive
	 */
	boolean exclusiveEnd;
	

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public boolean isExclusiveStart() {
		return exclusiveStart;
	}

	public boolean isExclusiveEnd() {
		return exclusiveEnd;
	}

	/**
	 * Constructor to create a range value from a string that begins with either 
	 * '[' - inclusive or '(' - exclusive and ends with either
	 * ']' - inclusive or ')' - exclusive with the range bounds separated by '..' . 
	 * @param value
	 */
	public RangeValue(String value) {
		// Trim the range
		String tVal = value.trim();
		char rbegin = tVal.charAt(0);
		int len = tVal.length();
		char rend = tVal.charAt(len-1);
		
		
		if ((rbegin == PREFIX_RANGE || rbegin == PREFIX_RANGE_EXCLUSIVE) && (rend == SUFFIX_RANGE || rend == SUFFIX_RANGE_EXCLUSIVE) ) {
			tVal = tVal.substring(1, len - 1); 
		} else {
			throw new IllegalArgumentException("Range should be specfied with [ .. ] or ( .. )");
		}
		
		int del = tVal.indexOf(delimiter);
		if (del != -1) {
			this.start = tVal.substring(0, del).trim();
			this.end = tVal.substring(del + delimiter.length()).trim();
		} else {
			this.start = tVal.trim();
			this.end = new String();
		}
		if (rbegin == PREFIX_RANGE_EXCLUSIVE && !this.start.isEmpty()) {
			this.exclusiveStart = true;
		}
		if (rend == SUFFIX_RANGE_EXCLUSIVE && !this.end.isEmpty()) {
			this.exclusiveEnd = true;
		}
	}
	
	/**
	 * Is this string a range
	 * @param value 
	 * @return true if a range is specified.
	 */
	public static boolean isRange(String value) {
		String tVal = value.trim();
		char rbegin = tVal.charAt(0);
		int len = tVal.length();
		char rend = tVal.charAt(len-1);
		
		if ((rbegin == PREFIX_RANGE || rbegin == PREFIX_RANGE_EXCLUSIVE) && (rend == SUFFIX_RANGE || rend == SUFFIX_RANGE_EXCLUSIVE) ) {
			return true;
		} else {
			return false;
		}
	}
}
