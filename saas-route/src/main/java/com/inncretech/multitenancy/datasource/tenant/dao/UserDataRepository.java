package com.inncretech.multitenancy.datasource.tenant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inncretech.multitenancy.datasource.tenant.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	List<UserData> findByName(String name);
}
