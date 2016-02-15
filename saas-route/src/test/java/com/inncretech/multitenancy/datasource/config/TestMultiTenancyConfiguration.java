package com.inncretech.multitenancy.datasource.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.datasource.config.MultiTenancyConfiguration;
import com.inncretech.multitenancy.datasource.config.RoutingDataSource;
import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MultiTenancyConfiguration.class })
@ActiveProfiles("dev")
public class TestMultiTenancyConfiguration {

    @Autowired
    @Qualifier("masterDataSource")
    private ComboPooledDataSource masterDataSource;

    @Autowired
    @Qualifier("routingDataSource")
    private RoutingDataSource routingDatasource;

    @Test
    public void masterDataSource() {
        Assert.notNull(masterDataSource);
    }

    @Test
    public void routingDataSource() throws SQLException {
        Assert.notNull(routingDatasource);
        DbContextHolder.setShardId(1);
        Connection connection = routingDatasource.getConnection();
        Assert.state(connection.getCatalog().equals("db1"));
        Assert.notNull(routingDatasource);
        DbContextHolder.setShardId(2);
        connection = routingDatasource.getConnection();
        Assert.state(connection.getCatalog().equals("db2"));
        Assert.notNull(routingDatasource);
        DbContextHolder.setShardId(3);
        connection = routingDatasource.getConnection();
        Assert.state(connection.getCatalog().equals("db3"));
    }
}
