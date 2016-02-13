package com.inncretech.multitenancy.service.impl;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.multitenancy.datasource.config.RoutingDataSource;
import com.inncretech.multitenancy.datasource.enums.DbLeaseType;
import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
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
    public TenantDTO addTenant(TenantDTO tenantDTO) {

        tenantValidator.validateTenantDTO(tenantDTO);

        DataSourceConfig dataSourceConfig = dataSourceConfigRepository.findOne(tenantDTO.getDataSourceConfigId());

        if (dataSourceConfig == null) {
            throw new MultiTenancyException("DB Config not found");
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

    public TenantDTO getTenantMetaData(Long tenantId) {
        if (tenantId == null || tenantId < 0) {
            throw new MultiTenancyException("Tenant id null");
        }
        Tenant tenant = tenantRepository.findOne(tenantId);
        if (tenant == null) {
            throw new MultiTenancyException("Tenant id not found");
        }
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setTenantId(tenant.getTenantId());
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
            comboPooledDataSource = DataSourceUtils.createDataSource(driverClass, initialPoolSize, minPoolSize, maxPoolSize, acquireIncrement,
                    "jdbc:mysql://" + dataSourceConfigId.getDbUrl() + "/" + dataSourceConfigId.getDbName() + "?zeroDateTimeBehavior=convertToNull",
                    dataSourceConfigId.getDbUserName(), dataSourceConfigId.getDbPassword());
            routingDataSource.updateDynmaicDataSource(dataSourceConfigId.getId(), comboPooledDataSource);
            dataSourceConfigDTO.setDataSourceConfigId(dataSourceConfigId.getId());
            return dataSourceConfigDTO;
        } catch (PropertyVetoException e) {
            throw new MultiTenancyException(e);
        }
    }
}
