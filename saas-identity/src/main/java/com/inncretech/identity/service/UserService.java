package com.inncretech.identity.service;

import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;
import com.inncretech.multitenancy.datasource.tenant.entity.User;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO getUserByEmail(String email);

    User authenticateUserByEmail(String userName, String password);

    String generateAccessToken(String userName, String password);

    UserDTO authenticateAccessToken(String email, String accessToken);

    UserDTO signin(String email, String password);

    UserDTO getUserById(Long userId);

    UserDTO authenticateAccessToken(Long userId, String accessToken);

}
