package com.inncretech.multitenancy.service;

import com.inncretech.multitenancy.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.master.dto.TenantDTO;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;

public interface TenantService {

	TenantDTO addTenant(TenantDTO tenantDTO) throws InvalidArgumentException;

	TenantDTO getTenantMetaData(Long tenantId) throws InvalidArgumentException;

	DataSourceConfigDTO addDataSourceConfig(DataSourceConfigDTO dataSourceConfigDTO) throws Exception;

}
