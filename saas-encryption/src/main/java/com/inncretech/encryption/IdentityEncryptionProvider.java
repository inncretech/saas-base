package com.inncretech.encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

public class IdentityEncryptionProvider {
	private static final Logger logger = LoggerFactory.getLogger(IdentityEncryptionProvider.class);

	private static String masterKey = "ABC123DE";

	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	public static String encryptPassword(String clearTextPassword) throws EncryptionException {
		if (null == clearTextPassword)
			return null;
		String encryptedPassword = null;
		try {

			encryptedPassword = BouncyCastleProvider.encrypt(clearTextPassword, generateRandomKeyData());
			clearTextPassword = null;
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage(), e);
			throw new EncryptionException(e.getMessage());
		}

		return encryptedPassword;

	}

	public static String decryptPassword(String encryptedPassword) throws DecryptionException {
		if (null == encryptedPassword)
			return null;
		String password = null;
		try {
			password = BouncyCastleProvider.decrypt(encryptedPassword, generateRandomKeyData());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new DecryptionException(e.getMessage());
		}
		return password;

	}

	private static String generateRandomKeyData() {
		return masterKey.substring(0, 8) + EncryptionConstants.DEFAULT_PRIMARY_KEY_IDENTITY.substring(0, 8);
	}

}
