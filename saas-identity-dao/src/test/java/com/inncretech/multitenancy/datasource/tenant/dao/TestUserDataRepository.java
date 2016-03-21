package com.inncretech.multitenancy.datasource.tenant.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;

import com.inncretech.multitenancy.datasource.AbstractIdentityBaseTest;
import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;

public class TestUserDataRepository extends AbstractIdentityBaseTest {


    @Test
    @Transactional
    public void saveUserData() {
        long currentTimeMillis = System.currentTimeMillis();
        DbContextHolder.setShardId(3);
        UserData user = new UserData();
        user.setId(idGenerator.create(DbContextHolder.getShardId().intValue()));
        user.setFirstName("userName-" + currentTimeMillis);
        user.setUserName("test-" + currentTimeMillis);
        user.setPassword("password");
        user.setEmail("Test@gmail.com");
        UserData savedUser = userDataRepository.save(user);
        System.out.println(savedUser);
    }

    @Test
    @Transactional
    public void findAll() {
        DbContextHolder.setShardId(3);
        List<UserData> userList = userDataRepository.findAll();
        System.out.println(userList);
    }
}
