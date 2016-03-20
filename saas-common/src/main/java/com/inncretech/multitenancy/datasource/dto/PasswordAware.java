package com.inncretech.multitenancy.datasource.dto;

public interface PasswordAware {

    String getUsername();

    char[] getPassword();

}
