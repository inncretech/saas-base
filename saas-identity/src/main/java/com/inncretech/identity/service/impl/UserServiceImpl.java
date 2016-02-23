package com.inncretech.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.inncretech.identity.service.UserService;
import com.inncretech.multitenancy.datasource.tenant.dao.UserAccessTokenRepository;
import com.inncretech.multitenancy.datasource.tenant.dao.UserRepository;
import com.inncretech.multitenancy.datasource.tenant.dto.UserDTO;
import com.inncretech.multitenancy.datasource.tenant.entity.User;
import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordServiceImpl passwordService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private UserServiceMapper mapper;

    @Autowired
    private UserAccessTokenRepository userAccessTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO signin(String email, char[] password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password.toString());
        mapper.mapUserDTOFromUser(authenticateUserByEmail(email, password), userDTO);
        return userDTO;
    }

    @Override
    public UserDTO authenticateAccessToken(String email, String accessToken) {
        UserDTO userDTO = getUserByEmail(email);
        UserAccessToken userAccessToken = userAccessTokenRepository.getUserAccessTokenByUserId(userDTO.getUserId());
        if (userAccessToken.getAccessToken().equals(accessToken)) {
            userDTO.setAuthToken(accessToken);
            return userDTO;
        } else
            return null;
    }

    @Override
    public String generateAccessToken(String email, char[] password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            UserAccessToken userAccessToken = passwordService.generateAccessToken(user, password);
            userAccessTokenRepository.save(userAccessToken);
            return userAccessToken.getAccessToken();
        }

        return null;
    }

    @Override
    public UserDTO authenticateAccessToken(Long userId, String accessToken) {
        UserDTO userDTO = getUserById(userId);
        UserAccessToken userAccessToken = userAccessTokenRepository.getUserAccessTokenByUserId(userDTO.getUserId());
        if (userAccessToken.getAccessToken().equals(accessToken)) {
            userDTO.setAuthToken(accessToken);
            return userDTO;
        } else
            return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO userDTO = new UserDTO();
        User user = userRepository.getUserById(userId);
        if (user == null) {
            System.out.println();
            return null;
        }
        mapper.mapUserDTOFromUser(user, userDTO);
        return userDTO;
    }

    @Override
    public User authenticateUserByEmail(String email, char[] password) {

        User user = userRepository.findByEmail(email);
        if (passwordService.checkPassword(password, user)) {
            passwordService.generateAccessToken(user, password);
            return user;
        }
        return null;
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            UserDTO userDTO2 = addUser(userDTO);
            userDTO.setUserId(userDTO2.getUserId());
            return userDTO2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private UserDTO addUser(UserDTO userDTO) throws Exception {
        User user = new User();
        mapper.mapUserFromUserDTO(userDTO, user);
        try {
            user.setId(idGenerator.create());
            passwordService.initializeCryptoForNewUser(user);
            userRepository.save(user);
        } catch (Exception exception) {

            exception.printStackTrace();
            throw new Exception("Internal Service");
        }
        String accessToken = generateAccessToken(userDTO.getEmail(), userDTO.getPassword().toCharArray());
        UserDTO resultantUserDTO = new UserDTO();
        mapper.mapUserDTOFromUser(user, resultantUserDTO);
        resultantUserDTO.setAuthToken(accessToken);
        return resultantUserDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = new UserDTO();
        System.out.println("UserServiceImpl " + email);
        ;
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println();
            return null;
        }
        mapper.mapUserDTOFromUser(user, userDTO);
        return userDTO;
    }
}