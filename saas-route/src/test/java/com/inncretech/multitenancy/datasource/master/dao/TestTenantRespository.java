package com.inncretech.multitenancy.datasource.master.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datatsource.BaseTest;

public class TestTenantRespository extends BaseTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    public void findAll() {
        tenantRepository.findAll();
    }

}
