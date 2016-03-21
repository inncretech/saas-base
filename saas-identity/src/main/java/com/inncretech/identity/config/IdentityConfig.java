package com.inncretech.identity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.inncretech.identity.service" })
@Import({ com.inncretech.multitenancy.datasource.IdentityConfig.class })
public class IdentityConfig {

}
