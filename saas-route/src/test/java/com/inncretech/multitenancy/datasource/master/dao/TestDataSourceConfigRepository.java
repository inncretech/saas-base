package com.inncretech.multitenancy.datasource.master.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datatsource.BaseTest;

public class TestDataSourceConfigRepository extends BaseTest {

    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;

    @Test
    public void findAll() {
        dataSourceConfigRepository.findAll();
    }
}
