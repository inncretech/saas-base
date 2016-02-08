package com.inncretech.multitenancy.service.exception;

import java.util.List;

public class InternalServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServiceException() {
	}

	public InternalServiceException(String message) {
		super(message);
	}

	public InternalServiceException(Throwable cause) {
		super(cause);
	}

	public InternalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServiceException(List<String> errorCodes) {
		super(errorCodes);
	}

	public InternalServiceException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public InternalServiceException(Throwable cause, List<String> errorCodes) {
		super(cause, errorCodes);
	}

	public InternalServiceException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause, errorCodes);
	}
}
