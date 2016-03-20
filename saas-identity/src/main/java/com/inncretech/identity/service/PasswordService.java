package com.inncretech.identity.service;

import com.inncretech.multitenancy.datasource.tenant.entity.UserData;
import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;

public interface PasswordService {

    UserAccessToken generateAccessToken(UserData user, char[] userInputPassword);

    String generateResetPasswordToken();

    boolean checkPassword(char[] inputPassword, UserData userDbEntity);

}
