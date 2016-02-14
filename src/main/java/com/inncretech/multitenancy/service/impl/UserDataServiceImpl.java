package com.inncretech.multitenancy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.inncretech.multitenancy.datasource.tenant.dao.UserRepository;
import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;
import com.inncretech.multitenancy.service.UserDataService;

@Component
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        UserData user = new UserData();
        user.setId(idGenerator.create(DbContextHolder.getShardId().intValue()));
        user.setName(userDTO.getUserName());
        userRepository.save(user);
        userDTO.setUserId(user.getId());

        return userDTO;
    }

    @Transactional
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();

        for (UserData userData : userRepository.findAll()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userData.getId());
            userDTO.setUserName(userData.getName());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
