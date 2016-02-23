package com.inncretech.identity.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.inncretech.identity.service.UserService;
import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;

public class TestUserServiceImpl extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserDTO() {
        UserDTO createUser = createUser();
        Assert.notNull(createUser);
        System.out.println(createUser);
    }

    private UserDTO createUser() {
        long time = System.currentTimeMillis();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test-" + time + "@gmail.com");
        userDTO.setFirstName("Test-" + time);
        userDTO.setLastName("Test-" + time);
        userDTO.setPassword("password");
        UserDTO createUser = userService.createUser(userDTO);
        return createUser;
    }

    @Test
    public void signIn() {
        UserDTO createUser = createUser();
        UserDTO signin = userService.signin(createUser.getEmail(), "password".toCharArray());
        Assert.notNull(signin);
        System.out.println(signin);
    }

    @Test
    public void generateAccessToken() {
        UserDTO createUser = createUser();
        String generateAccessToken = userService.generateAccessToken(createUser.getEmail(), "password".toCharArray());
        Assert.notNull(generateAccessToken);
        System.out.println(generateAccessToken);
    }
}
