package com.inncretech.multitenancy.datasource.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.inncretech.multitenancy.datasource.master.dao.DataSourceConfigRepository;
import com.inncretech.multitenancy.datasource.master.entity.DataSourceConfig;
import com.inncretech.multitenancy.datasource.utils.DataSourceUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.inncretech.multitenancy.datasource.tenant.dao" }, entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactinManager")
public class RoutingDataSourceConfig {

    private String driverClass = "com.mysql.jdbc.Driver";

    private int initialPoolSize = 3;

    private int minPoolSize = 1;

    private int maxPoolSize = 10;

    private int acquireIncrement = 1;

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    @Bean(name = "routingDataSource")
    public RoutingDataSource getRoutingDataSource() throws Exception {

        List<DataSourceConfig> dataSourceConfigs = dataSourceConfigRepository.findAll();

        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        for (DataSourceConfig config : dataSourceConfigs) {

            ComboPooledDataSource comboPooledDataSource = DataSourceUtils.createDataSource(driverClass, initialPoolSize, minPoolSize, maxPoolSize,
                    acquireIncrement,
                    "jdbc:mysql://" + config.getDbUrl() + "/" + config.getDbName() + "?zeroDateTimeBehavior=convertToNull&useSSL=false",
                    config.getDbUserName(), config.getDbPassword());
            targetDataSources.put(config.getId(), comboPooledDataSource);
            routingDataSource.setDefaultTargetDataSource(comboPooledDataSource);
        }
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setLenientFallback(false);
        return routingDataSource;
    }

    @Bean(name = "secondEntityManagerFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            @Qualifier(value = "routingDataSource") RoutingDataSource routingDataSource) throws Exception {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(routingDataSource);
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("secondaryPersistentUnit");
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.inncretech.multitenancy.datasource.tenant.entity",
                "com.vxlpartners.appanalytix.datasource.tenant.entity");
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "secondTransactinManager")
    @Autowired
    public JpaTransactionManager secondTransactionManager(@Qualifier(value = "routingDataSource") RoutingDataSource routingDataSource)
            throws Exception {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(secondEntityManagerFactory(routingDataSource).getObject());
        return jpaTransactionManager;
    }
}
