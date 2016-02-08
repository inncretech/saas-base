package com.inncretech.multitenancy.service.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.multitenancy.master.dto.TenantDTO;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;

@Component
public class TenantValidator {

	@Autowired
	private Validator validator;

	public void validateTenantDTO(TenantDTO tenantDTO) throws InvalidArgumentException {
		List<String> errorCodes = new ArrayList<String>();
		if (tenantDTO == null) {
			errorCodes.add("tenant DTO is null");
		} else {

			Set<ConstraintViolation<TenantDTO>> tenantConstraintViolations = validator.validate(tenantDTO);
			if (tenantConstraintViolations != null && !tenantConstraintViolations.isEmpty()) {
				for (ConstraintViolation<TenantDTO> violation : tenantConstraintViolations) {
					errorCodes.add(violation.getMessage());
				}
			}
		}
		if (errorCodes != null && !errorCodes.isEmpty()) {
			throw new InvalidArgumentException("Some Field in tenantDTO has problem", errorCodes);
		}
	}
}
