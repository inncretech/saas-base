package com.inncretech.multitenancy.datasource.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.tenant.entity.TestData;

@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long> {

}
