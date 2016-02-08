package com.inncretech.multitenancy.tenant.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inncretech.multitenancy.tenant.db.entity.UserData;

public interface UserRepository extends JpaRepository<UserData, Long> {
	List<UserData> findByName(String name);
}
