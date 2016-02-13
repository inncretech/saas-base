package com.inncretech.multitenancy.datasource.master.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TenantDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tenantId;

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
