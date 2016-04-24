package com.inncretech.multitenancy.datasource.master.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.datasource.AbstractRoutingTest;
import com.inncretech.multitenancy.datasource.master.entity.DataSourceConfig;
import com.inncretech.multitenancy.datasource.master.entity.Tenant;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;

public class TestTenantRespository extends AbstractRoutingTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    @Test 
    public void createTenant() {
        List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();
        Map<Long, DataSourceConfig> dataSourceConfigMap = new HashMap<>();
        for (DataSourceConfig dataSourceConfig : dataSourceConfigs) {
            dataSourceConfigMap.put(dataSourceConfig.getId(), dataSourceConfig);
        }
        Tenant tenant = new Tenant();
        tenant.setDescription("For Testing websocket");
        tenant.setName("Websocket Tenant");
        DataSourceConfig config = dataSourceConfigMap.get(3L);
        config.setId(3L);
        tenant.setId(idGenerator.create(config.getId().intValue()));
        tenant.setTenantDataSourceConfig(config);
        Tenant save = tenantRepository.save(tenant);
        Assert.notNull(save);
        Assert.notNull(save.getId());
        System.out.println(save);
    }

    @Test
    public void findAll() {
        List<Tenant> tenants = tenantRepository.findAll();
        System.out.println(tenants);
    }
}
