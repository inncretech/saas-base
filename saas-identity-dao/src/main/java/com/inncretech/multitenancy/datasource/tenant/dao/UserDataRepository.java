package com.inncretech.multitenancy.datasource.tenant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.tenant.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long>, JpaSpecificationExecutor<UserData> {

    UserData getUserById(Long id);

    UserData findByEmail(String emailId);

    UserData findByPhoneNumber(String phoneNumber);

    @Query(value = " from UserData where email =?1 or ( firstName = ?2 or lastName =?2)")
    List<UserData> findByEmailOrName(String emailId, String userName);

    @Query(value = " from UserData where  firstName = ?1 or lastName =?1")
    List<UserData> getUserByUserFirstAndLastName(String userName);

    UserData getUserByUserName(String userName);

    UserData getUserByUserNameOrEmail(String userName, String email);
}
