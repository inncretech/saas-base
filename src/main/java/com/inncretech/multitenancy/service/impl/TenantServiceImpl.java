package com.inncretech.multitenancy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.multitenancy.datasource.config.RoutingDataSource;
import com.inncretech.multitenancy.datasource.helper.DataSourceHelper;
import com.inncretech.multitenancy.master.db.entity.DataSourceConfig;
import com.inncretech.multitenancy.master.db.entity.Tenant;
import com.inncretech.multitenancy.master.db.repository.DataSourceConfigRepository;
import com.inncretech.multitenancy.master.db.repository.TenantRepository;
import com.inncretech.multitenancy.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.master.dto.TenantDTO;
import com.inncretech.multitenancy.master.entity.constants.DbType;
import com.inncretech.multitenancy.service.TenantService;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;
import com.inncretech.multitenancy.service.validator.TenantValidator;
import com.inncretech.multitenancy.util.IdGenerator;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private DataSourceConfigRepository dataSourceConfigRepository;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private RoutingDataSource routingDataSource;

	@Autowired
	private TenantValidator tenantValidator;

	private String driverClass = "com.mysql.jdbc.Driver";

	private int initialPoolSize = 3;

	private int minPoolSize = 1;

	private int maxPoolSize = 10;

	private int acquireIncrement = 1;

	@Transactional
	public TenantDTO addTenant(TenantDTO tenantDTO) throws InvalidArgumentException {

		tenantValidator.validateTenantDTO(tenantDTO);

		DataSourceConfig dataSourceConfig = dataSourceConfigRepository.findOne(tenantDTO.getDataSourceConfigId());

		if (dataSourceConfig == null) {
			throw new InvalidArgumentException("DB Config not found");
		}
		Tenant tenant = new Tenant();
		tenant.setDomain(tenantDTO.getDomain());
		tenant.setTenantDataSourceConfig(dataSourceConfig);
		tenant.setName(tenantDTO.getTenantName());
		tenant.setMasterTenantId(idGenerator.create());
		tenantRepository.save(tenant);
		tenantDTO.setTenantId(tenant.getTenantId());
		return tenantDTO;
	}

	public TenantDTO getTenantMetaData(Long tenantId) throws InvalidArgumentException {
		if (tenantId == null || tenantId < 0) {
			throw new InvalidArgumentException("Tenant id null");
		}
		Tenant tenant = tenantRepository.findOne(tenantId);
		if (tenant == null) {
			throw new InvalidArgumentException("Tenant id not found");
		}
		TenantDTO tenantDTO = new TenantDTO();
		tenantDTO.setTenantId(tenant.getTenantId());
		tenantDTO.setDomain(tenantDTO.getDomain());
		tenantDTO.setDataSourceConfigId(tenant.getTenantDataSourceConfig().getId());
		tenantDTO.setTenantName(tenant.getName());
		return tenantDTO;
	}

	public DataSourceConfigDTO addDataSourceConfig(DataSourceConfigDTO dataSourceConfigDTO) throws Exception {
		DataSourceConfig dataSourceConfigId = new DataSourceConfig();
		dataSourceConfigId.setDbName(dataSourceConfigDTO.getDbName());
		dataSourceConfigId.setDbPassword(dataSourceConfigDTO.getDbPassword());
		dataSourceConfigId.setDbUrl(dataSourceConfigDTO.getDbUrl());
		dataSourceConfigId.setDbUserName(dataSourceConfigDTO.getDbUserName());
		dataSourceConfigId.setDbType(DbType.valueOf(dataSourceConfigDTO.getDbType().name()));
		dataSourceConfigRepository.save(dataSourceConfigId);
		ComboPooledDataSource comboPooledDataSource = DataSourceHelper.createDataSource(driverClass, initialPoolSize,
				minPoolSize, maxPoolSize, acquireIncrement, "jdbc:mysql://" + dataSourceConfigId.getDbUrl() + "/"
						+ dataSourceConfigId.getDbName() + "?zeroDateTimeBehavior=convertToNull",
				dataSourceConfigId.getDbUserName(), dataSourceConfigId.getDbPassword());
		routingDataSource.updateDynmaicDataSource(dataSourceConfigId.getId(), comboPooledDataSource);
		dataSourceConfigDTO.setDataSourceConfigId(dataSourceConfigId.getId());
		return dataSourceConfigDTO;
	}
}
