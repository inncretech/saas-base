package com.inncretech.identity.service.impl;

import static com.inncretech.identity.utils.MapperUtils.checkArgument;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.inncretech.identity.utils.MapperUtils;
import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;
import com.inncretech.multitenancy.datasource.tenant.entity.UserData;

@Component
public class UserServiceMapper {

    public void mapUserFromUserDTO(UserDTO userDTO, UserData user) {

        checkArgument(userDTO, MapperUtils.Object_Type.SOURCE);
        checkArgument(user, MapperUtils.Object_Type.DESTINATION);

        user.setId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        if (!StringUtils.isEmpty(userDTO.getFirstName())) {
            user.setFirstName(userDTO.getFirstName());
        }
        // user.setFirstName(userDTO.getFirstName());
        if (!StringUtils.isEmpty(userDTO.getLastName())) {
            user.setLastName(userDTO.getLastName());
        }
        // user.setLastName(userDTO.getLastName());
        if (!StringUtils.isEmpty(userDTO.getPassword())) {
            // user.setPassword(new Md5PasswordEncoder().encodePassword(userDTO.getPassword(), null));
            user.setPassword(userDTO.getPassword());
        }
        // user.setPassword(userDTO.getPassword());
        user.setUserName(userDTO.getEmail());

    }

    public void mapUserDTOFromUser(UserData user, UserDTO userDTO) {
        checkArgument(user, MapperUtils.Object_Type.SOURCE);
        checkArgument(userDTO, MapperUtils.Object_Type.DESTINATION);
        userDTO.setUserId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
    }
}