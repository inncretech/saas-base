package com.inncretech.multitenancy.datasource.helper;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceHelper {

	public static ComboPooledDataSource createDataSource(String driverClass, int initialPoolSize, int minPoolSize,
			int maxPoolSize, int acquireIncrement, String jdbcUrl, String user, String password)
			throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setInitialPoolSize(initialPoolSize);
		comboPooledDataSource.setMinPoolSize(minPoolSize);
		comboPooledDataSource.setMaxPoolSize(maxPoolSize);
		comboPooledDataSource.setTestConnectionOnCheckin(true);
		comboPooledDataSource.setAcquireIncrement(acquireIncrement);
		comboPooledDataSource.setMaxIdleTime(3600);
		comboPooledDataSource.setJdbcUrl(jdbcUrl);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		return comboPooledDataSource;
	}
}
