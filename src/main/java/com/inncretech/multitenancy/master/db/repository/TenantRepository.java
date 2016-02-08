package com.inncretech.multitenancy.master.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.master.db.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
	List<Tenant> findByName(String name);
}
