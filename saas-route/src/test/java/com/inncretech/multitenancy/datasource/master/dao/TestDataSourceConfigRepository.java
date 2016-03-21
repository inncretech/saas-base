package com.inncretech.multitenancy.datasource.master.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datasource.AbstractRoutingTest;

public class TestDataSourceConfigRepository extends AbstractRoutingTest {

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    @Test
    public void findAll() {
        dataSourceConfigRepository.findAll();
    }
}
