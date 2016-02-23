package com.inncretech.multitenancy.datasource.tenant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.tenant.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User getUserById(Long id);

    User findByEmail(String emailId);

    @Query(value = " from User where email =?1 or ( firstName = ?2 or lastName =?2)")
    List<User> findByEmailOrName(String emailId, String userName);

    @Query(value = " from User where  firstName = ?1 or lastName =?1")
    List<User> getUserByUserFirstAndLastName(String userName);
}
