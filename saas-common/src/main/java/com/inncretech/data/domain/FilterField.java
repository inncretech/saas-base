package com.inncretech.data.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * An element of a filter parameter used to specify a specific filter that should be used in a search query
 *
 */
public class FilterField {
	private static final String DELIMITER = ":";
	private static final String PREFIX_SET = "{";
	private static final String SUFFIX_SET = "}";
	private static final String NEGATED = "-";

	private static final String SET_DELIMITER = "\\|";
	
	/**
	 * The name of the field to be used in the filter
	 */
	String field;
	
	/**
	 * The value if any associated with the field. This is the unprocessed value in case of range or sets of values
	 */
	String value;
	
	/**
	 * The field should be treated as an exclusion
	 */
	boolean negated;
	
	/**
	 * Is the value a range of values
	 */
	RangeValue range;
	
	/**
	 * Is the value a set of values
	 */
	Set<String> set;

	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

	public boolean isNegated() {
		return negated;
	}

	public RangeValue getRange() {
		return range;
	}

	public Set<String> getSet() {
		return set;
	}
	
	/**
	 * Constructor to create a filter
	 * @param filter
	 */
	public FilterField(String filter) {
		String tFilter = filter.trim();
		int delim = tFilter.indexOf(DELIMITER);
		if (delim != -1) {
			String fName = tFilter.substring(0, delim);
			if (fName.startsWith(NEGATED)) {
				this.field = fName.substring(NEGATED.length());
				this.negated = true;
			} else {
				this.field = fName;
			}
			this.value = tFilter.substring(delim + DELIMITER.length());
		} else {
			this.field = tFilter;
		}
		
		if (null != value) {
			// Check to see if this is a set
			String trimmedVal = value.trim();
			if (trimmedVal.startsWith(PREFIX_SET) && trimmedVal.endsWith(SUFFIX_SET)) {
				this.set = processValueAsSet(trimmedVal.substring(PREFIX_SET.length(), trimmedVal.length() - SUFFIX_SET.length()));
			} else if (RangeValue.isRange(trimmedVal)) {
				this.range = new RangeValue(trimmedVal);				
			}
		}
		
	}
	
	/**
	 * Process a set of values separated by '|' 
	 * @return
	 */
	
	Set<String> processValueAsSet(String setValue) {
		Set<String> ret = new HashSet<String>();
		String[] setVals = setValue.split(SET_DELIMITER);
		for (String val: setVals) {
			ret.add(val.trim());
		}		
		return ret;
	}
		
}
