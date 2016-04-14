package com.inncretech.multitenancy.datasource.exceptions;

import java.util.List;

public class MultiTenancyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> errorCodes;

    public MultiTenancyException(Throwable cause) {
        super(cause);
    }

    public MultiTenancyException(String message) {
        super(message);
    }

    public MultiTenancyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiTenancyException(Throwable e, List<String> errorCodes, String message) {
        super(message, e);
        this.errorCodes = errorCodes;

    }

    public MultiTenancyException(String message, List<String> errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }
}