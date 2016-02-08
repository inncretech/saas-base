package com.inncretech.multitenancy.master.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.master.db.entity.DataSourceConfig;

@Repository
public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {

}
