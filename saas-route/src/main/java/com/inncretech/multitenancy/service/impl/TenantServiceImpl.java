package com.inncretech.multitenancy.service.impl;

import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.List;
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
import com.inncretech.multitenancy.datasource.master.dto.DataSourceConfigDTO;
import com.inncretech.multitenancy.datasource.master.dto.TenantDTO;
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
	public TenantDTO addTenant(TenantDTO tenantDTO) throws TenantDomainException, DataSourceConfigException,
			MultiTenancyException {
		DataSourceConfig dataSourceConfig = findDataSourceConfigIdForTenant(tenantDTO);

		if (dataSourceConfig == null) {
			throw new MultiTenancyException("DB Config not found");
		}

		System.out.println("TenantService : addTenant DSC " + dataSourceConfig.toString());
		tenantDTO.setDataSourceConfigId(dataSourceConfig.getId());
		tenantValidator.validateTenantDTO(tenantDTO);

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

	// / TODO: need to use ID to compare
	public DataSourceConfig findDataSourceConfigIdForTenant(TenantDTO tenantDTO) throws DataSourceConfigException {

		if (tenantDTO.getDbLeaseType() == com.inncretech.multitenancy.datasource.master.dto.enums.DbLeaseType.DEDICATED) {
			// follow 1 logic
		} else {
			// follow other
		}

		List<Tenant> tenants = tenantRepository.findAll();
		Set<DataSourceConfig> tenantDSCSet = new HashSet<DataSourceConfig>();
		if (tenants != null && tenants.size() > 0) {
			for (Tenant tenant : tenants) {
				tenantDSCSet.add(tenant.getTenantDataSourceConfig());
			}
		} else {
			System.out.println("No existing tenants");
		}
		List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();
		if (dataSourceConfigs != null && dataSourceConfigs.size() > 0) {
			for (DataSourceConfig dataSourceConfig : dataSourceConfigs) {
				if (!(tenantDSCSet.contains(dataSourceConfig))) {
					// if (dataSourceConfig.getId() == 3) {
					return dataSourceConfig;
				}
			}
		}
		throw new DataSourceConfigException("No valid DataSourceConfig Found");

	}

	public TenantDTO getTenantMetaData(Long tenantId) {
		if (tenantId == null || tenantId < 0) {
			throw new MultiTenancyException("Tenant id null");
		}
		Tenant tenant = tenantRepository.findOne(tenantId);
		if (tenant == null) {
			throw new MultiTenancyException("Tenant id not found");
		}
		TenantDTO tenantDTO = new TenantDTO();
		tenantDTO.setTenantId(tenant.getId());
		tenantDTO.setDomain(tenantDTO.getDomain());
		tenantDTO.setDataSourceConfigId(tenant.getTenantDataSourceConfig().getId());
		tenantDTO.setTenantName(tenant.getName());
		return tenantDTO;
	}

	public DataSourceConfigDTO addDataSourceConfig(DataSourceConfigDTO dataSourceConfigDTO) {
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
