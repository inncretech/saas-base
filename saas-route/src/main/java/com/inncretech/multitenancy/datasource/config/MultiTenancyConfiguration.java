package com.inncretech.multitenancy.datasource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Import({ RoutingDataSourceConfig.class })
@ComponentScan(basePackages = { "com.inncretech.multitenancy.datasource.utils", "com.inncretech.multitenancy.service" })
public class MultiTenancyConfiguration {

    private static final Resource[] DEV_PROPERTIES = new ClassPathResource[] { new ClassPathResource("properties/dev-db.properties"), };
    private static final Resource[] PREPROD_PROPERTIES = new ClassPathResource[] { new ClassPathResource("properties/preprod-db.properties"), };
    private static final Resource[] PROD_PROPERTIES = new ClassPathResource[] { new ClassPathResource("properties/prod-db.properties"), };

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Profile({ "dev" })
    public static class DevConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(DEV_PROPERTIES);
            return pspc;
        }
    }

    @Profile({ "preprod" })
    public static class CloudConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(PREPROD_PROPERTIES);
            return pspc;
        }
    }

    @Profile({ "prod" })
    public static class QAConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(PROD_PROPERTIES);
            return pspc;
        }
    }

}