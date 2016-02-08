package com.inncretech.multitenancy.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.inncretech.multitenancy.datasource.helper.DataSourceHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.inncretech.multitenancy.master.db.repository" }, entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterTransactinManager")
public class MasterSource {

	private String driverClass = "com.mysql.jdbc.Driver";

	@Value(value = "${master.config.initialPoolSize}")
	private int initialPoolSize = 3;

	@Value(value = "${master.config.minPoolSize}")
	private int minPoolSize;

	@Value(value = "${master.config.maxPoolSize}")
	private int maxPoolSize;

	@Value(value = "${master.config.acquireIncrement}")
	private int acquireIncrement;

	@Value(value = "${master.config.jdbcUrl}")
	private String jdbcUrl;

	@Value(value = "${master.config.user}")
	private String user;

	@Value(value = "${master.config.password}")
	private String password;

	@Bean(name = "masterDataSource")
	public ComboPooledDataSource masterDataSource() throws Exception {
		ComboPooledDataSource comboPooledDataSource = DataSourceHelper.createDataSource(driverClass, initialPoolSize,
				minPoolSize, maxPoolSize, acquireIncrement, jdbcUrl, user, password);
		return comboPooledDataSource;

	}

	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("masterDataSource") ComboPooledDataSource masterDataSource) throws Exception {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(masterDataSource);
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("masterPersistentUnit");
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.inncretech.multitenancy.master.db.entity");
		return localContainerEntityManagerFactoryBean;
	}

	@Bean(name = "masterTransactinManager")
	public JpaTransactionManager transactionManager(
			@Qualifier("masterEntityManagerFactory") LocalContainerEntityManagerFactoryBean masterEntityManagerFactory)
			throws Exception {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(masterEntityManagerFactory.getObject());
		return jpaTransactionManager;
	}
}
