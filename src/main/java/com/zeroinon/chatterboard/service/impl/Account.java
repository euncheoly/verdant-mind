package com.zeroinon.chatterboard.service.impl;

import com.google.gson.Gson;
import com.zeroinon.chatterboard.dto.response.GenericResponseDTO;
import com.zeroinon.chatterboard.dto.UserDTO;
import com.zeroinon.chatterboard.exception.UserException;
import com.zeroinon.chatterboard.mapper.UserMapper;
import com.zeroinon.chatterboard.service.JwtService;
import com.zeroinon.chatterboard.service.UserService;
import com.zeroinon.chatterboard.utils.BCryptUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Account implements UserService {


    private final UserMapper userMapper;
    private final JwtService jwtService;

    public Account(UserMapper userMapper, JwtService jwtService) {
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @Override
    public GenericResponseDTO register(UserDTO userProfile) {

        boolean duplicateResult = isDuplicateId(userProfile.getUserId());
        if (duplicateResult) {
            throw new UserException.DuplicatedUserID("Duplicated User ID");
        }
        userProfile.setPassword(BCryptUtils.bcryptHash(userProfile.getPassword()));


        int registerResult = userMapper.insertUser(userProfile);
        if (registerResult != 1) {
            throw new RuntimeException("Register failed");
        }
        return GenericResponseDTO.of(registerResult);
    }

    @Override
    public GenericResponseDTO login(UserDTO userDTO) {

        String hashed = userMapper.findHashByUserid(userDTO);

        if(hashed == null || !BCrypt.checkpw(userDTO.getPassword(), hashed)){
            throw new UserException.InvalidPassword("Invalid Password");
        }
        String access = jwtService.generateToken(userDTO.getUserId(), JwtService.TokenRole.ACCESS);
        String refresh = jwtService.generateToken(userDTO.getUserId(), JwtService.TokenRole.REFRESH);
        HashMap jwt = new HashMap();
        jwt.put("access", access);
        jwt.put("refresh", refresh);
        return GenericResponseDTO.of(jwt);
    }

    @Override
    public boolean isDuplicateId(String id) {
        boolean isDuplicateId = userMapper.isDuplicateId(id) > 0 ? true : false;
        System.out.println("isDuplicateId: " + isDuplicateId);
        return isDuplicateId;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return null;
    }

    @Override
    public GenericResponseDTO updatePassword(UserDTO userDTO) {

        return null;
    }

    @Override
    public void deleteId(String id, String password) {
    }
}
