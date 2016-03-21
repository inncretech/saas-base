package com.inncretech.multitenancy.datasource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.inncretech.multitenancy.datasource.config.MultiTenancyConfiguration;

@Configuration
@ComponentScan(basePackages = { "com.inncretech.multitenancy.datasource.tenant.entity, com.inncretech.multitenancy.datasource.tenant.dao" })
@Import({ MultiTenancyConfiguration.class })
public class IdentityConfig {

}
