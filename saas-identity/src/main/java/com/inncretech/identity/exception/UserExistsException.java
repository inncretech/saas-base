package com.inncretech.identity.exception;

import java.util.List;

public class UserExistsException extends IdentityException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UserExistsException() {
        super();
    }

    public UserExistsException(List<String> errorCodes) {
        super(errorCodes);
    }

    public UserExistsException(String message, List<String> errorCodes) {
        super(message, errorCodes);
    }

    public UserExistsException(String message, Throwable cause, List<String> errorCodes) {
        super(message, cause, errorCodes);
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException(Throwable cause, List<String> errorCodes) {
        super(cause, errorCodes);
    }

    public UserExistsException(Throwable cause) {
        super(cause);
    }
}
