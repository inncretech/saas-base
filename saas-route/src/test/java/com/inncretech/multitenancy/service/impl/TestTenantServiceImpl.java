package com.inncretech.multitenancy.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.datasource.AbstractRoutingTest;
import com.inncretech.multitenancy.datasource.exceptions.MultiTenancyException;
import com.inncretech.multitenancy.datasource.master.dto.TenantDTO;
import com.inncretech.multitenancy.service.TenantService;

public class TestTenantServiceImpl extends AbstractRoutingTest {

    @Autowired
    private TenantService tenantService;

    @Rollback(false)
    @Test(expected = MultiTenancyException.class)
    public void validationTest() throws Exception {
        TenantDTO tenantDTO = new TenantDTO();
        tenantService.addTenant(tenantDTO);
    }

    @Rollback(false)
    @Test(expected = MultiTenancyException.class)
    public void addTenantNegative() throws Exception {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDataSourceConfigId(10l);
        tenantDTO.setDomain("test");
        tenantDTO.setTenantName("test");
        tenantService.addTenant(tenantDTO);
    }

    @Rollback(false)
    @Test
    public void addTenant1() throws Exception {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDataSourceConfigId(1l);
        tenantDTO.setDomain("test1" + System.currentTimeMillis());
        tenantDTO.setTenantName("test");
        TenantDTO addTenant = tenantService.addTenant(tenantDTO);
        Assert.notNull(addTenant.getTenantId());
    }

    @Rollback(false)
    @Test
    public void addTenant2() throws Exception {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDataSourceConfigId(2l);
        tenantDTO.setDomain("test2" + System.currentTimeMillis());
        tenantDTO.setTenantName("test");
        TenantDTO addTenant = tenantService.addTenant(tenantDTO);
        Assert.notNull(addTenant.getTenantId());
    }

    @Rollback(false)
    @Test
    public void addTenant3() throws Exception {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDataSourceConfigId(3l);
        tenantDTO.setDomain("test" + System.currentTimeMillis());
        tenantDTO.setTenantName("test3");
        TenantDTO addTenant = tenantService.addTenant(tenantDTO);
        Assert.notNull(addTenant.getTenantId());
    }

    @Rollback(false)
    @Test(expected = MultiTenancyException.class)
    public void getTenants() {
        tenantService.getTenantMetaData(null);
    }
}
