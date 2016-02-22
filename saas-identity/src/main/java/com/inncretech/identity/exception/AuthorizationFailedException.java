package com.inncretech.identity.exception;

import java.util.Collections;

public class AuthorizationFailedException extends IdentityException {

    public AuthorizationFailedException(String message) {
        super(message, Collections.singletonList(ErrorCode.AUTHORIZATION_FAILED));
    }
}
