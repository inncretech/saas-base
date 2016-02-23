package com.inncretech.identity.service;

import com.inncretech.multitenancy.datasource.tenant.entity.User;
import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;

public interface PasswordService {

    UserAccessToken generateAccessToken(User user, char[] userInputPassword);

    String generateResetPasswordToken();

    boolean checkPassword(char[] inputPassword, User userDbEntity);

}
