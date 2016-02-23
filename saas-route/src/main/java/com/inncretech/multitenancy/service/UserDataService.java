package com.inncretech.multitenancy.service;

import java.util.List;

import com.inncretech.multitenancy.datasource.tenant.dto.UserDataDto;

public interface UserDataService {

	UserDataDto addUser(UserDataDto userDTO);

	List<UserDataDto> getUsers();
}
