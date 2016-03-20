package com.inncretech.multitenancy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.inncretech.multitenancy.datasource.tenant.dao.UserDataRepository;
import com.inncretech.multitenancy.datasource.tenant.dto.UserDataDto;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;
import com.inncretech.multitenancy.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Transactional
    public UserDataDto addUser(UserDataDto userDTO) {
        UserData user = new UserData();
        user.setId(idGenerator.create(DbContextHolder.getShardId().intValue()));
        user.setUserName(userDTO.getUserName());
        userDataRepository.save(user);
        userDTO.setUserId(user.getId());

        return userDTO;
    }

    @Transactional
    public List<UserDataDto> getUsers() {
        List<UserDataDto> userDTOs = new ArrayList<UserDataDto>();

        for (UserData userData : userDataRepository.findAll()) {
            UserDataDto userDTO = new UserDataDto();
            userDTO.setUserId(userData.getId());
            userDTO.setUserName(userData.getUserName());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
