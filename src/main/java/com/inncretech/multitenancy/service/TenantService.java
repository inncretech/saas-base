package com.inncretech.multitenancy.service;

import com.inncretech.multitenancy.datasource.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.datasource.master.dto.TenantDTO;

public interface TenantService {

    TenantDTO addTenant(TenantDTO tenantDTO);

    TenantDTO getTenantMetaData(Long tenantId);

    DataSourceConfigDTO addDataSourceConfig(DataSourceConfigDTO dataSourceConfigDTO);
}
