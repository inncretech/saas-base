package com.inncretech.encryption.config;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import com.inncretech.encryption.EncryptionProviderType;
import com.inncretech.encryption.IdentityEncryptionProvider;
import com.inncretech.encryption.PaymentDynamicEncryptionProvider;
import com.inncretech.encryption.PaymentEncryptionProvider;
import com.inncretech.encryption.auth.AuthManager;

@Configuration
public class BeanConfiguration {

	@Bean
	public Map<EncryptionProviderType, PaymentEncryptionProvider> paymentMap() {
		Map<EncryptionProviderType, PaymentEncryptionProvider> paymentMap = new HashMap<EncryptionProviderType, PaymentEncryptionProvider>();
		paymentMap.put(EncryptionProviderType.STATIC_PAYMENT, staticProvider());

		return paymentMap;
	}

	@Bean
	public Map<EncryptionProviderType, IdentityEncryptionProvider> identityMap() {
		Map<EncryptionProviderType, IdentityEncryptionProvider> identityMap = new HashMap<EncryptionProviderType, IdentityEncryptionProvider>();
		identityMap.put(EncryptionProviderType.STATIC_IDENTITY,
				identityProvider());
		return identityMap;
	}

	@Bean
	public PaymentEncryptionProvider staticProvider() {
		return new PaymentEncryptionProvider();
	}

	@Bean
	public IdentityEncryptionProvider identityProvider() {
		return new IdentityEncryptionProvider();
	}

	@Bean
	public AuthManager authManager() {
		return new AuthManager();
	}
	
	@Bean
	public static PropertyPlaceholderConfigurer propertiesInit() throws MalformedURLException
	{
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		String keyfilePath = System.getProperty("keyfile.location");
		propertyPlaceholderConfigurer.setLocation(new UrlResource("file:"+keyfilePath+"/encr.properties"));
		propertyPlaceholderConfigurer.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");
		propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertyPlaceholderConfigurer;
	}
	
	@Bean
	public PaymentDynamicEncryptionProvider cardEncryptor()
	{
		return new PaymentDynamicEncryptionProvider();
	}
	
	
	

}
