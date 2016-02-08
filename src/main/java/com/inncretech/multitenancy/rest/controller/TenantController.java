package com.inncretech.multitenancy.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inncretech.multitenancy.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.master.dto.TenantDTO;
import com.inncretech.multitenancy.service.TenantService;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;

@RestController
public class TenantController {

	@Autowired
	private TenantService tenantService;

	@RequestMapping(value = "/api/addTenant", method = RequestMethod.POST)
	public TenantDTO crateTenant(@RequestBody TenantDTO tenantDTO) throws InvalidArgumentException {
		return tenantService.addTenant(tenantDTO);
	}

	@RequestMapping(value = "/api/getTenantMetaData/{tenantId}", method = RequestMethod.GET)
	public TenantDTO getTenantMetaData(@PathVariable("tenantId") Long tenantId) throws InvalidArgumentException {
		return tenantService.getTenantMetaData(tenantId);
	}

	@RequestMapping(value = "/api/addDataSourceConfig", method = RequestMethod.POST)
	public DataSourceConfigDTO addDataSourceConfig(@RequestBody DataSourceConfigDTO dataSourceConfigDTO)
			throws Exception {
		return tenantService.addDataSourceConfig(dataSourceConfigDTO);
	}
}
