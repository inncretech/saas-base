package com.inncretech.identity.service;

import com.inncretech.multitenancy.datasource.tenant.entity.User;
import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;

public interface PasswordService {

    UserAccessToken generateAccessToken(User user, String userInputPassword);

    String generateResetPasswordToken();

    boolean checkPassword(String inputPassword, User userDbEntity);

}
