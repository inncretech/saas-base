package com.inncretech.multitenancy.datasource.tenant.entity.convertor;

import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class LongListConvertor implements AttributeConverter<Set<Long>, String> {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(LongListConvertor.class);

	@Override
	public String convertToDatabaseColumn(Set<Long> attribute) {
		String finalString = null;
		if (attribute != null && !attribute.isEmpty()) {
			try {
				finalString = OBJECT_MAPPER.writeValueAsString(attribute);
			} catch (Exception e) {
				LOGGER.error("Error converting map to string", e);
			}
		}
		return finalString;
	}

	@Override
	public Set<Long> convertToEntityAttribute(String dbData) {
		Set<Long> map = null;

		if (!StringUtils.isEmpty(dbData)) {
			try {
				map = OBJECT_MAPPER.readValue(dbData, new TypeReference<Set<Long>>() {
				});
			} catch (Exception e) {
				LOGGER.error("Error converting string to map ", e);
			}

		}

		return map;
	}

}