package com.inncretech.multitenancy.datatsource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.multitenancy.datasource.config.MultiTenancyConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MultiTenancyConfiguration.class })
@ActiveProfiles("dev")
public class BaseTest {

}
