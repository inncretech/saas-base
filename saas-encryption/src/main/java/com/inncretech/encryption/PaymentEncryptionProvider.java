package com.inncretech.encryption;

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
import org.springframework.util.StringUtils;

import com.inncretech.encryption.bean.DecryptedPaymentDataBean;
import com.inncretech.encryption.bean.EncryptedPaymentDataBean;
import com.inncretech.encryption.bean.Payment;
import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

public class PaymentEncryptionProvider implements GenericProvider {

	private Logger logger = LoggerFactory.getLogger(PaymentEncryptionProvider.class);

	@Value("${encryption.masterPaymentKey}")
	private String masterKey;

	public EncryptedPaymentDataBean encryptPayment(Payment payment) throws EncryptionException {
		EncryptedPaymentDataBean encryptedPaymentDataBean = null;
		if (null == payment.getCardInfo())
			return null;

		String cardNumString = null;

		if (null != payment.getCardInfo().getCardNumber()) {
			cardNumString = payment.getCardInfo().getCardNumber();
		}
		String cardCvvString = null;
		if (null != payment.getCardInfo().getCvv()) {
			cardCvvString = payment.getCardInfo().getCvv();
		}
		if (StringUtils.hasLength(cardNumString) || StringUtils.hasLength(cardCvvString)) {
			try {

				encryptedPaymentDataBean = new EncryptedPaymentDataBean();
				// Encrypt and set card number
				if (StringUtils.hasLength(cardNumString)) {
					String encryptedCardNumber = BouncyCastleProvider.encrypt(cardNumString, generateRandomKeyData());
					if (StringUtils.hasLength(encryptedCardNumber)) {
						encryptedPaymentDataBean.setEncryptedCardData(encryptedCardNumber);
					}
				}
				// Encrypt and set card cvv number
				if (StringUtils.hasLength(cardCvvString)) {
					String encryptedCardCvvNumber = BouncyCastleProvider
							.encrypt(cardCvvString, generateRandomKeyData());
					if (StringUtils.hasLength(encryptedCardCvvNumber)) {
						encryptedPaymentDataBean.setEncryptedCVVData(encryptedCardCvvNumber);
					}
				}
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
		return encryptedPaymentDataBean;

	}

	public DecryptedPaymentDataBean decryptPayment(EncryptedPaymentDataBean encryptedPaymentDataBean)
			throws DecryptionException {
		DecryptedPaymentDataBean decryptedPaymentDataBean = null;
		try {
			if (null != encryptedPaymentDataBean
					&& (StringUtils.hasLength((encryptedPaymentDataBean.getEncryptedCardData())) || StringUtils
							.hasLength(encryptedPaymentDataBean.getEncryptedCVVData()))) {

				decryptedPaymentDataBean = new DecryptedPaymentDataBean();
				// Decrypt Card Number
				String cardNumber = BouncyCastleProvider.decrypt(encryptedPaymentDataBean.getEncryptedCardData(),
						generateRandomKeyData());
				decryptedPaymentDataBean.setCardNumber(cardNumber);

				// Decrypt CVV Number
				String cvvNumber = BouncyCastleProvider.decrypt(encryptedPaymentDataBean.getEncryptedCVVData(),
						generateRandomKeyData());
				decryptedPaymentDataBean.setCvv(cvvNumber);

			}
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
		return decryptedPaymentDataBean;
	}

	public String generateRandomKeyData() {
		return masterKey.substring(0, 8) + EncryptionConstants.DEFAULT_PRIMARY_KEY_PAYMENT.substring(0, 8);
	}

}
