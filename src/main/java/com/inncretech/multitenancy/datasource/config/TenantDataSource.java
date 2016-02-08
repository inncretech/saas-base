package com.inncretech.multitenancy.datasource.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.inncretech.multitenancy.datasource.helper.DataSourceHelper;
import com.inncretech.multitenancy.master.db.entity.DataSourceConfig;
import com.inncretech.multitenancy.master.db.repository.DataSourceConfigRepository;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@Import(value = { MasterSource.class })
@EnableJpaRepositories(basePackages = { "com.inncretech.multitenancy.tenant.db.repository" }, entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactinManager")
public class TenantDataSource {

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

			ComboPooledDataSource comboPooledDataSource = DataSourceHelper.createDataSource(driverClass,
					initialPoolSize, minPoolSize, maxPoolSize, acquireIncrement, "jdbc:mysql://" + config.getDbUrl()
							+ "/" + config.getDbName() + "?zeroDateTimeBehavior=convertToNull", config.getDbUserName(),
					config.getDbPassword());
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
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.inncretech.multitenancy.tenant.db.entity");
		return localContainerEntityManagerFactoryBean;
	}

	@Bean(name = "secondTransactinManager")
	@Autowired
	public JpaTransactionManager secondTransactionManager(
			@Qualifier(value = "routingDataSource") RoutingDataSource routingDataSource) throws Exception {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(secondEntityManagerFactory(routingDataSource).getObject());
		return jpaTransactionManager;
	}
}
