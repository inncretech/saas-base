package com.inncretech.identity.service;

import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO getUserByEmail(String email);

    UserData authenticateUserByEmail(String userName, char[] password);

    String generateAccessToken(String userName, char[] password);

    UserDTO authenticateAccessToken(String email, String accessToken);

    UserDTO signin(String email, char[] password);

    UserDTO getUserById(Long userId);

    UserDTO authenticateAccessToken(Long userId, String accessToken);

}
