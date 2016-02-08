package com.inncretech.multitenancy.service.exception;

import java.util.List;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -7509566285973906588L;

	private List<String> errorCodes;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, List<String> errorCodes) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public ServiceException(Throwable cause, List<String> errorCodes) {
		super(cause);
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}
}
