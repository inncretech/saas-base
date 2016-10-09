package com.inncretech.multitenancy.datasource.tenant.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.inncretech.encryption.IdentityEncryptionProvider;

@Converter
public class PasswordConvertor implements AttributeConverter<String, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LongListConvertor.class);

	@Override
	public String convertToDatabaseColumn(String attribute) {
		String finalString = null;
		if (attribute != null && !attribute.isEmpty()) {
			try {
				finalString = IdentityEncryptionProvider.encryptPassword(attribute);

			} catch (Exception e) {
				LOGGER.error("Error converting map to string", e);
			}
		}
		return finalString;
	}

	@Override
	public String convertToEntityAttribute(String encryptedPassword) {
		String map = null;

		if (!StringUtils.isEmpty(encryptedPassword)) {
			try {
				map = IdentityEncryptionProvider.decryptPassword(encryptedPassword);
			} catch (Exception e) {
				LOGGER.error("Error converting string to map ", e);
			}

		}

		return map;
	}

}