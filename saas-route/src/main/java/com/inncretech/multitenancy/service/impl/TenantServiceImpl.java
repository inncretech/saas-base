package com.inncretech.multitenancy.service.impl;

import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.multitenancy.datasource.config.RoutingDataSource;
import com.inncretech.multitenancy.datasource.enums.DbLeaseType;
import com.inncretech.multitenancy.datasource.exceptions.DataSourceConfigException;
import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
import com.inncretech.multitenancy.datasource.exceptions.TenantDomainException;
import com.inncretech.multitenancy.datasource.master.dao.DataSourceConfigRepository;
import com.inncretech.multitenancy.datasource.master.dao.TenantRepository;
import com.inncretech.multitenancy.datasource.master.dto.DataSourceConfigDto;
import com.inncretech.multitenancy.datasource.master.dto.TenantDto;
import com.inncretech.multitenancy.datasource.master.entity.DataSourceConfig;
import com.inncretech.multitenancy.datasource.master.entity.Tenant;
import com.inncretech.multitenancy.datasource.utils.DataSourceUtils;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;
import com.inncretech.multitenancy.service.TenantService;
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
	public TenantDto addTenant(TenantDto tenantDTO) throws TenantDomainException, DataSourceConfigException,
			MultiTenancyException {
		DataSourceConfig dataSourceConfig = findDataSourceConfigIdForTenant(tenantDTO);

		if (dataSourceConfig == null) {
			throw new MultiTenancyException("DB Config not found");
		}

		System.out.println("TenantService : addTenant DSC " + dataSourceConfig.toString());
		tenantDTO.setDataSourceConfigId(dataSourceConfig.getId());
		tenantValidator.validateTenantDto(tenantDTO);

		List<Tenant> tenants = tenantRepository.findByDomain(tenantDTO.getDomain());
		if (tenants != null && tenants.size() > 0) {
			throw new TenantDomainException("Domain AlreadyExists");
		}

		Tenant tenant = new Tenant();
		tenant.setDomain(tenantDTO.getDomain());
		tenant.setTenantDataSourceConfig(dataSourceConfig);
		tenant.setName(tenantDTO.getTenantName());
		tenant.setMasterTenantId(idGenerator.create(dataSourceConfig.getId().intValue()));
		tenant.setId(idGenerator.create(dataSourceConfig.getId().intValue()));
		tenantRepository.save(tenant);
		tenantDTO.setTenantId(tenant.getId());
		return tenantDTO;
	}

	public DataSourceConfig findDataSourceConfigIdForTenant(TenantDto tenantDTO) throws DataSourceConfigException {
		List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();
		if (dataSourceConfigs != null && !dataSourceConfigs.isEmpty()) {
			Random random = new Random();
			int dataSourceConfigId = random.nextInt(dataSourceConfigs.size());			
			return dataSourceConfigs.get(dataSourceConfigId);

		}
		throw new DataSourceConfigException("No valid DataSourceConfig Found");
	}

	@Transactional
	public TenantDto getTenant(Long tenantId) {
		if (tenantId == null || tenantId < 0) {
			throw new MultiTenancyException("Tenant id null");
		}
		Tenant tenant = tenantRepository.findOne(tenantId);
		if (tenant == null) {
			throw new MultiTenancyException("Tenant id not found");
		}
		TenantDto tenantDTO = new TenantDto();
		tenantDTO.setTenantId(tenant.getId());
		tenantDTO.setDomain(tenant.getDomain());
		if (tenant.getTenantDataSourceConfig() != null && tenant.getTenantDataSourceConfig().getDbLeaseType() != null) {
			tenantDTO.setDbLeaseType(com.inncretech.data.domain.enums.DbLeaseType.valueOf(tenant
					.getTenantDataSourceConfig().getDbLeaseType().name()));
		}
		tenantDTO.setDataSourceConfigId(tenant.getTenantDataSourceConfig().getId());
		tenantDTO.setTenantName(tenant.getName());
		return tenantDTO;
	}

	public DataSourceConfigDto addDataSourceConfig(DataSourceConfigDto dataSourceConfigDTO) {
		DataSourceConfig dataSourceConfigId = new DataSourceConfig();
		dataSourceConfigId.setDbName(dataSourceConfigDTO.getDbName());
		dataSourceConfigId.setDbPassword(dataSourceConfigDTO.getDbPassword());
		dataSourceConfigId.setDbUrl(dataSourceConfigDTO.getDbUrl());
		dataSourceConfigId.setDbUserName(dataSourceConfigDTO.getDbUserName());
		dataSourceConfigId.setDbLeaseType(DbLeaseType.valueOf(dataSourceConfigDTO.getDbInstanceType().name()));
		dataSourceConfigRepository.save(dataSourceConfigId);
		ComboPooledDataSource comboPooledDataSource;
		try {
			comboPooledDataSource = DataSourceUtils.createDataSource(driverClass, initialPoolSize, minPoolSize,
					maxPoolSize, acquireIncrement, "jdbc:mysql://" + dataSourceConfigId.getDbUrl() + "/"
							+ dataSourceConfigId.getDbName() + "?zeroDateTimeBehavior=convertToNull",
					dataSourceConfigId.getDbUserName(), dataSourceConfigId.getDbPassword());
			routingDataSource.updateDynmaicDataSource(dataSourceConfigId.getId(), comboPooledDataSource);
			dataSourceConfigDTO.setDataSourceConfigId(dataSourceConfigId.getId());
			return dataSourceConfigDTO;
		} catch (PropertyVetoException e) {
			throw new MultiTenancyException(e);
		}
	}
}
