package com.inncretech.encryption.auth;

import com.inncretech.encryption.EncryptionManager;
import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

public class EncryptionHandler {
	private static final Long tolerance = (long) (10 * 60 * 1000);

	public String getHeader() throws EncryptionException {
		Long millisec = System.currentTimeMillis();
		return EncryptionManager.getAuthHeader(millisec);
	}

	public void handleMessage(String encryptedHeader, Long millisec) {
		try {
			Boolean authPassed = EncryptionManager.auth(encryptedHeader, millisec);
			Long currentMillisec = System.currentTimeMillis();
			Long diff = currentMillisec - millisec;

			if (Math.abs(diff) > tolerance)
				authPassed = Boolean.FALSE;

			if (!authPassed)
				throw new RuntimeException("Authentication Failed. Please contact service owners");
		} catch (DecryptionException e) {
			throw new RuntimeException(e);
		}

	}

}
