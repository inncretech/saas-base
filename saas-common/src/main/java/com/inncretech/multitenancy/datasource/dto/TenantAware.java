package com.inncretech.multitenancy.datasource.dto;

public interface TenantAware {

    Long getTenantId();

    void setTenantId(Long tenantId);
}
