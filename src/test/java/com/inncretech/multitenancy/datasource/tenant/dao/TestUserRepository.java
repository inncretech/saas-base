package com.inncretech.multitenancy.datasource.tenant.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;
import com.inncretech.multitenancy.datatsource.BaseTest;

public class TestUserRepository extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Test
    @Transactional
    public void saveUser() {
        DbContextHolder.setShardId(3);
        UserData user = new UserData();
        user.setId(idGenerator.create(DbContextHolder.getShardId().intValue()));
        user.setName("userName-" + System.currentTimeMillis());
        UserData savedUserData = userRepository.save(user);
        System.out.println(savedUserData);
    }

    @Test
    @Transactional
    public void findAll() {
        DbContextHolder.setShardId(3);
        List<UserData> userDataList = userRepository.findAll();
        System.out.println(userDataList);
    }
}
