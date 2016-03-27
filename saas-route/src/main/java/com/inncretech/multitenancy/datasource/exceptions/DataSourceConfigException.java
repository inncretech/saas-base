package com.inncretech.multitenancy.datasource.exceptions;

import java.util.List;

public class DataSourceConfigException extends Exception {
	
	private List<String> errorCodes;

	public DataSourceConfigException() {
	}

	public DataSourceConfigException(String message) {
		super(message);
	}

	public DataSourceConfigException(Throwable cause) {
		super(cause);
	}

	public DataSourceConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceConfigException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public DataSourceConfigException(String message, List<String> errorCodes) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public DataSourceConfigException(Throwable cause, List<String> errorCodes) {
		super(cause);
		this.errorCodes = errorCodes;
	}

	public DataSourceConfigException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}

}
