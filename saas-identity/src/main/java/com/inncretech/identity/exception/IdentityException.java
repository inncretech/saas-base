package com.inncretech.identity.exception;

import java.util.List;

public class IdentityException extends RuntimeException {

    private static final long serialVersionUID = -7509566285973906588L;

    private List<String> errorCodes;

    public IdentityException() {
    }

    public IdentityException(String message) {
        super(message);
    }

    public IdentityException(Throwable cause) {
        super(cause);
    }

    public IdentityException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IdentityException(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public IdentityException(String message, List<String> errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public IdentityException(Throwable cause, List<String> errorCodes) {
        super(cause);
        this.errorCodes = errorCodes;
    }

    public IdentityException(String message, Throwable cause, List<String> errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }
}
