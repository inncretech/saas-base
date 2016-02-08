package com.inncretech.multitenancy.service;

import java.util.List;

import com.inncretech.multitenancy.master.dto.UserDTO;

public interface UserDataService {

	UserDTO addUser(UserDTO userDTO);

	List<UserDTO> getUsers();
}
