package com.inncretech.identity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.inncretech.multitenancy.datasource.config.MultiTenancyConfiguration;

@Configuration
@ComponentScan(basePackages = { "com.inncretech.identity.service" })
@Import({ MultiTenancyConfiguration.class })
public class IdentityConfig {

}
