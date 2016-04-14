package com.inncretech.multitenancy.datasource.master.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.master.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
	List<Tenant> findByName(String name);

	List<Tenant> findByDomainIn(List<String> domains);

	List<Tenant> findByDomain(String domain);
}
