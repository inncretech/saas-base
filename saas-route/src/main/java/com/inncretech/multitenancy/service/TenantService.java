package com.inncretech.multitenancy.service;

import com.inncretech.multitenancy.datasource.exceptions.DataSourceConfigException;
import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
import com.inncretech.multitenancy.datasource.exceptions.TenantDomainException;
import com.inncretech.multitenancy.datasource.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.datasource.master.dto.TenantDTO;

public interface TenantService {

    TenantDTO addTenant(TenantDTO tenantDTO) throws TenantDomainException, DataSourceConfigException, MultiTenancyException;

    TenantDTO getTenantMetaData(Long tenantId);

    DataSourceConfigDTO addDataSourceConfig(DataSourceConfigDTO dataSourceConfigDTO);
}
