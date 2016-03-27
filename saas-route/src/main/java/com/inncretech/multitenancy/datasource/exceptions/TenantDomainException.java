package com.inncretech.multitenancy.datasource.exceptions;

import java.util.List;

public class TenantDomainException extends Exception {
	
	private List<String> errorCodes;

	public TenantDomainException() {
	}

	public TenantDomainException(String message) {
		super(message);
	}

	public TenantDomainException(Throwable cause) {
		super(cause);
	}

	public TenantDomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public TenantDomainException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public TenantDomainException(String message, List<String> errorCodes) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public TenantDomainException(Throwable cause, List<String> errorCodes) {
		super(cause);
		this.errorCodes = errorCodes;
	}

	public TenantDomainException(String message, Throwable cause, List<String> errorCodes) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}
}