package com.inncretech.multitenancy.datasource.tenant.entity.convertor;

import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
@Converter(autoApply = true)
public class StringListConvertor implements AttributeConverter<List, String> {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(StringListConvertor.class);

	@Override
	public String convertToDatabaseColumn(List attribute) {
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
	public List convertToEntityAttribute(String dbData) {
		List<String> map = null;

		if (!StringUtils.isEmpty(dbData)) {
			try {
				map = OBJECT_MAPPER.readValue(dbData, new TypeReference<List<String>>() {
				});
			} catch (Exception e) {
				LOGGER.error("Error converting string to map ", e);
			}

		}

		return map;
	}

}