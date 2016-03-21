package com.inncretech.multitenancy.datasource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.inncretech.multitenancy.datasource.master.dao.TestDataSourceConfigRepository;
import com.inncretech.multitenancy.datasource.master.dao.TestSubjectAreaRespository;
import com.inncretech.multitenancy.datasource.master.dao.TestTenantRespository;
import com.inncretech.multitenancy.datasource.tenant.dao.TestTestDataRepository;
import com.inncretech.multitenancy.service.impl.TestTenantServiceImpl;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestTenantServiceImpl.class, TestTenantRespository.class, TestDataSourceConfigRepository.class,
        TestSubjectAreaRespository.class, TestTestDataRepository.class })
public class TestSuite {

}
