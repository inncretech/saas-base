package com.inncretech.multitenancy.datasource.master.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.inncretech.multitenancy.datasource.dto.TenantAware;
import com.inncretech.multitenancy.datasource.master.dto.enums.DbLeaseType;

public class TenantDto implements Serializable, TenantAware {

    private Long tenantId;
    
    private DbLeaseType dbLeaseType;


    public DbLeaseType getDbLeaseType() {
		return dbLeaseType;
	}

	public void setDbLeaseType(DbLeaseType dbLeaseType) {
		this.dbLeaseType = dbLeaseType;
	}

	@NotBlank(message = "tenant name is blank")
    private String tenantName;

    @NotBlank(message = "domain is blank")
    private String domain;

    @NotNull(message = "db config id  blank")
    private Long dataSourceConfigId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getDataSourceConfigId() {
        return dataSourceConfigId;
    }

    public void setDataSourceConfigId(Long dataSourceConfigId) {
        this.dataSourceConfigId = dataSourceConfigId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
