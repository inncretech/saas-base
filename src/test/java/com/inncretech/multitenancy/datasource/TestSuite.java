package com.inncretech.multitenancy.datasource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.inncretech.multitenancy.datasource.config.TestMultiTenancyConfiguration;
import com.inncretech.multitenancy.datasource.master.dao.TestDataSourceConfigRepository;
import com.inncretech.multitenancy.datasource.master.dao.TestTenantRespository;
import com.inncretech.multitenancy.datasource.tenant.dao.TestUserRepository;
import com.inncretech.multitenancy.service.impl.TestTenantServiceImpl;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestTenantServiceImpl.class, TestMultiTenancyConfiguration.class, TestUserRepository.class, TestTenantRespository.class,
        TestDataSourceConfigRepository.class })
public class TestSuite {

}
