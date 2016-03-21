package com.inncretech.multitenancy.datasource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.inncretech.multitenancy.datasource.tenant.dao.TestUserDataRepository;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestUserDataRepository.class })
public class TestSuite {

}
