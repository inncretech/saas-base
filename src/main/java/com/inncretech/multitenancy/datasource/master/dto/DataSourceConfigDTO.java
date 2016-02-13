package com.inncretech.multitenancy.datasource.master.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.inncretech.multitenancy.datasource.enums.DbLeaseType;


public class DataSourceConfigDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long dataSourceConfigId;
	
	private DbLeaseType dbInstanceType;

	@NotBlank(message="db url is blank")
	private String dbUrl;

	@NotBlank(message="db username is blank")
	private String dbUserName;

	@NotBlank(message="db password is blank")
	private String dbPassword;

	@NotBlank(message="db name is blank")
	private String dbName;

	public Long getDataSourceConfigId() {
		return dataSourceConfigId;
	}

	public void setDataSourceConfigId(Long dataSourceConfigId) {
		this.dataSourceConfigId = dataSourceConfigId;
	}

	public DbLeaseType getDbInstanceType() {
		return dbInstanceType;
	}

	public void setDbInstanceType(DbLeaseType dbInstanceType) {
		this.dbInstanceType = dbInstanceType;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
