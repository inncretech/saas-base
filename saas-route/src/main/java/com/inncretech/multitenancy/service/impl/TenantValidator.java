package com.inncretech.multitenancy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
import com.inncretech.multitenancy.datasource.master.dto.TenantDto;

@Component
public class TenantValidator {

	@Autowired
	private Validator validator;

	public void validateTenantDTO(TenantDto tenantDTO) {
		List<String> errorCodes = new ArrayList<String>();
		if (tenantDTO == null) {
			errorCodes.add("tenant DTO is null");
		} else {

			Set<ConstraintViolation<TenantDto>> tenantConstraintViolations = validator.validate(tenantDTO);
			if (tenantConstraintViolations != null && !tenantConstraintViolations.isEmpty()) {
				for (ConstraintViolation<TenantDto> violation : tenantConstraintViolations) {
					errorCodes.add(violation.getMessage());
				}
			}
		}
		if (errorCodes != null && !errorCodes.isEmpty()) {
			throw new MultiTenancyException("Some Field in tenantDTO has problem", errorCodes);
		}
	}
}
