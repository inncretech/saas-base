package com.inncretech.encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BouncyCastleProvider {

	// The default block size
	private static int blockSize = 16;
	// The key
	private byte[] key = null;
	// The initialisation vector needed by the CBC mode
	private static byte[] IV = new byte[blockSize];

	public static String encrypt(String data, String password) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		if (data != null) {
			Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
			encryptCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"), new IvParameterSpec(
					IV));
			byte[] encVal = encryptCipher.doFinal(data.getBytes());
			String encryptedValue = new BASE64Encoder().encode(encVal);
			return encryptedValue;
		}
		return null;
	}

	public static String decrypt(String encryptedData, String password) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		if (encryptedData != null) {
			Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
			decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(), "AES"), new IvParameterSpec(
					IV));
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
			byte[] decValue = decryptCipher.doFinal(decordedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
		}
		return null;
	}
}
