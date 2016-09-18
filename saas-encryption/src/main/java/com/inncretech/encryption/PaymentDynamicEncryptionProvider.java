package com.inncretech.encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.inncretech.encryption.bean.CardNumber;
import com.inncretech.encryption.bean.EncryptedCardBean;
import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

public class PaymentDynamicEncryptionProvider {

	private Logger logger = LoggerFactory.getLogger(PaymentEncryptionProvider.class);

	@Value("${encryption.masterPaymentKey}")
	private String masterKey;

	public EncryptedCardBean encryptCardNumber(CardNumber cardNumber) throws EncryptionException {
		if (null == cardNumber || null == cardNumber.getNumber())
			return null;
		String number = cardNumber.getNumber();
		EncryptedCardBean encryptedCardBean = null;
		String randomKey = generateRandomKey();

		try {
			encryptedCardBean = new EncryptedCardBean();
			String encryptedCardNumber = BouncyCastleProvider.encrypt(number, randomKey);
			encryptedCardBean.setEncryptedCardNumber(encryptedCardNumber);
			String encryptedKey = BouncyCastleProvider.encrypt(randomKey, masterKey);
			encryptedCardBean.setEncryptedKey(encryptedKey);

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

		return encryptedCardBean;

	}

	public CardNumber decryptCardNumber(EncryptedCardBean encryptedCardBean) throws DecryptionException {
		CardNumber cardNumber = null;

		if (null == encryptedCardBean || null == encryptedCardBean.getEncryptedCardNumber()
				|| null == encryptedCardBean.getEncryptedKey())
			return null;

		// decrypt encryptedKey to get randomKey

		try {
			String encryptedKey = encryptedCardBean.getEncryptedKey();
			String randomKey = BouncyCastleProvider.decrypt(encryptedKey, masterKey);
			String encryptedCardNumber = encryptedCardBean.getEncryptedCardNumber();
			String cardNumberStr = BouncyCastleProvider.decrypt(encryptedCardNumber, randomKey);

			cardNumber = new CardNumber();
			cardNumber.setNumber(cardNumberStr);
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

		return cardNumber;
	}

	private String generateRandomKey() {
		String randomString = RandomStringUtils.randomAlphanumeric(16);
		if (!StringUtils.isBlank(randomString) && randomString.length() == 16) {
			return randomString;
		}
		return EncryptionConstants.DEFAULT_PRIMARY_KEY_PAYMENT;
	}

}
