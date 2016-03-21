package com.inncretech.multitenancy.datasource.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;

@Repository
public interface UserAccessTokenRepository extends JpaRepository<UserAccessToken, Long> {

    UserAccessToken getUserAccessTokenByUserId(Long userId);
}
