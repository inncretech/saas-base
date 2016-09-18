package com.inncretech.encryption;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.inncretech.encryption.auth.AuthManager;
import com.inncretech.encryption.bean.CardNumber;
import com.inncretech.encryption.bean.DecryptedPaymentDataBean;
import com.inncretech.encryption.bean.EncryptedCardBean;
import com.inncretech.encryption.bean.EncryptedPaymentDataBean;
import com.inncretech.encryption.bean.Payment;
import com.inncretech.encryption.config.BeanConfiguration;
import com.inncretech.encryption.exception.DecryptionException;
import com.inncretech.encryption.exception.EncryptionException;

@SuppressWarnings("unchecked")
public class EncryptionManager {

	private static Map<EncryptionProviderType, PaymentEncryptionProvider> paymentProviderMap;
	private static Map<EncryptionProviderType, IdentityEncryptionProvider> identityProviderMap;
	private static AuthManager authManager;
	private static PaymentDynamicEncryptionProvider cardEncr;
	private static Boolean init = false;
	static {
		Security.addProvider(new BouncyCastleProvider());
		if (!init)
			synchronized (EncryptionManager.class) {
				if (!init) {
					ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
					paymentProviderMap = context.getBean("paymentMap", HashMap.class);
					identityProviderMap = context.getBean("identityMap", HashMap.class);
					authManager = context.getBean(AuthManager.class);
					cardEncr = context.getBean(PaymentDynamicEncryptionProvider.class);
				}
			}

	}

	public static void main(String[] args) {
		System.out.println(authManager);
	}

	public static EncryptedPaymentDataBean encryptPayment(Payment payment, EncryptionProviderType type)
			throws EncryptionException {
		PaymentEncryptionProvider encryptionProvider = paymentProviderMap.get(type);
		if (null != encryptionProvider)
			return encryptionProvider.encryptPayment(payment);
		return null;
	}

	public static DecryptedPaymentDataBean decryptPayment(EncryptedPaymentDataBean payment, EncryptionProviderType type)
			throws DecryptionException {
		PaymentEncryptionProvider encryptionProvider = paymentProviderMap.get(type);
		if (null != encryptionProvider)
			return encryptionProvider.decryptPayment(payment);
		return null;
	}

	public static String encryptPassword(String clearTextPassword, EncryptionProviderType type)
			throws EncryptionException {
		IdentityEncryptionProvider identityEncryptionProvider = identityProviderMap.get(type);
		if (null != identityEncryptionProvider)
			return identityEncryptionProvider.encryptPassword(clearTextPassword);
		return null;
	}

	public static String decryptPassword(String encryptedPassword, EncryptionProviderType type)
			throws DecryptionException {
		IdentityEncryptionProvider identityEncryptionProvider = identityProviderMap.get(type);
		if (null != identityEncryptionProvider)
			return identityEncryptionProvider.decryptPassword(encryptedPassword);
		return null;
	}

	public static String getAuthHeader(Long millisec) throws EncryptionException {
		return authManager.getAuthHeader(millisec);
	}

	public static Boolean auth(String key, Long millisec) throws DecryptionException {
		return authManager.auth(key, millisec);
	}

	public static EncryptedCardBean encryptCard(CardNumber cardNumber) throws EncryptionException {

		return cardEncr.encryptCardNumber(cardNumber);
	}

	public static CardNumber decryptCard(EncryptedCardBean encryptedCardBean) throws DecryptionException {
		return cardEncr.decryptCardNumber(encryptedCardBean);
	}

}
