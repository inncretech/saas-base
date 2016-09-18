package com.inncretech.encryption.auth;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.inncretech.encryption.BouncyCastleProvider;
import com.inncretech.encryption.EncryptionConstants;
import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

public class AuthManager {

	@Value("${encryption.masterAuthKey}")
	private String key;

	private Logger logger = LoggerFactory.getLogger(AuthManager.class);

	public String getAuthHeader(Long millisec) throws EncryptionException {
		try {
			return BouncyCastleProvider.encrypt(EncryptionConstants.AUTH_KEY, getKey(millisec));
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
	}
	
	private String getKey(Long millisec)
	{
		String millisecStr = String.valueOf(millisec);
		if (millisecStr.length() > 8)
			return key.substring(0, 8).concat(millisecStr.substring(millisecStr.length() - 8));
		return key;
	}

	public Boolean auth(String data, Long millisec) throws DecryptionException {
		String decrypted;
		try {
			decrypted = BouncyCastleProvider.decrypt(data, getKey(millisec));
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
		if (EncryptionConstants.AUTH_KEY.equals(decrypted))
			return Boolean.TRUE;
		return Boolean.FALSE;

	}


}
