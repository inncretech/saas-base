package com.inncretech.encryption.bean;

import java.io.Serializable;

public class EncryptedPaymentDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5792643891845616202L;

	private String encryptedCardData;

	private String encryptedCVVData;

	private String encryptedKey;

	public String getEncryptedCardData() {
		return encryptedCardData;
	}

	public void setEncryptedCardData(String encryptedCardData) {
		this.encryptedCardData = encryptedCardData;
	}

	public String getEncryptedCVVData() {
		return encryptedCVVData;
	}

	public void setEncryptedCVVData(String encryptedCVVData) {
		this.encryptedCVVData = encryptedCVVData;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

}
