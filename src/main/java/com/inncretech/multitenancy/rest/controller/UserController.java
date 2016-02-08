package com.inncretech.multitenancy.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inncretech.multitenancy.master.dto.UserDTO;
import com.inncretech.multitenancy.service.UserDataService;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;

@RestController
public class UserController {

	@Autowired
	private UserDataService userDataService;

	@RequestMapping(value = "/api/addUser", method = RequestMethod.POST)
	public UserDTO addUser(@RequestBody UserDTO userDTO) throws InvalidArgumentException {
		return userDataService.addUser(userDTO);
	}

	@RequestMapping(value = "/api/getUsers", method = RequestMethod.GET)
	public List<UserDTO> getUsers() throws InvalidArgumentException {
		return userDataService.getUsers();
	}
}
