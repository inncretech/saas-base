package com.inncretech.multitenancy.datasource.tenant.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.datasource.AbstractRoutingTest;

public class TestTestDataRepository extends AbstractRoutingTest {

    @Autowired
    private TestDataRepository testDataRepository;

    @Test
    public void testDataRepository() {
        Assert.notNull(testDataRepository);
    }
}
