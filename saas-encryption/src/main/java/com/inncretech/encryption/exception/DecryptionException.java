package com.inncretech.encryption.exception;

public class DecryptionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8413150945752073436L;

	public DecryptionException() {

	}

	public DecryptionException(String message) {
		super(message);

	}

	public DecryptionException(Throwable cause) {
		super(cause);

	}

	public DecryptionException(String message, Throwable cause) {
		super(message, cause);

	}

}
