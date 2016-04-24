package com.inncretech.multitenancy.service;

import com.inncretech.multitenancy.datasource.exceptions.DataSourceConfigException;
import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
import com.inncretech.multitenancy.datasource.exceptions.TenantDomainException;
import com.inncretech.multitenancy.datasource.master.dto.DataSourceConfigDto;
import com.inncretech.multitenancy.datasource.master.dto.TenantDto;

public interface TenantService {

    TenantDto addTenant(TenantDto tenantDTO) throws TenantDomainException, DataSourceConfigException, MultiTenancyException;

    TenantDto getTenant(Long tenantId);

    DataSourceConfigDto addDataSourceConfig(DataSourceConfigDto dataSourceConfigDTO);
}
