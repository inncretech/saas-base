package com.inncretech.multitenancy.master.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inncretech.multitenancy.master.dto.constants.DbType;

public class DataSourceConfigDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long dataSourceConfigId;
	private DbType dbType;

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

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
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

	public static void main(String[] args) throws Exception {
		DataSourceConfigDTO dataSourceConfigDTO = new DataSourceConfigDTO();
		dataSourceConfigDTO.setDbName("mt2");
		dataSourceConfigDTO.setDbType(DbType.SHARED);
		dataSourceConfigDTO.setDbPassword("root");
		dataSourceConfigDTO.setDbUserName("root");
		dataSourceConfigDTO.setDbUrl("localhost:3306");
		System.out.println(new ObjectMapper().writeValueAsString(dataSourceConfigDTO));
	}

}
